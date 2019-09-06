package test.testcases;

import test.resources.generic.TLogger;
import test.resources.generic.TestData;

public class TC01_VerifyLogin 
{
	public static void testdefinition()
	{
		String URL = TestData.getValue("URL");
		String UID = TestData.getValue("UID");
		String Password = TestData.getValue("Password");
		
		
		Boolean status;
		//Step 1: Login to CMS
		status = UserLib.Login(URL, UID, Password);		
		if (status)
			TLogger.LogEventWithScreeshot("pass", "HRM login successfull");
		else
			TLogger.LogEventWithScreeshot("fail", "HRM login unsuccessfull");
				
		
		
		//Step 5: Logout from HRM
		status = UserLib.Logout();
		if (status)
			TLogger.LogEventWithScreeshot("pass", "HRM Logout successfull");
		else
			TLogger.LogEventWithScreeshot("fail", "HRM Logout unsuccessfull");
		
	}
}
