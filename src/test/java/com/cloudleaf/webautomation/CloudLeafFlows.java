package com.cloudleaf.webautomation;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CloudLeafFlows {
	
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
	AssetAdminPage assetAdminPage;
	
	//Creating page object reference
	/*CloudLeafLoginPage loginPage = new CloudLeafLoginPage(driver);
	AssetOverViewPage	assetOverViewPage = new AssetOverViewPage(driver);
	SiteAdminPage	siteAdminPage = new SiteAdminPage(driver);
	DeviceAdminPage	deviceAdminPage = new DeviceAdminPage(driver);
	AssetAdminPage assetAdminPage = new AssetAdminPage(driver);*/
	
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeTest
	public void beforeTest()
	{
		System.out.println("USER DIR: "+System.getProperty("user.dir"));
		//System.out.println("TEST METHOD NAME: "+test.getTest().getName());
		log = Logger.getLogger(this.getClass().getName());

		System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		log.info("===============Launched Firefox Browser==========");
		
		log.info("Launching Browser");
		driver.manage().window().maximize();
		log.info("Maximized  Browser");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		log.info("Initiated Implicit Wait Browser");

		driver.manage().deleteAllCookies();
		driver.get("https://test.cloudleaf.io");
		
		//Getting the browser Name and Version
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = caps.getBrowserName();
		String browserVersion = caps.getVersion();
		System.out.println("Browser Name"+browserName);
		System.out.println("Browser Version"+browserVersion);
		
		//Extent Report Configuration where your report will be saved if you pass true then existing report will be overwritten, if it's false it adds to the existing report
		extent = new ExtentReports(System.getProperty("user.dir")+"/ExtentReports/CloudLeafExtentReport.html", true);
		extent.addSystemInfo("Environment","Test")
		.addSystemInfo("User Name","Ramesh Allamsetti")
		.addSystemInfo("Browser Name",browserName)
		.addSystemInfo("Browser Version",browserVersion);
		
		//Not manadatory if you give the blow line then it will read the vales what we give in the config other wise default values will come
		extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
	
	
}
	
	@Test
	public void addSiteAreaFlow() throws IOException, InterruptedException
	{
		loginPage = new CloudLeafLoginPage(driver);
		assetOverViewPage = new AssetOverViewPage(driver);
		siteAdminPage = new SiteAdminPage(driver);
		
		
		// creates a toggle for the given test, adds all log events under it 
		test = extent.startTest("addSiteAreaFlow");
		
		//Loggin into the cloudleaf application
		System.out.println("Logging into the cloud leaf application");
		loginPage.login(driver,"rallamsetti@cloudleaf.io", "Easwar123$");
		Thread.sleep(9000);
		System.out.println("User landed on Login  Page");
		test.log(LogStatus.INFO,"Login Screen",test.addScreenCapture(Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver)));
		
		
		log.info("Logged into Cloud Leaf applicaton");
		
		//Clicking on userImage Link
		assetOverViewPage.clickUserImage(driver);
		
		//Clicking on Site Admin Link
		assetOverViewPage.clickSiteAdmin(driver);
		Thread.sleep(3000);
		System.out.println("User landed on Site Admin Page");
		Utility.captureScreenShot(driver, driver.getTitle());
		
		//Clicking on Add Site Button
		siteAdminPage.clickAddSiteButton(driver);
		Thread.sleep(3000);
		System.out.println("User landed on Add Site Page");
		Utility.captureScreenShot(driver, driver.getTitle());
		
		//Adding a new Site
		//siteAdminPage.addSite(driver,"RameshSite9", "Miyapur", "Hyderabad", "Telangana", "532127", "India", "17.494243", "78.357553");
		siteAdminPage.addSite(driver,"Site -1 ", "Miyapur", "Telangana", "532127", "India");
		Thread.sleep(3000);
		Utility.captureScreenShot(driver, driver.getTitle());
		
		System.out.println("User added a site");
		
		//Clicking on double right link for the site
		//siteAdminPage.clickDoubleRight(driver,"RameshSite9");
		Thread.sleep(9000);
		Utility.captureScreenShot(driver, driver.getTitle());
		
		//Clicking on add Area button
		siteAdminPage.clickAddAreaButton(driver);
		Thread.sleep(5000);
		System.out.println("User landed on Add Area Page");
		Utility.captureScreenShot(driver, driver.getTitle());
		
		//Adding a new Area
		siteAdminPage.addArea(driver,"TestArea", "0");
		Thread.sleep(3000);
		test.log(LogStatus.INFO,"Area Added ",test.addScreenCapture(Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver)));
			
		System.out.println("User added an Area");
	
		
	}
	
	@Test
	public void addCCandTagFlow() throws InterruptedException, IOException
	{
		loginPage = new CloudLeafLoginPage(driver);
		assetOverViewPage = new AssetOverViewPage(driver);
		deviceAdminPage = new DeviceAdminPage(driver);
		
		
			// creates a toggle for the given test, adds all log events under it 
				test = extent.startTest("addCCandTagFlow");
				
				//Loggin into the cloudleaf application
				System.out.println("Logging into the cloud leaf application");
				loginPage.login(driver,"rallamsetti@cloudleaf.io", "Easwar123$");
				Thread.sleep(3000);
				System.out.println("User landed on Login  Page");
				test.log(LogStatus.INFO,"Login Screen",test.addScreenCapture(Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver)));
				
				
				log.info("Logged into Cloud Leaf applicaton");
				
				//Clicking on userImage Link
				assetOverViewPage.clickUserImage(driver);
				
				//Clicking on Add CC Button
				deviceAdminPage.clickAddCCButton(driver);
				Thread.sleep(3000);
				System.out.println("User landed on Add CC Page");
				test.log(LogStatus.INFO,"Add CC",test.addScreenCapture(Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver)));
				
				
				//Adding a new Cloud Connector 
				deviceAdminPage.addCC(driver,"RameshautoCC1", "123456789012");
				Thread.sleep(3000);
				test.log(LogStatus.INFO,"CC Added",test.addScreenCapture(Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver)));
				
				
				//Clicking on Tags link
				deviceAdminPage.clickTagLink(driver);
				
				//Clicking on Add Tags Button
				deviceAdminPage.clickAddTagsButton(driver);
				Thread.sleep(3000);
				test.log(LogStatus.INFO,"Add Tag Page",test.addScreenCapture(Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver)));
				
				
				//Adding a new Tag
				deviceAdminPage.addTag(driver,"C1F000300226", "CLF D1");
				Thread.sleep(3000);
				//Capturing the SS throuth Utility class and adding to extent report
				test.log(LogStatus.INFO,"Tag Addedded",test.addScreenCapture(Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver)));
				
			
	}
	
	@Test
	public void addAssetandCategoryFlow() throws InterruptedException, IOException
	{
		loginPage = new CloudLeafLoginPage(driver);
		assetAdminPage = new AssetAdminPage(driver);
		assetOverViewPage = new AssetOverViewPage(driver);
		// creates a toggle for the given test, adds all log events under it 
		test = extent.startTest("addAssetandCategoryFlow");
		
		//Loggin into the cloudleaf application
		System.out.println("Logging into the cloud leaf application");
		loginPage.login(driver,"rallamsetti@cloudleaf.io", "Easwar123$");
		Thread.sleep(3000);
		System.out.println("User landed on Login  Page");
		test.log(LogStatus.INFO,"Login Screen",test.addScreenCapture(Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver)));
		
		
		log.info("Logged into Cloud Leaf applicaton");
		
		//Clicking on userImage Link
		assetOverViewPage.clickUserImage(driver);
		
		
		//Clicking on Add Asset Button
		assetAdminPage.clickAddAssetButton(driver);
		Thread.sleep(3000);
		System.out.println("User landed on Add Asset Page");
		test.log(LogStatus.INFO,"Add Asset",test.addScreenCapture(Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver)));
		
		
		//Adding a new Asset 
		assetAdminPage.addAsset(driver,"RameshAsset001", "RameshAsset001", "Laptop");
		Thread.sleep(3000);
		test.log(LogStatus.INFO,"Asset Added",test.addScreenCapture(Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver)));
		
		
		//Clicking on Categoires link
		assetAdminPage.clickAssetCategoryLink(driver);
		
		//Clicking on Add Category Button
		assetAdminPage.clickAddCategoriesButton(driver);
		Thread.sleep(3000);
		test.log(LogStatus.INFO,"Add Category Page",test.addScreenCapture(Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver)));
		
		
		//Adding a new Category
		deviceAdminPage.addTag(driver,"C1F000300226", "CLF D1");
		assetAdminPage.addCategory(driver,"NewCatgory", "NewCatgory", "0");
		Thread.sleep(3000);
		//Capturing the SS throuth Utility class and adding to extent report
		test.log(LogStatus.INFO,"Asset Category Addedded",test.addScreenCapture(Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver)));
		
		
		
	}
	
	//This method will be executed at the end of all tests
	@AfterMethod
	public void getResult(ITestResult result) throws IOException
	{
		String str1 =Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver);
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(LogStatus.INFO,"Failed ScreenShot",test.addScreenCapture(str1));
			test.log(LogStatus.FAIL, result.getThrowable());
		}
		
		//Ending the test
		extent.endTest(test);
	}
	
	
	@AfterTest
	public void closeBroswer()
	{	//flush writes everything to the log file
		extent.flush();
		//closes all the open browsers
		driver.quit();
	}
}
