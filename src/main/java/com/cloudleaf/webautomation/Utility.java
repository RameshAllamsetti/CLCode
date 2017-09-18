package com.cloudleaf.webautomation;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class Utility {
	static By obj;
	static WebDriver driver;
	//DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");
	// Date date = new Date();
	public String currentTime = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss").format(new Date());
	
	
	
	public static void waitForElement(WebDriver driver,String xpath)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 100);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		}
		catch(Exception e)
		{
			
			System.out.println(xpath +"not found in the page");
			e.getStackTrace();
		}
		System.out.println(xpath + "element found");
		//click the element
		driver.findElement(By.xpath(xpath)).click();
		System.out.println(xpath + "element clicked");
	}

	public static void captureScreenShot(WebDriver driver,String fileName) throws IOException
	{
		TakesScreenshot ts =(TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(source, new  File("ScreenShots/"+fileName+"----"+new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date())+".png"));
		System.out.println("Screen Shot taken "+fileName);
	}

	public static String captureScreeshot(String fileName,WebDriver driver) throws IOException
	{
		//String dest = "ScreenShots/"+fileName+"----"+new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date())+".png";
		String dest = "C:\\Users\\rallamsetti\\workspace\\com.cloudleaf.webautomation\\ScreenShots\\"+fileName+".png";
		//String dest = "\\ScreenShots\\"+fileName+".png";
		File destination = new File(dest);
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(source,destination);

		// dest = "/ScreenShots/"+filename+".jpg";
		return dest;

	}
	
	public static String captureScreeshot2(WebDriver driver) throws IOException
	{
		//String dest = "ScreenShots/"+fileName+"----"+new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date())+".png";
		//String dest = "C:\\Users\\rallamsetti\\workspace\\com.cloudleaf.webautomation\\ScreenShots\\"+fileName+".png";
		String dest = System.getProperty("user.dir")+"\\ScreenShots\\"+new SimpleDateFormat("MMM dd yyyy HH-mm-ss").format(new Date())+".png";
		File destination = new File(dest);
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(source,destination);

		// dest = "/ScreenShots/"+filename+".jpg";
		return dest;

	}

	public static void isElementExists(WebElement element)
	{
		if(element.isDisplayed())
		{
			System.out.println(element + " is Displayed in the page");
		}
		else
		{
			System.out.println(element +" not Displayed in the page");
		}
	}

	public static WebDriver openBrowser(String browserName,String url)
	{

		System.out.println("Opening the browser");
		
		if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		else if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if(browserName.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", "./Drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get(url);

		return driver;
	}

	public static void softAssertValidateText(String actual,String expected)
	{
		SoftAssert sassert = new SoftAssert();
		sassert.assertEquals(actual, expected);
		System.out.println("Assertion Passed");

	}

	public static void ValidateText(String actual,String expected)
	{

		Assert.assertEquals(actual, expected);
		System.out.println("Assertion Passed");

	}
	
	public static ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
					.equals("complete");
		}
	};
	
	public static ExpectedCondition<Boolean> xhrLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			return ((JavascriptExecutor) driver).executeScript("return return xhr.readyState;").toString()
					.equals("4");
		}
	};
	
	public static ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			try {
				return (Boolean) ((JavascriptExecutor) driver)
						.executeScript("return window.jQuery != undefined || jQuery.active == 0");
			} catch (Exception e) {
				return true;
			}
		}
	};
	public boolean waitForPageLoaded(WebDriver driver) {
		boolean flag = true;		
		try{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			flag = wait.until(xhrLoad) && wait.until(jQueryLoad) && wait.until(jsLoad);
			return flag;
		}catch(Exception e){
			return false;
		}
	}

	public static void waitUntilElement(WebDriver driver,WebElement element)
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 100);
		//wait.until(ExpectedConditions.presenceOfElementLocated(obj));
		wait.until(ExpectedConditions.visibilityOf(element));
		//wait.until(ExpectedConditions.textToBePresentInElement(element, text))

	}
	
	public static void waitUntilTextPresent(WebDriver driver,WebElement element,String text)
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 100);
		//wait.until(ExpectedConditions.presenceOfElementLocated(obj));
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));

	}
	

	public static void waitUntilElement(WebDriver driver,String locator,String locatorValue)
	{
		By obj = byObjectReturn(locator,locatorValue);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		//wait.until(ExpectedConditions.presenceOfElementLocated(obj));
		wait.until(ExpectedConditions.visibilityOfElementLocated(obj));

	}

	public static By byObjectReturn(String locator,String locatorvalue )
	{

		if(locator.equals("id"))
		{
			obj = By.id(locatorvalue);
		}
		else if(locator.equals("name"))
		{
			obj = By.name(locatorvalue);
		}
		else if(locator.equals("xpath"))
		{
			obj = By.xpath(locatorvalue);
		}
		else if(locator.equals("linkText"))
		{
			obj = By.linkText(locatorvalue);
		}

		else if(locator.equals("className"))
		{
			obj = By.className(locatorvalue);
		}
		else if(locator.equals("partialLinkText"))
		{
			obj = By.partialLinkText(locatorvalue);
		}
		else if(locator.equals("tagName"))
		{
			obj = By.tagName(locatorvalue);
		}

		return obj;
	}




}
