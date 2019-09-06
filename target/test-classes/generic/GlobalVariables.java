package test.resources.generic;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class GlobalVariables 
{
	public interface report {

	}
	public static String RunManagerName = "RunManager.xlsx";
	public static String MainMethodName  = "testdefinition";
	public static String testdatapath = "TestData\\TestData.xlsx";
	public static String TestDataSheetName = "TestData";
	public static String CurrentTestCase = "";
	public static String ScreenshotsFolderPath = "";
	public static String ResultsFolderPath;
	public static String CurrentResultsFolderPath;
	public static String ResultFilePath;
	public static String CurrentDirectory;
	public static String TestDataPath;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static WebDriver driver;
	public static String ClassPath;
}