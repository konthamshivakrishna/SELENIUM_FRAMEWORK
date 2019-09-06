package test.testcases;


import test.resources.generic.TLogger;
import test.resources.generic.TestData;
import test.resources.generic.WebLib;

public class TC03_AddImmigrationDetailsAndVerify 
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

		status = WebLib.ClickElement("//a[text()='Immigration']");
		if (status)
			TLogger.LogEventWithScreeshot("info", "Clicked on Immigration Details");
		else
			TLogger.LogEventWithScreeshot("fail", "Unable to Click on Immigration Details");

		status = WebLib.Exist("//input[@id='btnAdd']");
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Navigated to Immigration details page");
		else
			TLogger.LogEventWithScreeshot("fail", "Unable to Navigate to Immigration details page");

		String Number = TestData.getValue("Number");
		String IssuedDate = TestData.getValue("IssuedDate");
		String ExpiryDate = TestData.getValue("ExpiryDate");
		String EligibleStatus = TestData.getValue("EligibleStatus");
		String IssuedBy = TestData.getValue("IssuedBy");
		String EligibleReviewDate = TestData.getValue("EligibleReviewDate");
		String Comments = TestData.getValue("Comments");

		WebLib.ClickElement("//input[@id='btnAdd']");
		status = WebLib.SetText("//input[@name='immigration[number]']", Number);
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Able to Enter Number");
		else
			TLogger.LogEventWithScreeshot("fail", "UnAble to Enter Number");
		status = WebLib.SetText("//input[@name='immigration[passport_issue_date]']", IssuedDate);
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Able to Enter IssuedDate");
		else
			TLogger.LogEventWithScreeshot("fail", "UnAble to Enter IssuedDate");
		status = WebLib.SetText("//input[@name='immigration[passport_expire_date]']", ExpiryDate);
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Able to Enter ExpiryDate");
		else
			TLogger.LogEventWithScreeshot("fail", "UnAble to Enter ExpiryDate");
		status = WebLib.SetText("//input[@name='immigration[i9_status]']", EligibleStatus);
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Able to Enter EligibleStatus");
		else
			TLogger.LogEventWithScreeshot("fail", "UnAble to Enter EligibleStatus");
		status = WebLib.ClickElement("//select[@name='immigration[country]']/option[text()='" + IssuedBy + "']");
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Able to Select country");
		else
			TLogger.LogEventWithScreeshot("fail", "UnAble to Select country");
		status = WebLib.SetText("//input[@name='immigration[i9_review_date]']", EligibleReviewDate);
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Able to Enter EligibleReviewDate");
		else
			TLogger.LogEventWithScreeshot("fail", "UnAble to Enter EligibleReviewDate");
		status = WebLib.SetText("//textarea[@name='immigration[comments]']", Comments);
		if (status)
			TLogger.LogEventWithScreeshot("pass", "Able to Enter Comments");
		else
			TLogger.LogEventWithScreeshot("fail", "UnAble to Enter Comments");
		
		status = WebLib.ClickElement("//*[@id='btnSave']");
		WebLib.ClickElement("//*[@id='btnSave']");
		
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
