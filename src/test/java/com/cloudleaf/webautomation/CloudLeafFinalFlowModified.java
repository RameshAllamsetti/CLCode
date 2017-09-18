package com.cloudleaf.webautomation;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CloudLeafFinalFlowModified extends Utility {

	WebDriver driver;
	String inputData;
	Logger log ;
	public Date cdate; 
	public String ctime;
	public String str;
	public String browserName;
	public String url;
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

	@BeforeClass
	public void beforeTest() throws Exception
	{
		browserName = PropertyFileUtils.getPropValuesFromConfig(PropertyFileUtils.WEB_PROPERTIES_FILE, "browserName").trim();
		url = PropertyFileUtils.getPropValuesFromConfig(PropertyFileUtils.WEB_PROPERTIES_FILE, "appurl").trim();

		System.out.println("Browser Name : "+browserName);

		System.out.println("APP URL : "+url);

		System.out.println("USER DIR: "+System.getProperty("user.dir"));
		//System.out.println("TEST METHOD NAME: "+test.getTest().getName());
		log = Logger.getLogger(this.getClass().getName());

		driver = openBrowser(browserName, url);

		//driver = openBrowser("chrome", "https://test.cloudleaf.io");


		//Getting the browser Name and Version
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		String browserName1 = caps.getBrowserName();
		String browserVersion = caps.getVersion();
		System.out.println("Browser Name"+browserName1);
		System.out.println("Browser Version"+browserVersion);


		//Extent Report Configuration where your report will be saved if you pass true then existing report will be overwritten, if it's false it adds to the existing report
		extent = new ExtentReports(System.getProperty("user.dir")+"/ExtentReports/CloudLeafExtentReport.html", true);
		extent.addSystemInfo("Environment","Test")
		.addSystemInfo("User Name","Ramesh Allamsetti")
		.addSystemInfo("Browser Name",browserName1)
		.addSystemInfo("Browser Version",browserVersion);

		//Not manadatory if you give the blow line then it will read the vales what we give in the config other wise default values will come
		extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));


	}

	@Test
	public void CloudLeafCompleteFlow() throws IOException, InterruptedException
	{
		// creates a toggle for the given test, adds all log events under it 
		test = extent.startTest("CloudLeafLogin");

		//Creating page object reference
		tenantAdminPage = new TenantAdminPage(driver);
		userAdminPage = new UserAdminPage(driver);
		loginPage = new CloudLeafLoginPage(driver);
		assetOverViewPage = new AssetOverViewPage(driver);
		siteAdminPage = new SiteAdminPage(driver);
		deviceAdminPage = new DeviceAdminPage(driver);
		assetAdminPage = new AssetAdminPage(driver);
		alertAdminPage = new AlertAdminPage(driver);
		//rnum = new Random();

		String uname = String.format("%04d", new Random().nextInt(10000));
		System.out.println(uname);

		//Loggin into the cloudleaf application as SuperAdmin
		System.out.println("Logging into the cloud leaf application");
		loginPage.login(driver,"superadmin@cloudleaf.io", "$LeAdEry0s");


		System.out.println("User landed on Login  Page");
		test.log(LogStatus.INFO,"Login Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		//Clicking on userImage Link
		assetOverViewPage.clickUserImage(driver);


		//Clicking on Tenat Admin Link
		assetOverViewPage.clickTenantAdmin(driver);
		test.log(LogStatus.INFO,"Tenant Admin Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));


		//Adding a new Tenant
		tenantAdminPage.addTenat(driver,"RameshTenant-"+uname);
		//System.out.println(driver.getPageSource());
		Assert.assertTrue(driver.getPageSource().contains("RameshTenant-"+uname), "Tenanat not created successfully");
		test.log(LogStatus.INFO,"Tenant Added Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		//Clicking on userImage Link
		assetOverViewPage.clickUserImage(driver);	


		//Clicking on User Admin Link
		assetOverViewPage.clickUserAdmin(driver);
		test.log(LogStatus.INFO,"User Admin Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		//Select the newly created tenancy
		userAdminPage.selectTeanant(driver,"RameshTenant-"+uname);
		test.log(LogStatus.INFO,"User Admin Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));


		//Adding a new Normal User
		userAdminPage.addNormalUser(driver,"rallamsetti-"+uname, "rallamsetti@cloudleaf.io", "Ramesh", "Password123$","RameshTenant-"+uname);
		Thread.sleep(2000);
		test.log(LogStatus.INFO,"Normal User Added Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));
		Assert.assertTrue(driver.getPageSource().contains("rallamsetti-"+uname), "User not created successfully");

		/*//Adding an API  User is not required 
				userAdminPage.addAPIlUser(driver,"rallamsetti-api-"+uname, "Ramesh", "Password123$","Ramesh-"+uname);
				test.log(LogStatus.INFO,"API User Added Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));
		 */
		//Logout from the super admin
		assetOverViewPage.clickSignOut(driver);



		//Loggin into the cloudleaf application with the newly created User
		System.out.println("Logging into the cloud leaf application with the newly created User");
		loginPage.login(driver,"rallamsetti-"+uname, "Password123$");
		//loginPage.login(driver,"rallamsetti@cloudleaf.io", "Easwar123$");
		System.out.println("User landed on Login  Page");
		test.log(LogStatus.INFO,"Login Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));


		log.info("Logged into Cloud Leaf applicaton");

		//Clicking on userImage Link
		waitForPageLoaded(driver);
		assetOverViewPage.clickUserImage(driver);

		//Clicking on Site Admin Link
		assetOverViewPage.clickSiteAdmin(driver);
		System.out.println("User landed on Site Admin Page");
		test.log(LogStatus.INFO,"Login Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		//Clicking on Add Site Button
		waitForPageLoaded(driver);
		siteAdminPage.clickAddSiteButton(driver);
		System.out.println("User landed on Add Site Page");
		test.log(LogStatus.INFO,"ADd Site Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		//Adding a new Site
		siteAdminPage.addSite(driver,"Site - "+uname, "Miyapur", "Telangana", "532127", "India");
		test.log(LogStatus.INFO,"AddSite Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Site - "+uname), "Site not created successfully");

		System.out.println("User added a site");

		//Clicking on the newly added site link
		waitForPageLoaded(driver);
		siteAdminPage.clickOnSiteName(driver, "Site - "+uname);
		test.log(LogStatus.INFO,"Add Site Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		//Clicking on add Area button
		waitForPageLoaded(driver);
		siteAdminPage.clickAddAreaButton(driver);
		System.out.println("User landed on Add Area Page");
		test.log(LogStatus.INFO,"Add Area Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		//Adding a new Area
		waitForPageLoaded(driver);
		siteAdminPage.addArea(driver,"Area - "+uname, "0");
		test.log(LogStatus.INFO,"Add Area Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		System.out.println("User added an Area");
		Assert.assertTrue(driver.getPageSource().contains("Area - "+uname), "Area not created successfully");

		//Clicking on userImage Link to go to Administration
		assetOverViewPage.clickUserImage(driver);
		waitForPageLoaded(driver);

		//Clicking on Device Admin link
		assetOverViewPage.clickDeviceAdmin(driver);
		System.out.println("User landed on Device Admin Page");
		test.log(LogStatus.INFO,"Device ADmin Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));


		//Clicking on Add CC Button
		waitForPageLoaded(driver);
		deviceAdminPage.clickAddCCButton(driver);
		System.out.println("User landed on Add CC Page");
		test.log(LogStatus.INFO,"Add CC Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		//Adding a new Cloud Connector 
		deviceAdminPage.addCC(driver,"CC-"+uname, "b0d5cc78"+uname);
		test.log(LogStatus.INFO,"ADd CC Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));
		Assert.assertTrue(driver.getPageSource().contains("CC-"+uname), "CC not created successfully");

		//Provision the newly added CC with an area
		driver.findElement(By.xpath("//td[text()='b0d5cc78"+uname+"']")).click();
		deviceAdminPage.provisonCCwithArea(driver, "Area - "+uname);
		test.log(LogStatus.INFO,"CC linked with Area Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		//Clicking on Tags link
		waitForPageLoaded(driver);
		deviceAdminPage.clickTagLink(driver);

		//Clicking on Add Tags Button
		waitForPageLoaded(driver);
		deviceAdminPage.clickAddTagsButton(driver);
		test.log(LogStatus.INFO,"ADd Tag Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));


		//Adding a new Tag
		waitForPageLoaded(driver);
		deviceAdminPage.addTag(driver,"C1F00030"+uname, "CLF D2");
		//Capturing the SS throuth Utility class and adding to extent report
		test.log(LogStatus.INFO,"Add Tag Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));
		Assert.assertTrue(driver.getPageSource().contains("C1F00030"+uname), "Sensor not created successfully");

		//Clicking on userImage Link to go to Administration
		assetOverViewPage.clickUserImage(driver);

		//Clicking on Asset Admin link
		waitForPageLoaded(driver);
		assetOverViewPage.clickAssetAdmin(driver);
		System.out.println("User landed on Asset Admin Page");
		test.log(LogStatus.INFO,"Asset Admin Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));


		//Clicking on Asset Categories link
		waitForPageLoaded(driver);
		assetAdminPage.clickAssetCategoryLink(driver);

		//Clicking on Add Category Button
		waitForPageLoaded(driver);
		assetAdminPage.clickAddCategoriesButton(driver);
		test.log(LogStatus.INFO,"ADd Categrory Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));


		//Adding a new Category
		waitForPageLoaded(driver);
		assetAdminPage.addCategory(driver,"Category-"+uname, "Category"+uname, "0");
		//Capturing the SS throuth Utility class and adding to extent report
		test.log(LogStatus.INFO,"ADd Categrory Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));
		Assert.assertTrue(driver.getPageSource().contains("Category-"+uname), "Category not created successfully");


		//Click on Assets link
		waitForPageLoaded(driver);
		assetAdminPage.clickAssetsLink(driver);

		//Selecting the Category
		assetAdminPage.selectCategory(driver,"Category-"+uname);
		driver.findElement(By.xpath("//a[text()='"+"Category-"+uname+"']")).click();
		System.out.println("Category-"+uname + "Selected");

		//Clicking on Add Asset Button
		assetAdminPage.clickAddAssetButton(driver);
		System.out.println("User landed on Add Asset Page");
		test.log(LogStatus.INFO,"Add Asset Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		//Adding a new Asset
		assetAdminPage.addAsset(driver,"Asset-"+uname, "Asset-"+uname, "Category-"+uname);
		test.log(LogStatus.INFO,"New Asset Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));
		Assert.assertTrue(driver.getPageSource().contains("Asset-"+uname), "Asset not created successfully");

		//Select the asset and do the provisioning
		//	assetAdminPage.selectAsset("Asset-"+uname);
		System.out.println("Binding the Asset to the Sensor");
		isElementExists(driver.findElement(By.xpath("//div[@class='ng-binding ng-scope'][text()='"+"Asset-"+uname+"']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='ng-binding ng-scope'][text()='"+"Asset-"+uname+"']")).click();
		assetAdminPage.linkAsset(driver,"C1F00030"+uname);
		test.log(LogStatus.INFO,"Asset Provisioned Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));


		//Clicking on userImage Link to go to Administration
		assetOverViewPage.clickUserImage(driver);

		//Clicking on Alert Admin Link
		waitForPageLoaded(driver);
		assetOverViewPage.clickAlertAdmin(driver);
		System.out.println("User landed on Alert Admin Page");
		test.log(LogStatus.INFO,"Alert Admin Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));


		//Create Alert
		waitForPageLoaded(driver);
		alertAdminPage.createAssetEntryWebAlert(driver,"Site - "+uname, "Area - "+uname, "Critical", "Asset Entered  Zone");
		test.log(LogStatus.INFO,"Alert Created Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));


		//Create Subscription
		waitForPageLoaded(driver);
		alertAdminPage.createSubscription(driver);
		test.log(LogStatus.INFO,"Subscription Created Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));




	}

	//This method will be executed at the end of all tests
	@AfterMethod
	public void getResult(ITestResult result) throws IOException
	{
		String str1 =Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver);

		System.out.println("REPORT URL LINK: "+System.getProperty("user.dir")+"\\ExtentReports\\CloudLeafExtentReport.html");
		System.out.println("----------------------------------------------------------------------------------------------------------");
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
