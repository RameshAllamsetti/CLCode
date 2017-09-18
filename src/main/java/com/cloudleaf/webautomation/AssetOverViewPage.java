package com.cloudleaf.webautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AssetOverViewPage extends Utility {

	WebDriver driver;

	@FindBy(xpath="//a[text()='Tenants']")
	public WebElement tenantAdminLink;
	
	@FindBy(xpath="//div[@id='user_img'][2]")
	public WebElement userImage;

	@FindBy(xpath="//i[@class='fa fa-users fa-lg iconSpace']")
	public WebElement userAdminLink;

	@FindBy(xpath="//i[@class='fa fa-building-o fa-lg iconSpace']")
	public WebElement siteAdminLink;

	@FindBy(xpath="//i[@class='fa fa-leaf fa-lg iconSpace']")
	public WebElement assetAdminLink;

	@FindBy(xpath="//i[@class='fa fa-hdd-o fa-lg iconSpace']")
	public WebElement deviceAdminLink;

	@FindBy(xpath="//i[@class='fa fa-cogs fa-lg iconSpace']")
	public WebElement settingsLink;

	@FindBy(xpath="//i[@class='fa fa-exclamation-triangle fa-lg iconSpace']")
	public WebElement alertAdminLink;

	@FindBy(xpath="//i[@class='fa fa-book fa-lg iconSpace']")
	public WebElement apiGuideLink;

	@FindBy(xpath="//a[@title='Signout']")
	public WebElement signOutLink;



	@FindBy(xpath="//a[text()='Dashboard']")
	public WebElement dashboardLink;

	@FindBy(xpath="//a[text()='Charts']")
	public WebElement chartsLink;

	@FindBy(xpath="//a[text()='Map']")
	public WebElement mapLink;

	@FindBy(xpath="//a[text()='Reports']")
	public WebElement reportsLink;

	@FindBy(name="searchbox")
	public WebElement searchTextField;

	public AssetOverViewPage(WebDriver driver)
	{
		//this.driver = driver;
		PageFactory.initElements(driver, this);
		//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

	}

	public void clickUserImage(WebDriver driver)
	{
			
		Utility.waitUntilElement(driver,userImage);
		
		//Validate that user is successfully logged in
		Assert.assertTrue(userImage.isDisplayed(), "User not logged in successfully");
				
		//isElementExists(userImage);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userImage.click();
		System.out.println("User Image is Clicked");
	}

	public void clickTenantAdmin(WebDriver driver)
	{
		Utility.waitUntilElement(driver, tenantAdminLink);
		isElementExists(tenantAdminLink);
		tenantAdminLink.click();
		System.out.println("Tenant Administration  is Clicked");
	}

	public void clickUserAdmin(WebDriver driver)
	{
		Utility.waitUntilElement(driver, userAdminLink);
		isElementExists(userAdminLink);
		userAdminLink.click();
		System.out.println("User Administration  is Clicked");
	}

	public void clickSiteAdmin(WebDriver driver)
	{
		Utility.waitUntilElement(driver, siteAdminLink);
		isElementExists(siteAdminLink);
		siteAdminLink.click();
		System.out.println("Site Administration  is Clicked");
	}

	public void clickAssetAdmin(WebDriver driver)
	{
		Utility.waitUntilElement(driver, siteAdminLink);
		isElementExists(assetAdminLink);
		assetAdminLink.click();
		System.out.println("Asset Administration  is Clicked");
	}

	public void clickDeviceAdmin(WebDriver driver)
	{
		Utility.waitUntilElement(driver, deviceAdminLink);
		isElementExists(deviceAdminLink);
		deviceAdminLink.click();
		System.out.println("Device Administration  is Clicked");
	}

	public void clickAlertAdmin(WebDriver driver)
	{
		Utility.waitUntilElement(driver, alertAdminLink);
		isElementExists(alertAdminLink);
		alertAdminLink.click();
		System.out.println("Alert Administration  is Clicked");
	}

	public void clickSettings(WebDriver driver)
	{
		Utility.waitUntilElement(driver, settingsLink);
		isElementExists(settingsLink);
		settingsLink.click();
		System.out.println("Settings Link  is Clicked");
	}

	public void clickAPIGuide(WebDriver driver)
	{
		Utility.waitUntilElement(driver, apiGuideLink);
		isElementExists(apiGuideLink);
		apiGuideLink.click();
		System.out.println("API Guide  is Clicked");
	}

	public void clickDashboard(WebDriver driver)
	{
		Utility.waitUntilElement(driver, dashboardLink);
		isElementExists(dashboardLink);
		dashboardLink.click();
		System.out.println("Dashboard Link  is Clicked");
	}

	public void clickCharts(WebDriver driver)
	{
		Utility.waitUntilElement(driver, chartsLink);
		isElementExists(chartsLink);
		chartsLink.click();
		System.out.println("Charts Link  is Clicked");
	}

	public void clickReports(WebDriver driver)
	{
		Utility.waitUntilElement(driver, reportsLink);
		isElementExists(reportsLink);
		reportsLink.click();
		System.out.println("Reports Link  is Clicked");
	}

	public void clickMaps(WebDriver driver)
	{
		Utility.waitUntilElement(driver, mapLink);
		isElementExists(mapLink);
		mapLink.click();
		System.out.println("MAPS Link  is Clicked");
	}

	public void searchItem(String name) throws InterruptedException
	{
		Utility.waitUntilElement(driver, searchTextField);
		isElementExists(searchTextField);
		searchTextField.sendKeys(name);
		Thread.sleep(4000);
		System.out.println("Sarched for String "+ name);
	}

	public void clickSignOut(WebDriver driver)
	{
		Utility.waitUntilElement(driver, signOutLink);
		isElementExists(signOutLink);
		signOutLink.click();
	}
}
