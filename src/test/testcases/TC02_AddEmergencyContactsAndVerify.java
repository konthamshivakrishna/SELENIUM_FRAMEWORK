package test.testcases;


import test.resources.generic.TLogger;
import test.resources.generic.TestData;
import test.resources.generic.WebLib;

public class TC02_AddEmergencyContactsAndVerify 
{
	public static void testdefinition()
	{
		
		String URL = TestData.getValue("URL");
		String UID = TestData.getValue("UID");
		String Password = TestData.getValue("Password");
		Boolean status;
		
		UserLib.Login(URL, UID, Password);
		
		
		status = WebLib.ClickElement("//b[text()='My Info']");
		if (status)
			TLogger.LogEventWithScreeshot("info", "Clicked on My Info button");
		else
			TLogger.LogEventWithScreeshot("fail", "Unable to Click on My Info button");

		status = WebLib.ClickElement("//a[text()='Emergency Contacts']");
		if (status)
			TLogger.LogEventWithScreeshot("info", "Clicked on Emergenecy Contact Details");
		else
			TLogger.LogEventWithScreeshot("fail", "Unable to Click on Emergenecy Contact Details");

		status = WebLib.Exist("//input[@id='btnAddContact']");
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Navigated to Emergenecy contact details page");
		else
			TLogger.LogEventWithScreeshot("fail", "Unable to Navigate to Emergenecy contact details page");
	
		
		String Name = TestData.getValue("Name");
		String RelationShip = TestData.getValue("RelationShip");
		String HomePhone = TestData.getValue("HomePhone");
		String MobilePhone = TestData.getValue("MobilePhone");
		String WorkPhone = TestData.getValue("WorkPhone");
		
		status = WebLib.ClickElement("//input[@id='btnAddContact']");
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Able to click on 'AddContact' button");
		else
			TLogger.LogEventWithScreeshot("fail", "UnAble to click on 'AddContact' button");
		
		status = WebLib.SetText("//input[@name='emgcontacts[name]']", Name);
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Able to Enter Name");
		else
			TLogger.LogEventWithScreeshot("fail", "UnAble to Enter Name");
		status = WebLib.SetText("//input[@name='emgcontacts[relationship]']", RelationShip);
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Able to Enter RelationShip");
		else
			TLogger.LogEventWithScreeshot("fail", "UnAble to Enter RelationShip");
		status = WebLib.SetText("//input[@name='emgcontacts[mobilePhone]']", HomePhone);
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Able to Enter HomePhone");
		else
			TLogger.LogEventWithScreeshot("fail", "UnAble to Enter HomePhone");
		status = WebLib.SetText("//input[@name='emgcontacts[homePhone]']", MobilePhone);
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Able to Enter MobilePhone");
		else
			TLogger.LogEventWithScreeshot("fail", "UnAble to Enter MobilePhone");
		status = WebLib.SetText("//input[@name='emgcontacts[workPhone]']", WorkPhone);
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Able to Enter WorkPhone");
		else
			TLogger.LogEventWithScreeshot("fail", "UnAble to Enter WorkPhone");
		status = WebLib.ClickElement("//input[@name='btnSaveEContact']");
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Able to click on SaveEContact");
		else
			TLogger.LogEventWithScreeshot("fail", "UnAble to click on SaveEContact");
		
		WebLib.ClickElement("//a[contains(text(),'Welcome')]");
		status = WebLib.ClickElement("//a[text()='Logout']");
		if (status)
			TLogger.LogEventWithScreeshot("info", "Clicked on Logout");
		else
			TLogger.LogEventWithScreeshot("fail", "Unable to clickon Clicked on Logout");
		
		status = WebLib.Exist("//input[@id='txtUsername']");
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Logout is successful");
		else
			TLogger.LogEventWithScreeshot("fail", "Logout is Not successful");
	}
}
