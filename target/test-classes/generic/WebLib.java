package test.resources.generic;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebLib 
{
	public static Boolean SetText(String ObjectXpath,String Value)
    {
        Boolean stepStatus = true;
        try
        {
        	GlobalVariables.driver.findElement(By.xpath(ObjectXpath)).click();
        	GlobalVariables.driver.findElement(By.xpath(ObjectXpath)).clear();
            GlobalVariables.driver.findElement(By.xpath(ObjectXpath)).sendKeys(Value);
            wait(0.5);
            GlobalVariables.driver.findElement(By.xpath(ObjectXpath)).sendKeys(Keys.ESCAPE);
        }
        catch (Exception e)
        {
            stepStatus = false;
        }
        return stepStatus;
    }
	public static Boolean Exist(String ObjectXpath)
    {
        Boolean stepStatus = true;
        try
        {
        	GlobalVariables.driver.findElement(By.xpath(ObjectXpath));
        }
        catch (Exception e)
        {
            stepStatus = false;
        }
        return stepStatus;
    }
    public static Boolean ClickElement(String ObjectXpath)
    {
        Boolean stepStatus = true;
        try
        {
        	GlobalVariables.driver.findElement(By.xpath(ObjectXpath)).click();
        }
        catch (Exception e)
        {
            stepStatus = false;
        }
        return stepStatus;
    }
    @SuppressWarnings("deprecation")
	public static WebDriver launchBrowser(String BrowserName)
	{
		WebDriver Tempdriver = null;
		switch(BrowserName.toLowerCase())
		{
			case "firefox":
			{
				Tempdriver = new FirefoxDriver();
				break;
			}
			case "internetexplorer":
			{
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				System.setProperty("webdriver.ie.driver", GlobalVariables.CurrentDirectory + "\\" +"JarFiles\\BrowserServers\\IEDriverServer.exe");
				Tempdriver = new InternetExplorerDriver(capabilities);
				break;
			}
			case "chrome":
			{
				System.setProperty("webdriver.chrome.driver",GlobalVariables.CurrentDirectory + "\\" +"JarFiles\\BrowserServers\\chromedriver.exe");
				Tempdriver = new ChromeDriver();
				break;
			}
			default:
			{
				System.out.println("please Select the correct browser");
			}
		}
		return Tempdriver;
	}
    public static Boolean OpenUrl(String URL)
    {
    	Boolean stepStatus = true;
    	try
    	{
    		GlobalVariables.driver.get(URL);
    		GlobalVariables.driver.manage().window().maximize();
    	}
    	catch(Exception e)
    	{
    		stepStatus = false;
    	}
    	String CurrentUrl = GlobalVariables.driver.getCurrentUrl();
    	if (!CurrentUrl.contains(URL))
    	{
    		stepStatus = false;
    	}
    	return stepStatus;
    }
    public static void wait(double d)
    {
    	try
    	{
    		Thread.sleep((long) (d*1000));
    	}
    	catch(Exception e)
    	{
    		
    	}
    }
}
