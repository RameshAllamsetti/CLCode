package com.cloudleaf.webautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DeviceAdminPage extends Utility {
	
	DeviceAdminPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='fa fa-plus']")
	public WebElement addCCButton;
	
	@FindBy(xpath="//span[@class='fa fa-angle-down']")
	public WebElement actionsButton;
	
	
	@FindBy(xpath="//i[@class='fa fa-link']")
	public WebElement provisionButton;
	
	@FindBy(xpath="//button[@class='btn btn-primary'][//i[@class='fa fa-link']]")
	public WebElement provisionButton2;
	
	
	@FindBy(xpath="//i[@class='fa fa-unlink']")
	public WebElement deprovisionButton;
	
	@FindBy(xpath="//input[@placeholder='Enter Receiver Name']")
	public WebElement ccNameTextField;
	
	@FindBy(xpath="//input[@placeholder='Enter Physical ID']")
	public WebElement physicalIDTextField;
	
	@FindBy(xpath="//select[@data-ng-model='receiver.type']")
	public WebElement receiverTypeDropDown;
	
	@FindBy(xpath="//select[@data-ng-model='receiver.attributes.rssiRangeDisplay']")
	public WebElement rssiRangeDropDown;
	
	@FindBy(xpath="//input[@value='rssiValue']")
	public WebElement rssiValueTextField;
	
	
	@FindBy(xpath="//button[text()='Save']")
	public WebElement ccSaveButton;
	
	@FindBy(xpath="//button[text()='Cancel']")
	public WebElement ccCancelButton;
	
	@FindBy(xpath="//a[text()='Sensors']")
	public WebElement tagsLink;
	
	@FindBy(xpath="//span[@class='fa fa-plus']")
	public WebElement addTagButton;
	
	@FindBy(xpath="//input[@placeholder='Enter Sensor MAC ID']")
	public WebElement tagIDTextField;
	
	@FindBy(xpath="//option[text()='-- select a type --']/..")
	public WebElement tagTypeDropDown;
	
	@FindBy(xpath="//button[text()='Save']")
	public WebElement tagSaveButton;
	
	@FindBy(xpath="//button[text()='Cancel']")
	public WebElement tagCancelButton;
	
	@FindBy(xpath="//input[@id='admin_receiver_value']")
	public WebElement areaNameTextField;
	
	
	public void clickAddCCButton(WebDriver driver)
	{
		waitUntilElement(driver, addCCButton);
		isElementExists(addCCButton);
		addCCButton.click();
		
	}
	
	public void addCC(WebDriver driver,String ccName,String physicalID) throws InterruptedException
	{
		waitUntilElement(driver, ccNameTextField);
		isElementExists(ccNameTextField);
		ccNameTextField.sendKeys(ccName);
		physicalIDTextField.sendKeys(physicalID);
		Select sel = new Select(receiverTypeDropDown);
		//sel.selectByValue("Gateway");
		sel.selectByVisibleText("Gateway");
		ccSaveButton.click();
		Thread.sleep(1000);
		System.out.println(ccName+ " is added successfully");
	}
	
	public void addMCC(WebDriver driver,String mccName,String physicalID,String rssiValue)
	{
		
		waitUntilElement(driver, ccNameTextField);
		isElementExists(ccNameTextField);
		ccNameTextField.sendKeys(mccName);
		physicalIDTextField.sendKeys(physicalID);
		Select selreciver = new Select(receiverTypeDropDown);
		selreciver.selectByValue("Zone Sensor(powered)");
		
		Select selRSSI = new Select(rssiRangeDropDown);
		selRSSI.selectByValue("CUSTOM");
		
		rssiValueTextField.sendKeys(rssiValue);
		
		ccSaveButton.click();
		
		System.out.println(mccName+ " is added successfully");
	}
	
	public void addLM(WebDriver driver,String lmName,String physicalID,String rssiValue)
	{
		waitUntilElement(driver, ccNameTextField);
		isElementExists(ccNameTextField);
		ccNameTextField.sendKeys(lmName);
		physicalIDTextField.sendKeys(physicalID);
		Select selreciver = new Select(receiverTypeDropDown);
		selreciver.selectByValue("Zone Sensor(battery)");
		
		Select selRSSI = new Select(rssiRangeDropDown);
		selRSSI.selectByValue("CUSTOM");
		
		rssiValueTextField.sendKeys(rssiValue);
		
		ccSaveButton.click();
		
		System.out.println(lmName+ " is added successfully");
	}
	
	public void clickTagLink(WebDriver driver)
	{
		waitUntilElement(driver, tagsLink);
		isElementExists(tagsLink);
		tagsLink.click();
	}
	
	public void clickAddTagsButton(WebDriver driver)
	{
		waitUntilElement(driver, addTagButton);
		isElementExists(addTagButton);
		addTagButton.click();
	}
	
	public void addTag(WebDriver driver,String tagID,String tagType)
	{
		waitUntilElement(driver, tagIDTextField);
		isElementExists(tagIDTextField);
		tagIDTextField.sendKeys(tagID);
		Select selectTag = new Select(tagTypeDropDown);
		selectTag.selectByVisibleText(tagType);
		tagSaveButton.click();
		
		System.out.println(tagID+ " is added successfully");
		
	}
	
	public void provisonCCwithArea(WebDriver driver,String areaName)
	{
			waitUntilElement(driver, actionsButton);
			isElementExists(actionsButton);
			actionsButton.click();
			waitUntilElement(driver, provisionButton);
			provisionButton.click();
			waitUntilElement(driver, areaNameTextField);
			areaNameTextField.sendKeys(areaName);
			waitUntilElement(driver, provisionButton2);
			provisionButton2.click();
			
			System.out.println("CC is  successfully Linked with "+ areaName);
		
	}

}
