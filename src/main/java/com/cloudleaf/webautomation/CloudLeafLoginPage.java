package com.cloudleaf.webautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CloudLeafLoginPage extends Utility {
	
	//WebDriver driver;
	
	@FindBy(id="username")
	public WebElement emailTextField;
	
	@FindBy(id="password")
	public WebElement passwordTextField;
	
	@FindBy(id="login")
	public WebElement loginButton;
	
	public CloudLeafLoginPage(WebDriver driver)
	{
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void login(WebDriver driver,String username,String password)
	{
		Utility.waitUntilElement(driver, emailTextField);
		emailTextField.sendKeys(username);
		passwordTextField.sendKeys(password);
		loginButton.click();
		

		
	}

}
