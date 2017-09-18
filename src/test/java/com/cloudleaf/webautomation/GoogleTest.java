package com.cloudleaf.webautomation;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GoogleTest {
	
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeTest
	public void extentSetup()
	{
				//Getting the browser Name and Version
				/*Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
				String browserName = caps.getBrowserName();
				String browserVersion = caps.getVersion();
				System.out.println("Browser Name"+browserName);
				System.out.println("Browser Version"+browserVersion);
				*/
				//Extent Report Configuration where your report will be saved if you pass true then existing report will be overwritten, if it's false it adds to the existing report
				//extent = new ExtentReports(System.getProperty("user.dir")+"/ExtentReports/ExtentReport---"+new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date())+".html", true);
				extent = new ExtentReports(System.getProperty("user.dir")+"/ExtentReports/ExtentReport.html", true);		
				extent.addSystemInfo("Environment","Test")
				.addSystemInfo("User Name","Ramesh Allamsetti");
				
				//Not manadatory if you give the blow line then it will read the vales what we give in the config other wise default values will come
				//extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
			
	}
	
	@Test
	public void openinFirefox() throws InterruptedException, IOException
	{
		/*ExtentReports extent;
		ExtentTest test;
		extent = new ExtentReports(System.getProperty("user.dir")+"/ExtentReports/ExtentReport.html", true);*/
		test = extent.startTest("openinFirefox");
		//System.out.println("Running the test method"+ test.getTest().getName());
		WebDriver driver ;
		
		System.setProperty("webdriver.gecko.driver", "Drivers//geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		
		driver.get("http://google.co.in");
		System.out.println("Opened the firefox Driver");
		System.out.println(driver.getTitle());
		Thread.sleep(5000);
		//Utility.captureScreenShot(driver, "firefoxreport");
		test.log(LogStatus.INFO,"Google Home FF",test.addScreenCapture(Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver)));
		
		
		driver.findElement(By.id("lst-ib")).sendKeys("selenium");
		driver.findElement(By.name("btnG")).click();
		Thread.sleep(4000);
		//Utility.captureScreenShot(driver, "firefoxreport");
		test.log(LogStatus.INFO,"Google Search FF",test.addScreenCapture(Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver)));
		System.out.println(driver.getTitle());
		
		System.out.println("Closing the firefox driver");
		driver.quit();
		/*extent.endTest(test);
		extent.flush();*/
		
	}
	
	@Test
	public void openinChrome() throws InterruptedException, IOException
	{
		/*ExtentReports extent;
		ExtentTest test;
		extent = new ExtentReports(System.getProperty("user.dir")+"/ExtentReports/ExtentReport.html", true);*/
		test = extent.startTest("openinChrome");
		//System.out.println("Running the test method"+ test.getTest().getName());
		WebDriver driver ;
		System.setProperty("webdriver.chrome.driver", "Drivers//chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("Opened the Chrome Driver");
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		driver.get("http://google.co.in");
		System.out.println(driver.getTitle());
		Thread.sleep(6000);
		
		//Utility.captureScreenShot(driver, "chromereport");
		test.log(LogStatus.INFO,"Google Home Chorme",test.addScreenCapture(Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver)));
		
		driver.findElement(By.id("lst-ib")).sendKeys("selenium");
		driver.findElement(By.name("btnG")).click();
		Thread.sleep(4000);
		//Utility.captureScreenShot(driver, "chromereport");
		test.log(LogStatus.INFO,"Google Search Chrome",test.addScreenCapture(Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver)));
		System.out.println(driver.getTitle());
		
		System.out.println("Closing the chrome driver");
		driver.quit();
		
		/*extent.endTest(test);
		extent.flush();*/
	}
	
	@Test
	public void openinIE() throws InterruptedException, IOException
	{
		/*ExtentReports extent;
		ExtentTest test;
		extent = new ExtentReports(System.getProperty("user.dir")+"/ExtentReports/ExtentReport.html", true);*/
		test = extent.startTest("openinIE");
		System.out.println("Running the test method"+ test.getTest().getName());
		WebDriver driver ;
		System.setProperty("webdriver.ie.driver", "Drivers//IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		System.out.println("Opened the IE Driver");
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		driver.get("http://google.co.in");
		System.out.println(driver.getTitle());
		Thread.sleep(6000);
		//Utility.captureScreenShot(driver, "iereport");
		test.log(LogStatus.INFO,"Google Home IE",test.addScreenCapture(Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver)));
		
		driver.findElement(By.id("lst-ib")).sendKeys("selenium");
		driver.findElement(By.name("btnG")).click();
		Thread.sleep(4000);
		//Utility.captureScreenShot(driver, "iereport");
		test.log(LogStatus.INFO,"Google Search IE",test.addScreenCapture(Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver)));
		System.out.println(driver.getTitle());
		
		System.out.println("Closing the ie driver");
		driver.quit();
		
	/*	extent.endTest(test);
		extent.flush();*/
		
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException
	{
		/*WebDriver driver;
		String str1 =Utility.captureScreeshot(new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date()),driver);
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(LogStatus.INFO,"Failed ScreenShot",test.addScreenCapture(str1));
			test.log(LogStatus.FAIL, result.getThrowable());
		}
		*/
		//Ending the test
		extent.endTest(test);
	}
	
	@AfterTest
	public void closeBroswer()
	{	//flush writes everything to the log file
		extent.flush();
	}

}
