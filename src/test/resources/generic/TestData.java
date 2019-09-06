package test.resources.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestData 
{
	@SuppressWarnings("rawtypes")
	static HashMap  dict = new HashMap();
	@SuppressWarnings({ "unchecked", "unused" })
	public static void loadData()
	{
		try
		{
			//Clear existing values
			dict.clear();
			String CurrentTestCase = GlobalVariables.CurrentTestCase;
			String ColumnName = "TestCaseName";
			String TestData = "";
			//access to the testdata file
			File f1 = new File(GlobalVariables.CurrentDirectory + "\\" + GlobalVariables.testdatapath);
			//create file input stream object
			FileInputStream fis = new FileInputStream(f1);
			//create work book object
			XSSFWorkbook wb1 = new XSSFWorkbook(fis);
			//create work sheet object
			XSSFSheet ws1 = wb1.getSheet(GlobalVariables.TestDataSheetName);
			//get total row count.
			int rowcount = ws1.getLastRowNum();
			//use 'foundrow' to verify if the specified testcase exists
			int foundrow = -1;
			for(int j = 0;j<=rowcount;j++)
			{
				Cell cell = ws1.getRow(j).getCell(0);
				String ExcelTestCaseName =cell.getStringCellValue();
				if(ExcelTestCaseName.toLowerCase().trim().equalsIgnoreCase(GlobalVariables.CurrentTestCase.toLowerCase().trim()))
				{
					foundrow = j;
					break;
				}
			}
			//if the testcase row is found, then add all column values to dict object
			if(foundrow!=-1)
			{
				int colcount = ws1.getRow(0).getLastCellNum();
				for(int i = 1;i<colcount;i++)
				{
					String ColumnName1 = ws1.getRow(0).getCell(i).getStringCellValue();
					String ColumnValue1 = "";
					try
					{
						ColumnValue1 =ws1.getRow(foundrow).getCell(i).getStringCellValue();
					}
					catch (Exception e)
					{
						
					}
					ColumnName1 = ColumnName1.toLowerCase();
					dict.put(ColumnName1, ColumnValue1); 
				}
			}
			//if the test case row is not found the print the console.
			else
				System.out.println("TestCase" + GlobalVariables.CurrentTestCase + " row not found in datatable");
			
			//close work book
			wb1.close();
			//close input stream
			fis.close();
		}
		catch(Exception e)
		{
			
		}
	}
	public static String getValue(String ColumnName)
	{
		String ColumnValue = "";
		ColumnName = ColumnName.toLowerCase();
		if (dict.get(ColumnName)!=null)
		{
			ColumnValue = dict.get(ColumnName).toString();
		}
		else
		{
			System.out.println(ColumnName + "does not exist in the testdata sheet");
		}
		return ColumnValue;
	}
	@SuppressWarnings("unchecked")
	public static void putValue(String ColumnName,String ColumnValue)
	{
		ColumnName = ColumnName.toLowerCase();
		if (dict.get(ColumnName)!=null)
		{
			dict.put(ColumnName, ColumnValue);
		}
		else
		{
			System.out.println(ColumnName + "does not exist in the testdata sheet");
		}
	}
	
	@SuppressWarnings("unused")
	public static void exportData()
	{
		try
		{
			String CurrentTestCase = GlobalVariables.CurrentTestCase;
			String ColumnName = "TestCaseName";
			String TestData = "";
			//Access TestData File
			File f1 = new File(GlobalVariables.CurrentDirectory + "\\" + GlobalVariables.testdatapath);
			//Create FileInput Stream object
			FileInputStream fis = new FileInputStream(f1);
			//create work book object
			XSSFWorkbook wb1 = new XSSFWorkbook(fis);
			//create work sheet object
			XSSFSheet ws1 = wb1.getSheet(GlobalVariables.TestDataSheetName);
			//get row count
			int rowcount = ws1.getLastRowNum();
			//using 'foundrow' variable to verify if the testcase row exists
			int foundrow = -1;
			for(int j = 0;j<=rowcount;j++)
			{
				Cell cell = ws1.getRow(j).getCell(0);
				String ExcelTestCaseName = cell.getStringCellValue();
				if(ExcelTestCaseName.toLowerCase().trim().equalsIgnoreCase(GlobalVariables.CurrentTestCase.toLowerCase().trim()))
				{
					foundrow = j;
					break;
				}
			}
			//if testcase row exist then copy values from dict object to excel
			if(foundrow!=-1)
			{
				int colcount = ws1.getRow(0).getLastCellNum();
				for(int i = 1;i<colcount;i++)
				{
					String ColumnName1 = ws1.getRow(0).getCell(i).getStringCellValue();
					ColumnName1 = ColumnName1.toLowerCase();
					String ColumnValue1 = dict.get(ColumnName1).toString();
					Cell cell = ws1.getRow(foundrow).getCell(i);
					if (cell==null)
					{
						cell = ws1.getRow(foundrow).createCell(i);
					}
					cell.setCellValue(ColumnValue1);
				}
			}
			//if testcase row exist report in console
			else
				System.out.println("TestCase" + GlobalVariables.CurrentTestCase + " row not found in datatable");
			//close input stream
			fis.close();
			//open output stream
		    FileOutputStream outFile =new FileOutputStream(f1);
		    //write contents to excel
		    wb1.write(outFile);
		    //close output stream
		    outFile.close();
		    //close work book
		    wb1.close();
		}
		catch(Exception e)
		{
			
		}
	}
}
