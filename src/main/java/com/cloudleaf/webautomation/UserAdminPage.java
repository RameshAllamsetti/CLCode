package com.cloudleaf.webautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class UserAdminPage extends Utility {
	
	UserAdminPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//select[@data-ng-model='loggedInUser.effectiveTenantId']")
	public WebElement tenantDropDown;
	
	@FindBy(xpath="//span[@class='fa fa-plus']")
	public WebElement addUserButton;
	
	@FindBy(xpath="//select[@data-ng-model='euser.type']")
	public WebElement typeDropDown;
	
	@FindBy(id="uid")
	public WebElement useridTextField;
	
	@FindBy(id="primaryEmail")
	public WebElement emailTextField;
	
	@FindBy(id="name")
	public WebElement nameTextField;
	
	@FindBy(xpath="//input[@placeholder='Enter Password']")
	public WebElement passwordTextField;
	
	@FindBy(xpath="//input[@placeholder='Re-enter Password']")
	public WebElement reenterPasswordTextField;
	
	@FindBy(xpath="//input[@value='ROLE_ADMIN']")
	public WebElement roleAdminCheckbox;
	
	@FindBy(xpath="//input[@value='ROLE_USER']")
	public WebElement roleUserCheckbox;
	
	@FindBy(xpath="//input[@value='ROLE_RECEIVER']")
	public WebElement roleReceiverCheckbox;
	
	@FindBy(xpath="//label[@class='radio-inline'][1]")
	public WebElement enableRaidoButtonYes;
	
	@FindBy(xpath="//label[@class='radio-inline'][2]")
	public WebElement enableRaidoButtonNo;
	
	@FindBy(xpath="//select[@data-ng-model='euser.tenantId']")
	public WebElement tenantDropDown2;
	
	
	
	@FindBy(xpath="//button[text()='Save']")
	public WebElement saveButton;
	
	@FindBy(xpath="//button[text()='Cancel']")
	public WebElement cancelButton;
	
	public void selectTeanant(WebDriver driver,String tenantName)
	{
		Utility.waitUntilElement(driver, tenantDropDown);
		isElementExists(tenantDropDown);
		Select tenantDD = new Select(tenantDropDown);
		tenantDD.selectByVisibleText(tenantName);
		
	}

	
	public void addNormalUser(WebDriver driver,String userID,String email,String name,String password,String tenantName) throws InterruptedException
	{
		Utility.waitUntilElement(driver, addUserButton);
		isElementExists(addUserButton);
		addUserButton.click();
		
		Utility.waitUntilElement(driver, useridTextField);
		isElementExists(useridTextField);
		useridTextField.sendKeys(userID);
		emailTextField.sendKeys(email);
		nameTextField.sendKeys(name);
		passwordTextField.sendKeys(password);
		reenterPasswordTextField.sendKeys(password);
		roleAdminCheckbox.click();
		
		Select tenantDD = new Select(tenantDropDown2);
		tenantDD.selectByVisibleText(tenantName);
	
		enableRaidoButtonYes.click();
		
		saveButton.click();
		
		
		System.out.println(userID + " is  successfully created ");
	}
	
	public void addAPIlUser(WebDriver driver,String apiuserID,String name,String password,String tenantName) throws InterruptedException
	{
		Utility.waitUntilElement(driver, addUserButton);
		isElementExists(addUserButton);
		addUserButton.click();
		
		isElementExists(useridTextField);
		
		Select userTypeDD = new Select(typeDropDown);
		userTypeDD.selectByVisibleText("API_USER");
		
		useridTextField.sendKeys(apiuserID);
		nameTextField.sendKeys(name);
		passwordTextField.sendKeys(password);
		reenterPasswordTextField.sendKeys(password);
		roleUserCheckbox.click();
		roleReceiverCheckbox.click();
		enableRaidoButtonYes.click();
		
		Select tenantDD = new Select(tenantDropDown2);
		tenantDD.selectByVisibleText(tenantName);
		
		saveButton.click();
		
		
		System.out.println(apiuserID + " is  successfully created ");
	}
	


}
