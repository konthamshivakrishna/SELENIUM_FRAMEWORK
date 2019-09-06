package test.testcases;

import test.resources.business.ElementLocators.login;
import test.resources.generic.TLogger;
import test.resources.generic.TestData;
import test.resources.generic.WebLib;

public class TC01_VerifyLoginLinear 
{
	public static void testdefinition()
	{	
		//Read data from test data file
		String url = TestData.getValue("URL");
		String uid = TestData.getValue("UID");
		String pwd = TestData.getValue("Password");
		
		boolean retval;
		TLogger.LogEvent("info", "Start:TC01_Verify Login Linear");
		
		//Launch login page
		retval = WebLib.OpenUrl(url);
		if(retval)
			TLogger.LogEvent("info", "HRM Login page opened");
		else
			TLogger.LogEvent("fail", "HRM Login page not opened");
	
		//Enter user name
		retval = WebLib.SetText(login.userName, uid);
		if(retval)
			TLogger.LogEvent("info", "User Name entred successfully");
		else
			TLogger.LogEvent("fail", "User name not entered");
		
		retval = WebLib.SetText(login.password, pwd);
		if(retval)
			TLogger.LogEvent("info", "Password entred successfully");
		else
			TLogger.LogEvent("fail", "Password not entered");
		
		//Click Login button
		retval = WebLib.ClickElement(login.Login);
		if(retval)
			TLogger.LogEvent("info", "Login button clicked successfully");
		else
			TLogger.LogEvent("fail", "Login button not clicked");
		
		TLogger.LogEvent("info", "End:TC01_Verify Login");
		
	}
}
