package com.cloudleaf.webautomation;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;


public class GenerateExtentReport {

	ExtentReports extent;
	ExtentTest test;
	WebDriver driver ;
	
	@BeforeTest
	public void startReport()
	{
		System.setProperty("webdriver.gecko.driver", "Drivers//geckodriver.exe");
		driver = new FirefoxDriver();
		//Getting the browser Name
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = caps.getBrowserName();
		String browserVersion = caps.getVersion();
		System.out.println("Browser Name"+browserName);
		System.out.println("Browser Version"+browserVersion);
		
		//Extent Report Configuration where your report will be saved if you pass true then existing report will be overwritten, if it's false it adds to the existing report
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/MyExtentReport.html", false);
		extent.addSystemInfo("Environment","Test")
		.addSystemInfo("User Name","Ramesh Allamsetti")
		.addSystemInfo("Browser Name",browserName)
		.addSystemInfo("Browser Version",browserVersion);
		
		//Not manadatory if you give the blow line then it will read the vales what we give in the config other wise default values will come
		//extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
	
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
	}
	
	@Test
	public void googleTest() throws InterruptedException, IOException
	{
		test = extent.startTest("googleTest");
		//Assert.assertTrue(true);
		
		System.out.println("Running the test method"+ Logger.getLogger(this.getClass().getName()));
	
		
		driver.get("http://google.co.in");
		System.out.println("Opened the firefox Driver");
		System.out.println(driver.getTitle());
		Thread.sleep(5000);
		//Utility.captureScreenShot(driver, "firefoxreport");
		String str1 =Utility.captureScreeshot("Ramesh"+String.valueOf(new Date().getTime()),driver);
		//String img=test.addScreenCapture(str1);
		//Assert.assertTrue(true);
		test.log(LogStatus.INFO,"Capturing ScreenShot",test.addScreenCapture(str1));
		
		driver.findElement(By.id("lst-ib")).sendKeys("selenium");
		driver.findElement(By.name("btnG")).click();
		Thread.sleep(4000);
		Utility.captureScreenShot(driver, "firefoxreport");
		System.out.println(driver.getTitle());
	
		String str =Utility.captureScreeshot("Ramesh"+String.valueOf(new Date().getTime()),driver);
		test.log(LogStatus.INFO,"Capturing ScreenShot",test.addScreenCapture(str));
		//test.log(LogStatus.PASS, "Test Passed");
	}
	
	@Test
	public void seleniumTest() throws IOException, InterruptedException
	{
		test = extent.startTest("seleniumTest");
		driver.get("http://www.seleniumhq.org/");
		System.out.println("Opened the firefox Driver");
		System.out.println(driver.getTitle());
		Thread.sleep(5000);
		Utility.captureScreenShot(driver, "firefoxreport");
		String str =Utility.captureScreeshot("Ramesh"+String.valueOf(new Date().getTime()),driver);
		test.log(LogStatus.INFO,"Capturing ScreenShot",test.addScreenCapture(str));
		//Assert.assertTrue(false);
		driver.findElement(By.id("q")).sendKeys("selenium");
		driver.findElement(By.id("submit")).click();
		Thread.sleep(4000);
		Utility.captureScreenShot(driver, "firefoxreport");
		String str2 =Utility.captureScreeshot("Ramesh"+String.valueOf(new Date().getTime()),driver);
		test.log(LogStatus.INFO,"Capturing ScreenShot",test.addScreenCapture(str2));
		
		System.out.println(driver.getTitle());
		Assert.assertTrue(false);
		//test.log(LogStatus.PASS, "Test Passed");
		//Assert.assertTrue(false);
		//test.log(LogStatus.FAIL, "Test Failed");
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException
	{
		String str1 =Utility.captureScreeshot("Ramesh"+String.valueOf(new Date().getTime()),driver);
		//String img=test.addScreenCapture(str1);
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(LogStatus.INFO,"Capturing ScreenShot",test.addScreenCapture(str1));
			test.log(LogStatus.FAIL, result.getThrowable());
		}
		extent.endTest(test);
	}
	
	@AfterTest
	public void endReport()
	{
		extent.flush();
		driver.quit();
	}
	
}
