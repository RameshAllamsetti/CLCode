package com.cloudleaf.webautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SiteAdminPage extends Utility {
	
	SiteAdminPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='fa fa-plus']")
	public WebElement addSiteButton;
	
	@FindBy(xpath="//input[@placeholder='Enter site Name']")
	public WebElement siteNameTextField;	
	
	@FindBy(xpath="//input[@placeholder='Enter Address']")
	public WebElement addressTextField;
	
	@FindBy(xpath="//input[@placeholder='Enter City']")
	public WebElement cityTextField;
	
	@FindBy(xpath="//input[@placeholder='Enter State']")
	public WebElement stateTextField;
	
	@FindBy(xpath="//input[@placeholder='Enter Zip']")
	public WebElement zipTextField;
	
	@FindBy(xpath="//input[@placeholder='Enter Country']")
	public WebElement countryTextField;
	
	@FindBy(xpath="//input[@placeholder='Enter Latitude']")
	public WebElement latitudeTextField;
	
	@FindBy(xpath="//input[@placeholder='Enter Longitude']")
	public WebElement LongitudeTextField;
	
	
	@FindBy(xpath="//button[text()='Save']")
	public WebElement saveButton;
	
	@FindBy(xpath="//button[text()='Cancel']")
	public WebElement cancelButton;
	
	@FindBy(xpath="(//div[text()='RameshSite2']/../..)//i[@class='fa fa-angle-double-right']")
	public WebElement doubleArrowLink;
	
	@FindBy(xpath="//span[@class='fa fa-plus']")
	public WebElement addAreaButton;
	
	
	@FindBy(xpath="//input[@placeholder='Enter Area Name']")
	public WebElement areaNameTextField;
	
	@FindBy(xpath="//input[@placeholder='Enter Area Sort Order (0-99)']")
	public WebElement areaSortOrderTextField;
	
	@FindBy(xpath="//button[text()='Save']")
	public WebElement areaSaveButton;
	
	@FindBy(xpath="//button[text()='Cancel']")
	public WebElement areaCancelButton;
	


	public void clickAddSiteButton(WebDriver driver)
	{
		waitUntilElement(driver, addSiteButton);
		isElementExists(addSiteButton);
		addSiteButton.click();
	}
	
	public void clickOnSiteName(WebDriver driver,String siteName)
	{
		//driver.findElement(By.xpath("(//div[text()='"+siteName+"']/../..)//i[@class='fa fa-angle-double-right']")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//a[text()='"+siteName+"']")).click();
		//doubleArrowLink.click();
		System.out.println("User redirected to  Area Page");
	}
	
	public void addSite(WebDriver driver,String siteName,String address,String city,String zip,String country)  
	{
		waitUntilElement(driver, siteNameTextField);
		isElementExists(siteNameTextField);
		siteNameTextField.sendKeys(siteName);
		addressTextField.sendKeys(address);
		cityTextField.sendKeys(city);
		//stateTextField.sendKeys(state);
		zipTextField.sendKeys(zip);
		countryTextField.sendKeys(country);
		//latitudeTextField.sendKeys(latitude);
		//LongitudeTextField.sendKeys(longitude);
		saveButton.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitUntilElement(driver, driver.findElement(By.xpath("//a[text()='"+siteName+"']")));
		System.out.println(siteName+ " Site is created successfully");
	}
	
	public void clickAddAreaButton(WebDriver driver)
	{
		waitUntilElement(driver, addAreaButton);
		isElementExists(addAreaButton);
		addAreaButton.click();
		System.out.println("Add Area button is clicked");
	}
	
	public void addArea(WebDriver driver,String areaName,String areaSortOrder)
	{
		waitUntilElement(driver,areaNameTextField);
		isElementExists(areaNameTextField);
		areaNameTextField.sendKeys(areaName);
		areaSortOrderTextField.sendKeys(areaSortOrder);
		areaSaveButton.click();
		
		System.out.println(areaName + "Area is added Successfully");
	}
	
	
	
}
