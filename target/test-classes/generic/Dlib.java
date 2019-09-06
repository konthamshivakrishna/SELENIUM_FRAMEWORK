package test.resources.generic;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;

public class Dlib
{
	public static void onTestCaseStart()
	{
		String TestCaseDescription = getTestCaseDescription(GlobalVariables.CurrentTestCase);
		String CurrentBrowser = getCurrentBrowser(GlobalVariables.CurrentTestCase);
   	    GlobalVariables.logger = GlobalVariables.report.startTest(GlobalVariables.CurrentTestCase, TestCaseDescription);
   	    GlobalVariables.ClassPath = "test.testcases" + "." + GlobalVariables.CurrentTestCase;
        TestData.loadData();
        System.out.println("Start:" + GlobalVariables.CurrentTestCase);
        GlobalVariables.driver = WebLib.launchBrowser(CurrentBrowser);
	}
	public static String getCurrentBrowser(String currentTestCase) 
	{
		String CurrentBrowser = "";
		try
		{
			//Access to RunManager.xls file
			File f1 = new File(GlobalVariables.CurrentDirectory + "\\" +GlobalVariables.RunManagerName);
			//Create File Input Steam Object
			FileInputStream fis = new FileInputStream(f1);
			//Create Workbook Object
			XSSFWorkbook wb1 = new XSSFWorkbook(fis);
			//Create Worksheet Object
			XSSFSheet ws1 = wb1.getSheet("Main");
			//get Row count
			int rowcount = ws1.getLastRowNum();
			//Iterate Through all rows and get the testcases which are set to true.
			for(int j = 1;j<=rowcount;j++)
			{
				Cell cell = ws1.getRow(j).getCell(0);
				if (cell == null) break;
				if(cell.getStringCellValue().toLowerCase().trim().equalsIgnoreCase(currentTestCase.toLowerCase().trim()))
				{
					Cell cell1 = ws1.getRow(j).getCell(2);
					CurrentBrowser = cell1.getStringCellValue();
				}
			}
			//close workbook
			wb1.close();
			//close input stream
			fis.close();
		}
		catch(Exception e)
		{
			CurrentBrowser = "";
		}
		return CurrentBrowser;
	}
	public static void onTestCaseFinish()
	{
		TestData.exportData();
        GlobalVariables.driver.close();
        System.out.println("End:" + GlobalVariables.CurrentTestCase + "\n");
        GlobalVariables.report.endTest(GlobalVariables.logger);
	}
	
	public static void onExecutionStart() 
	{
		  	String CurrentFolderPath = System.getProperty("user.dir");
		  	CurrentFolderPath = CurrentFolderPath.replace("\\src","");
			GlobalVariables.CurrentDirectory = CurrentFolderPath;
			GlobalVariables.TestDataPath = CurrentFolderPath + "\\TestData\\";
			GlobalVariables.ResultsFolderPath = CurrentFolderPath + "\\Results\\";
			String CurrentResultsPath = createresultsfolder();
			GlobalVariables.CurrentResultsFolderPath = CurrentResultsPath;
			GlobalVariables.ScreenshotsFolderPath = GlobalVariables.CurrentResultsFolderPath + "\\ScreenShots\\";
			GlobalVariables.ResultFilePath = GlobalVariables.CurrentResultsFolderPath + "\\Summary.html";
			GlobalVariables.report = new ExtentReports(GlobalVariables.ResultFilePath);
	 }
	public static String getTestCases()
	{
		String ExcelTestCasesName = "";
		try
		{
			//Access to RunManager.xls file
			File f1 = new File(GlobalVariables.CurrentDirectory + "\\" + GlobalVariables.RunManagerName);
			//Create File Input Steam Object
			FileInputStream fis = new FileInputStream(f1);
			//Create Workbook Object
			XSSFWorkbook wb1 = new XSSFWorkbook(fis);
			//Create Worksheet Object
			XSSFSheet ws1 = wb1.getSheet("Main");
			//get Row count
			int rowcount = ws1.getLastRowNum();
			//Iterate Through all rows and get the testcases which are set to true.
			for(int j = 1;j<=rowcount;j++)
			{
				Cell cell = ws1.getRow(j).getCell(3);
				if(cell.getStringCellValue().toLowerCase().trim().equalsIgnoreCase("true".toLowerCase().trim()))
				{
					Cell cell1 = ws1.getRow(j).getCell(0);
					ExcelTestCasesName = ExcelTestCasesName + ";;" + cell1.getStringCellValue();
				}
			}
			//close workbook
			wb1.close();
			//close input stream
			fis.close();
		}
		catch(Exception e)
		{
			
		}
		return ExcelTestCasesName;
	}
	public static String getTestCaseDescription(String TestCaseName)
	{
		String TestCaseDescription = "";
		try
		{
			//Access to RunManager.xls file
			File f1 = new File(GlobalVariables.CurrentDirectory + "\\" +GlobalVariables.RunManagerName);
			//Create File Input Steam Object
			FileInputStream fis = new FileInputStream(f1);
			//Create Workbook Object
			XSSFWorkbook wb1 = new XSSFWorkbook(fis);
			//Create Worksheet Object
			XSSFSheet ws1 = wb1.getSheet("Main");
			//get Row count
			int rowcount = ws1.getLastRowNum();
			//Iterate Through all rows and get the testcases which are set to true.
			for(int j = 1;j<=rowcount;j++)
			{
				Cell cell = ws1.getRow(j).getCell(0);
				if (cell == null) break;
				if(cell.getStringCellValue().toLowerCase().trim().equalsIgnoreCase(TestCaseName.toLowerCase().trim()))
				{
					Cell cell1 = ws1.getRow(j).getCell(1);
					TestCaseDescription = cell1.getStringCellValue();
				}
			}
			//close workbook
			wb1.close();
			//close input stream
			fis.close();
		}
		catch(Exception e)
		{
			TestCaseDescription = "";
		}
		return TestCaseDescription;
	}
	@SuppressWarnings("unused")
	public static boolean execute(String ClassPath)
	{
		boolean status = true;
		try
		{
	        Class<?> c = Class.forName(ClassPath);
	        Method m = c.getDeclaredMethod(GlobalVariables.MainMethodName, (Class<?>[]) null);
	        Object o = m.invoke((Object[]) null, (Object[]) null);
		}
		catch(Exception e)
		{
			status = false;
		}
		return status;
	}
	private static String createresultsfolder() 
	{
		DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance();
	    String DateTimeStamp = dateTimeInstance.format(Calendar.getInstance().getTime());
	    DateTimeStamp = DateTimeStamp.replace(",", "");
	    DateTimeStamp = DateTimeStamp.replace(" ", "_");
	    DateTimeStamp = DateTimeStamp.replace(":", "-");
		File dir = new File(GlobalVariables.ResultsFolderPath + DateTimeStamp);
		dir.mkdir();
		return GlobalVariables.ResultsFolderPath + DateTimeStamp;
	}
	public static void onExecutionFinish() 
	{
		GlobalVariables.report.close();
		WebDriver driver = new FirefoxDriver();
		driver.get(GlobalVariables.ResultFilePath);
		driver.manage().window().maximize();
	 }
}
