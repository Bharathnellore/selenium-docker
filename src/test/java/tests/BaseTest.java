package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	protected WebDriver driver;
	@BeforeTest
	public void setupDriver(ITestContext ctx) throws MalformedURLException {
		//BROWSER=>chrome/firefox
		//HUB_HOST=>localhost/10.0.1.3/hostname
		
		String host="localhost";
		MutableCapabilities mc;
		
		if(System.getProperty("BROWSER")!=null&&
				System.getProperty("BROWSER").equalsIgnoreCase("firefox"))
		{
			
			mc=new FirefoxOptions();
		}
		else 
		{
			mc=new ChromeOptions();
		}
		if(System.getProperty("HUB_HOST") != null){

			host = System.getProperty("HUB_HOST");
		}
			String testName = ctx.getCurrentXmlTest().getName();

			String completeUrl = "http://" + host + ":4444/wd/hub";
			mc.setCapability("name", testName); 
			
			this.driver = new RemoteWebDriver(new URL(completeUrl), mc);
		

	}
				
	 
	
	
	@AfterTest
	public void quitBrowser() {
		this.driver.quit();
	}
		
}
