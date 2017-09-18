package com.cloudleaf.webautomation;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class CLLoginTest {
	
	WebDriver driver;
	String inputData;
	Logger log ;
	public Date cdate; 
	public String ctime;
	public String str;
	public static ExtentReports extReport;
	public static ExtentTest extLogger;
	public boolean flag;
	DesiredCapabilities dc;
	
	
	Utility util;
	CloudLeafLoginPage loginPage;
	AssetOverViewPage assetOverViewPage;
	SiteAdminPage siteAdminPage;
	DeviceAdminPage deviceAdminPage;
	//@BeforeTest
	//@Parameters({"browserType","GridConcept","nodeURL"})
	//public void beforeTest(String browserType,String GridConcept,String nodeURL) throws FileNotFoundException, IOException
	@BeforeTest
	public void beforeTest()
	{
		log = Logger.getLogger(this.getClass().getName());

		System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		log.info("===============Launched Firefox Browser==========");
		
		/*log.info("===================Started Test Execution==========================");

		if(!GridConcept.equalsIgnoreCase("Yes"))
		{

			if(browserType.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
				driver = new FirefoxDriver();
				log.info("===============Launched Firefox Browser==========");
			}
			else if(browserType.equalsIgnoreCase("iexplore"))
			{
				System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				log.info("===============Launched Internet Explorer Browser==========");
			}

			else if(browserType.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				
				driver = new ChromeDriver();
				log.info("===============Launched Chrome  Browser==========");
				
			}
		}
		else
		{
			log.info("===============Executing with Grid Concept==========");
			if(browserType.equalsIgnoreCase("firefox"))
			{
				dc = DesiredCapabilities.firefox();
				dc.setPlatform(Platform.VISTA);
				dc.setBrowserName("firefox");
			}
			else if(browserType.equalsIgnoreCase("iexplore"))
			{
				dc = DesiredCapabilities.internetExplorer();
				dc.setPlatform(Platform.VISTA);
				dc.setBrowserName("internet explorer");
			}
			else if(browserType.equalsIgnoreCase("chrome"))
			{
				ChromeOptions options = new ChromeOptions();
				options.addArguments("user-data-dir=C:/Users/harish.raju/AppData/Local/Google/Chrome/User Data");

				dc = DesiredCapabilities.chrome();
				dc.setPlatform(Platform.VISTA);
				dc.setBrowserName("chrome");
				dc.setCapability(options.CAPABILITY,options);
			}

			try {
				driver = new RemoteWebDriver(new URL(nodeURL),dc);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				log.info("===============Node Url Erro=========="+e.getMessage());
			}

		}
	*/
		log.info("Launching Browser");
		driver.manage().window().maximize();
		log.info("Maximized  Browser");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		log.info("Initiated Implicit Wait Browser");

		driver.manage().deleteAllCookies();
		driver.get("https://test.cloudleaf.io");
	
}
	
	@Test
	public void CloudLeafLogin() throws IOException, InterruptedException
	{
		/*System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		log.info("===============Launched Firefox Browser==========");
		
		driver.get("https://test.cloudleaf.io");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		*/
		
		//Utility.openBrowser("firefox", "https://www.test.cloudleaf.io");
		
		//Creating page object reference
		loginPage = new CloudLeafLoginPage(driver);
		assetOverViewPage = new AssetOverViewPage(driver);
		siteAdminPage = new SiteAdminPage(driver);
		
		//Loggin into the cloudleaf application
		System.out.println("Logging into the cloud leaf application");
		loginPage.login(driver,"rallamsetti@cloudleaf.io", "Easwar123$");
		
		Utility.captureScreenShot(driver, driver.getTitle());
		
		log.info("Logged into Cloud Leaf applicaton");
		
		//Clicking on userImage Link
		assetOverViewPage.clickUserImage(driver);
		
		//Clicking on Site Admin Link
		assetOverViewPage.clickSiteAdmin(driver);
		Thread.sleep(6000);
		System.out.println("User landed on Site Admin Page");
		Utility.captureScreenShot(driver, driver.getTitle());
		
		//Clicking on Add Site Button
		siteAdminPage.clickAddSiteButton(driver);
		Thread.sleep(6000);
		System.out.println("User landed on Add Site Page");
		Utility.captureScreenShot(driver, driver.getTitle());
		
		//Adding a new Site
		siteAdminPage.addSite(driver,"RameshSite8", "Miyapur", "Telangana", "532127", "India");
		Thread.sleep(9000);
		Utility.captureScreenShot(driver, driver.getTitle());
		
		System.out.println("User added a site");
		
		//Clicking on double right link for the site
		siteAdminPage.clickOnSiteName(driver, "RameshSite8");
		Thread.sleep(9000);
		Utility.captureScreenShot(driver, driver.getTitle());
		
		//Clicking on add Area button
		siteAdminPage.clickAddAreaButton(driver);
		Thread.sleep(5000);
		System.out.println("User landed on Add Area Page");
		Utility.captureScreenShot(driver, driver.getTitle());
		
		//Adding a new Area
		siteAdminPage.addArea(driver,"TestArea", "0");
		Thread.sleep(9000);
		Utility.captureScreenShot(driver, driver.getTitle());
		
		System.out.println("User added an Area");
			
	}
	
	@Test
	public void sencodTest()
	{
		assertEquals(true, true);
		System.out.println("Second Test Passed");
		
	}
	
	
	
	@AfterTest
	public void closeBroswer()
	{
		driver.quit();
	}
}
