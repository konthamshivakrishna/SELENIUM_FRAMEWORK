package test.resources.generic;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

public class TLogger 
{
	public static void LogEvent(String Status,String Description)
	{
		WebLib.wait(0.5);
		if(Status.equalsIgnoreCase("pass"))
		{
			GlobalVariables.logger.log(LogStatus.PASS,  Description);
			System.out.println("\t " +"StepStatus:" + Status + "; StepDesciption:" + Description);
		}
		else if(Status.equalsIgnoreCase("fail"))
		{
			GlobalVariables.logger.log(LogStatus.FAIL, Description);
			System.out.println("\t " +"StepStatus:" + Status + "; StepDesciption:" + Description);
		}
		else if (Status.equalsIgnoreCase("warning"))
		{
			GlobalVariables.logger.log(LogStatus.WARNING, Description);
			System.out.println("\t " +"StepStatus:" + Status + "; StepDesciption:" + Description);
		}
		else if(Status.equalsIgnoreCase("info"))
		{
			GlobalVariables.logger.log(LogStatus.INFO, Description);
			System.out.println("\t " +"StepStatus:" + Status + "; StepDesciption:" + Description);
		}
	}
	public static void LogEventWithScreeshot(String Status,String Description)
	{
		WebLib.wait(0.5);
		String ScreenShotPath = getscreenshot(GlobalVariables.driver);
		String scn = GlobalVariables.logger.addScreenCapture(ScreenShotPath);
		if(Status.equalsIgnoreCase("pass"))
		{
			GlobalVariables.logger.log(LogStatus.PASS, Description + scn);
			System.out.println("\t " +"StepStatus:" + Status + "; StepDesciption:" + Description);
		}
		else if(Status.equalsIgnoreCase("fail"))
		{
			GlobalVariables.logger.log(LogStatus.FAIL, Description + scn);
			System.out.println("\t " +"StepStatus:" + Status + "; StepDesciption:" + Description);
		}
		else if (Status.equalsIgnoreCase("warning"))
		{
			GlobalVariables.logger.log(LogStatus.WARNING, Description + scn);
			System.out.println("\t " +"StepStatus:" + Status + "; StepDesciption:" + Description);
		}
		else if(Status.equalsIgnoreCase("info"))
		{
			GlobalVariables.logger.log(LogStatus.INFO, Description + scn);
			System.out.println("\t " +"StepStatus:" + Status + "; StepDesciption:" + Description);
		}
	}
	public static String getscreenshot(WebDriver driver)
	  {
		  try
		  {
			String ScreenshotName;
			DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance();
			String DateTimeStamp = dateTimeInstance.format(Calendar.getInstance().getTime());
			DateTimeStamp = DateTimeStamp.replace(",", "");
			DateTimeStamp = DateTimeStamp.replace(" ", "_");
			DateTimeStamp = DateTimeStamp.replace(":", "_");
			ScreenshotName =  GlobalVariables.CurrentTestCase + "_"+ DateTimeStamp;
			TakesScreenshot ts =(TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String Dest = GlobalVariables.ScreenshotsFolderPath + ScreenshotName + ".png";
			File destination = new File(Dest);
			FileUtils.copyFile(source, destination);
			return Dest;
		  }
		  catch(Exception e)
		  {
			  return e.getMessage();
		  }
	  }
}