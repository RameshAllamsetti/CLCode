package com.cloudleaf.webautomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CloudLeafFlowByReadExcelData extends Utility

{
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
	
	@DataProvider
	public Object[][] readData() throws IOException, InvalidFormatException
	{
		File inputxl = new File("src//test//resources//TestData//tenantallinfo.xlsx");
		FileInputStream fis = new FileInputStream(inputxl);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet ws =  workbook.getSheetAt(0);
		
		XSSFRow rowsno = ws.getRow(0);
	       int colNum = rowsno.getLastCellNum();
	       System.out.println("Total Number of Columns in the excel is : "+colNum);
	       int rowNum = ws.getLastRowNum()+1;
	       System.out.println("Total Number of Rows in the excel is : "+rowNum);
	       
	   	Object[][] data = new Object[rowNum][colNum];
	       
	   	for(int row=0; row<rowNum;row++)
		{
			for(int column=0;column<ws.getRow(row).getLastCellNum();column++)
			{
				data[row][column] = ws.getRow(row).getCell(column).getStringCellValue();		
			}	
		}

	/*	int rows = ws.getLastRowNum();
		int columns = ws.getRow(rows).getLastCellNum();

		System.out.println("No of Rows in XL : "+rows);
		System.out.println("No of Columns in XL : "+columns);

		Object[][] data = new Object[rows][columns];
	       
	       Object[][] data = new Object[rowNum][colNum];

		for(int row=1; row<=rowNum;row++)
		{
			for(int column=0;column<=ws.getRow(row).getLastCellNum();column++)
			{
				data[row][column] = ws.getRow(row).getCell(column).getStringCellValue();		
			}	
		}*/
		return data;
	}


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
	
	@Test(dataProvider="readData")
	public void cloudleafFlow(String tenantName,String userId,String password,String emailId,String siteName,String address,String cityName,String countryName,String zipCode,String areaName,String ccName,String ccID,String tagName,String tagID,String assetName) throws Exception
	{

		System.out.println(tenantName +"----"+userId+"------"+password+"-------"+siteName+"------"+address+"----"+cityName+"---"+countryName+"----"+zipCode+"-----"+areaName+"----"+ccName+"----"+ccID+"--------"+tagName+"----------"+tagID+"-----"+assetName);

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
		
		//loginPage.login(driver,"superadmin@cloudleaf.io", "RaviTrack123#");
		

		System.out.println("User landed on Login  Page");
		test.log(LogStatus.INFO,"Login Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));
		
		//Clicking on userImage Link
		assetOverViewPage.clickUserImage(driver);
		

		//Clicking on Tenat Admin Link
		assetOverViewPage.clickTenantAdmin(driver);
		test.log(LogStatus.INFO,"Tenant Admin Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));


		//Adding a new Tenant
		tenantAdminPage.addTenat(driver,tenantName);
		//System.out.println(driver.getPageSource());
		Assert.assertTrue(driver.getPageSource().contains(tenantName), "Tenanat not created successfully");
		test.log(LogStatus.INFO,"Tenant Added Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		//Clicking on userImage Link
		assetOverViewPage.clickUserImage(driver);	
		
		
		//Clicking on User Admin Link
		assetOverViewPage.clickUserAdmin(driver);
		test.log(LogStatus.INFO,"User Admin Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		//Select the newly created tenancy
		userAdminPage.selectTeanant(driver,tenantName);
		test.log(LogStatus.INFO,"User Admin Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		
		//Adding a new Normal User
		userAdminPage.addNormalUser(driver,userId, emailId, "Ramesh", password,tenantName);
		Thread.sleep(5000);
		test.log(LogStatus.INFO,"Normal User Added Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));
		Assert.assertTrue(driver.getPageSource().contains(userId), "User not created successfully");

		//Adding an API  User is not required 
		userAdminPage.addAPIlUser(driver,"rallamsetti-api-"+uname, "Ramesh", "Password123$","Ramesh-"+uname);
		test.log(LogStatus.INFO,"API User Added Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		//Logout from the super admin
		assetOverViewPage.clickSignOut(driver);



		//Loggin into the cloudleaf application with the newly created User
		System.out.println("Logging into the cloud leaf application with the newly created User");
		loginPage.login(driver,userId, password);
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
		//siteAdminPage.addSite(driver,"Site - "+uname, "Miyapur", "Hyderabad", "Telangana", "532127", "India", "17.494243", "78.357553");
		siteAdminPage.addSite(driver,siteName, address, cityName, zipCode, countryName);
		test.log(LogStatus.INFO,"AddSite Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));
		Assert.assertTrue(driver.getPageSource().contains(siteName), "Site not created successfully");

		System.out.println("User added a site");

		//Clicking on the newly added site link
		waitForPageLoaded(driver);
		siteAdminPage.clickOnSiteName(driver, siteName);
		test.log(LogStatus.INFO,"Add Site Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		//Clicking on add Area button
		waitForPageLoaded(driver);
		siteAdminPage.clickAddAreaButton(driver);
		System.out.println("User landed on Add Area Page");
		test.log(LogStatus.INFO,"Add Area Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		//Adding a new Area
		waitForPageLoaded(driver);
		siteAdminPage.addArea(driver,areaName, "0");
		test.log(LogStatus.INFO,"Add Area Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		System.out.println("User added an Area");
		Assert.assertTrue(driver.getPageSource().contains(areaName), "Area not created successfully");

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
		deviceAdminPage.addCC(driver,ccName, ccID);
		test.log(LogStatus.INFO,"ADd CC Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));
		Assert.assertTrue(driver.getPageSource().contains(ccName), "CC not created successfully");

		//Clicking on Tags link
		waitForPageLoaded(driver);
		deviceAdminPage.clickTagLink(driver);

		//Clicking on Add Tags Button
		waitForPageLoaded(driver);
		deviceAdminPage.clickAddTagsButton(driver);
		test.log(LogStatus.INFO,"ADd Tag Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));


		//Adding a new Tag
		waitForPageLoaded(driver);
		deviceAdminPage.addTag(driver,tagName, "CLF D2");
		//Capturing the SS throuth Utility class and adding to extent report
		test.log(LogStatus.INFO,"Add Tag Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));
		Assert.assertTrue(driver.getPageSource().contains(tagName), "Sensor not created successfully");

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
		assetAdminPage.addAsset(driver,assetName, assetName, "Asset");
		test.log(LogStatus.INFO,"New Asset Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));
		Assert.assertTrue(driver.getPageSource().contains(assetName), "Asset not created successfully");

		//Select the asset and do the provisioning
		//	assetAdminPage.selectAsset("Asset-"+uname);
		System.out.println("Binding the Asset to the Sensor");
		isElementExists(driver.findElement(By.xpath("//div[@class='ng-binding ng-scope'][text()='"+assetName+"']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='ng-binding ng-scope'][text()='"+assetName+"']")).click();
		assetAdminPage.linkAsset(driver,tagID);
		test.log(LogStatus.INFO,"Asset Provisioned Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));

		
		/*//Clicking on userImage Link to go to Administration
		assetOverViewPage.clickUserImage(driver);

		//Clicking on Alert Admin Link
		waitForPageLoaded(driver);
		assetOverViewPage.clickAlertAdmin(driver);
		System.out.println("User landed on Alert Admin Page");
		test.log(LogStatus.INFO,"Alert Admin Screen",test.addScreenCapture(Utility.captureScreeshot2(driver)));*/



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