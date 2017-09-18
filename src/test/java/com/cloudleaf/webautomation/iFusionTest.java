package com.cloudleaf.webautomation;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class iFusionTest extends Utility {

	WebDriver driver;
	Logger log;

	@Test
	public void ifusionFlow() throws InterruptedException
	{
		log = Logger.getLogger(this.getClass().getName());
		
		//Handling SSL Certificates in firefox
		ProfilesIni prof = new ProfilesIni();				
		FirefoxProfile ffProfile= prof.getProfile ("myProfile");
		ffProfile.setAcceptUntrustedCertificates(true);
		ffProfile.setAssumeUntrustedCertificateIssuer(false);

		System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
		driver = new FirefoxDriver(ffProfile);

	/*	//Handling SSL Certificates in Chrome
		DesiredCapabilities handlSSLErr = DesiredCapabilities.chrome();  
		handlSSLErr.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
		
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver(handlSSLErr);*/

		log.info("===============Launched Firefox Browser==========");

		//log.info("Launching Browser");
		driver.manage().window().maximize();
		log.info("Maximized  Browser");

		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		log.info("Initiated Implicit Wait Browser");

		driver.manage().deleteAllCookies();
		driver.get("https://172.17.17.61:8444/iscope/");
		
		String uname = String.format("%04d", new Random().nextInt(10000));

		//Logging into the application as admin
		waitUntilElement(driver, driver.findElement(By.id("email")));

		driver.findElement(By.id("email")).sendKeys("admin");
		driver.findElement(By.id("pwd")).sendKeys("admin");

		Select roleDD = new Select(driver.findElement(By.id("sel1")));
		roleDD.selectByVisibleText("Admin");

		driver.findElement(By.xpath("//button[text()='Login']")).click();

		//Click in Source
		waitUntilElement(driver, driver.findElement(By.xpath("//span[text()='Source']")));
		driver.findElement(By.xpath("//span[text()='Source']")).click();

		//Click on Add Soruce
		waitUntilElement(driver, driver.findElement(By.xpath("//button[@aria-label='addSourceBtn']")));
		Thread.sleep(4000);
		driver.findElement(By.xpath("//button[@aria-label='addSourceBtn']")).click();

		//create a source for HIVE
		waitUntilElement(driver, driver.findElement(By.name("element")));
		driver.findElement(By.name("element")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='HIVE']")).click();

		Thread.sleep(2000);
		driver.findElement(By.name("dsnName")).sendKeys("DSName"+uname);

		driver.findElement(By.xpath("//input[@ng-model='dsn.dsn_description']")).sendKeys("DDText"+uname);

		driver.findElement(By.xpath("//table[@class='table table-hover']/tbody/tr[6]/td[2]/custom-dropdown/div/input")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='INDIRECTORY']")).click();


		driver.findElement(By.xpath("//table[@class='table table-hover']/tbody/tr[12]/td[2]/input")).sendKeys("thrift://qauscentos2.localdomain:9083");

		//driver.findElement(By.xpath("//table[@class='table table-hover']/tbody/tr[13]/td[2]/input")).sendKeys("Hadoop Version");

		driver.findElement(By.xpath("//table[@class='table table-hover']/tbody/tr[14]/td[2]/input")).sendKeys("qauscentos2.localdomain");

		//driver.findElement(By.xpath("//table[@class='table table-hover']/tbody/tr[15]/td[2]/input")).sendKeys("10000");

		driver.findElement(By.xpath("//table[@class='table table-hover']/tbody/tr[16]/td[2]/input")).sendKeys("ifusiondb");

		driver.findElement(By.xpath("//table[@class='table table-hover']/tbody/tr[18]/td[2]/input")).sendKeys("hive");

		driver.findElement(By.xpath("//table[@class='table table-hover']/tbody/tr[19]/td[2]/input")).sendKeys("hive");

		driver.findElement(By.xpath("//a[text()='Save']")).click();
		
		Thread.sleep(6000);

		//Validate that the new source got created.

		
		//Now LogOut
		driver.findElement(By.xpath("//span[text()='admin']")).click();
		waitUntilElement(driver, driver.findElement(By.xpath("//div[text()='Logout']")));
		driver.findElement(By.xpath("//div[text()='Logout']")).click();

		

		driver.quit();
	}

}
