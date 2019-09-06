package test.testcases;

import test.resources.generic.TLogger;
import test.resources.generic.WebLib;

public class UserLib 
{
	public static boolean Login(String URL,String UID,String Password)
	{
		boolean loginstatus = true;
		boolean status;
		
		status = WebLib.OpenUrl(URL);
		if (status)
			TLogger.LogEventWithScreeshot("info", "Application is Up and Running");
		else
			TLogger.LogEventWithScreeshot("fail", "Unable to Launch HRM Application");
		
		status = WebLib.SetText("//input[@id='txtUsername']", UID);
		if (status)
			TLogger.LogEventWithScreeshot("info", "Entered UserName");
		else
			TLogger.LogEventWithScreeshot("fail", "Unable to Enter UserName");
		
		status = WebLib.SetText("//input[@id='txtPassword']", Password);
		if (status)
			TLogger.LogEventWithScreeshot("info", "Entered Password");
		else
			TLogger.LogEventWithScreeshot("fail", "Unable to Enter Password");

		status = WebLib.ClickElement("//input[@name='Submit']");
		if (status)
			TLogger.LogEventWithScreeshot("info", "Clicked on Login");
		else
			TLogger.LogEvent("fail", "Unable to Click on login");
		
		status = WebLib.Exist("//a[contains(text(),'Welcome')]");
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Login is sucessful");
		else
		{
			TLogger.LogEventWithScreeshot("pass", "Login is not sucessful");
			loginstatus = false;
		}
		return loginstatus;
	}
	
	
	public static boolean Logout()
	{
		boolean Logoutstatus = true;
		boolean status;
		status = WebLib.ClickElement("//a[text()='Logout']");
		if (status)
			TLogger.LogEventWithScreeshot("info", "Clicked on Logout");
		else
		{
			TLogger.LogEventWithScreeshot("fail", "Unable to clickon Clicked on Logout");
			Logoutstatus = false;
		}
		
		status = WebLib.Exist("//input[@id='txtUsername']");
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Logout is successful");
		else
		{
			TLogger.LogEventWithScreeshot("fail", "Logout is Not successful");
			Logoutstatus = false;
		}		
		
		return Logoutstatus;
	}
			
}
