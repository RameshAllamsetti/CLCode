package com.cloudleaf.webautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TenantAdminPage extends Utility {
	
	TenantAdminPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='fa fa-plus']")
	public WebElement addTenantButton;
	
	@FindBy(id="name")
	public WebElement nameTextField;
	
	@FindBy(xpath="//button[text()='Save']")
	public WebElement saveButton;
	
	@FindBy(xpath="//button[text()='Cancel']")
	public WebElement cancelButton;
	
	

	
	public void addTenat(WebDriver driver,String tenantName) throws InterruptedException
	{
		waitUntilElement(driver, addTenantButton);
		isElementExists(addTenantButton);
		addTenantButton.click();
		waitUntilElement(driver, nameTextField);
		nameTextField.sendKeys(tenantName); 
		saveButton.click();
		
		System.out.println(tenantName + " is  successfully created ");
		//Thread.sleep(3000);
		waitUntilElement(driver, addTenantButton);
	}
	


}
