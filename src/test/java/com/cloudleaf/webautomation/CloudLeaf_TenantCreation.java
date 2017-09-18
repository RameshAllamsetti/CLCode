package com.cloudleaf.webautomation;

import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class CloudLeaf_TenantCreation extends Utility {

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

	CloudLeafLoginPage loginPage;
	TenantAdminPage tenantAdminPage;
	UserAdminPage userAdminPage;
	AssetOverViewPage assetOverViewPage;
	SiteAdminPage siteAdminPage;
	DeviceAdminPage deviceAdminPage;
	AssetAdminPage assetAdminPage;
	AlertAdminPage alertAdminPage;
	ExtentReports extent;
	ExtentTest test;
	Random rnum;

	String uname = String.format("%04d", new Random().nextInt(10000));
	@BeforeTest
	public void beforeTest()
	{
		System.out.println("USER DIR: "+System.getProperty("user.dir"));
		//System.out.println("TEST METHOD NAME: "+test.getTest().getName());
		log = Logger.getLogger(this.getClass().getName());

		System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		log.info("===============Launched Firefox Browser==========");

		//log.info("Launching Browser");
		driver.manage().window().maximize();
		log.info("Maximized  Browser");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		log.info("Initiated Implicit Wait Browser");

		driver.manage().deleteAllCookies();
		driver.get("https://dev.cloudleaf.io");

		/*//Getting the browser Name and Version
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

*/
	}


	@Test(priority=1)
	public void createTenanat() throws IOException, InterruptedException
	{
		
	
		log = Logger.getLogger(this.getClass().getName());

		//Creating page object reference
		tenantAdminPage = new TenantAdminPage(driver);
		userAdminPage = new UserAdminPage(driver);
		loginPage = new CloudLeafLoginPage(driver);
		assetOverViewPage = new AssetOverViewPage(driver);
		/*siteAdminPage = new SiteAdminPage(driver);
		deviceAdminPage = new DeviceAdminPage(driver);
		assetAdminPage = new AssetAdminPage(driver);
		alertAdminPage = new AlertAdminPage(driver);*/
		// creates a toggle for the given test, adds all log events under it 
	//	test = extent.startTest("CloudLeafLogin");
		System.out.println("USER DIR: "+System.getProperty("user.dir"));
		
	

		System.out.println(uname);

		//Loggin into the cloudleaf application as SuperAdmin
		System.out.println("Logging into the cloud leaf application");
		//loginPage.login(driver,"superadmin@cloudleaf.io", "$LeAdEry0s");
		loginPage.login(driver,"superadmin@cloudleaf.io", "RaviTrack123#");


		System.out.println("User landed on Login  Page");
		//test.log(LogStatus.INFO,"Login Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		//Clicking on userImage Link
		assetOverViewPage.clickUserImage(driver);


		//Clicking on Tenat Admin Link
		assetOverViewPage.clickTenantAdmin(driver);
		//test.log(LogStatus.INFO,"Tenant Admin Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));


		//Adding a new Tenant
		tenantAdminPage.addTenat(driver,"Ramesh-"+uname);
		//System.out.println(driver.getPageSource());
		Assert.assertTrue(driver.getPageSource().contains("Ramesh-"+uname), "Tenanat not created successfully");
		//test.log(LogStatus.INFO,"Tenant Added Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

	}
	
	@Test(priority=2)
	public void addUser() throws IOException, InterruptedException
	{
		
		userAdminPage = new UserAdminPage(driver);
		assetOverViewPage = new AssetOverViewPage(driver);
		
		//Clicking on userImage Link
		assetOverViewPage.clickUserImage(driver);	


		//Clicking on User Admin Link
		assetOverViewPage.clickUserAdmin(driver);
		//test.log(LogStatus.INFO,"User Admin Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		//Select the newly created tenancy
		userAdminPage.selectTeanant(driver,"Ramesh-"+uname);
	//	test.log(LogStatus.INFO,"User Admin Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));


		//Adding a new Normal User
		userAdminPage.addNormalUser(driver,"rallamsetti-"+uname, "rallamsetti@cloudleaf.io", "Ramesh", "Password123$","Ramesh-"+uname);
		Thread.sleep(5000);
		//test.log(LogStatus.INFO,"Normal User Added Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));
		Assert.assertTrue(driver.getPageSource().contains("rallamsetti-"+uname), "User not created successfully");

		/*//Adding an API  User is not required 
		userAdminPage.addAPIlUser(driver,"rallamsetti-api-"+uname, "Ramesh", "Password123$","Ramesh-"+uname);
		test.log(LogStatus.INFO,"API User Added Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));
		 */
		//Logout from the super admin
		assetOverViewPage.clickSignOut(driver);

	}


	@AfterTest
	public void closeBroswer()
	{	
		driver.quit();
	}
}
