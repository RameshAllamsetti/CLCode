package com.cloudleaf.webautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AlertAdminPage extends Utility {

	AlertAdminPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//span[@class='fa fa-plus']")
	public WebElement addRecipeButton;

	@FindBy(xpath="//button[text()='Location']")
	public WebElement locationButton;

	@FindBy(xpath="//button[text()='Low inventory']")
	public WebElement lowInventoryButton;

	@FindBy(xpath="//button[text()='High inventory']")
	public WebElement highInventoryButton;

	@FindBy(xpath="//button[text()='Asset enters an area or site (New!)']")
	public WebElement assetEntersAreaButton;

	@FindBy(xpath="//button[text()='Asset exits an area or site']")
	public WebElement assetExitsAreaButton;


	@FindBy(id="formly_1_select_toSite_0")
	public WebElement toSiteDropDown;

	@FindBy(id="formly_1_select_toArea_1")
	public WebElement toAreaDropDown;

	@FindBy(xpath="//button[text()='Next']")
	public WebElement nextButton;

	@FindBy(xpath="//button[text()='Previous']")
	public WebElement previousButton;

	@FindBy(xpath="//button[text()='Notify Via. Web']")
	public WebElement notifyViaWebButton;

	@FindBy(xpath="//button[text()='Change Asset State']")
	public WebElement chaneAssetStateButton;

	@FindBy(xpath="//button[text()='Send Email']")
	public WebElement sendEmailButton;


	@FindBy(id="formly_4_input_message_0")
	public WebElement messageTextField;

	@FindBy(xpath="//select[@ng-model='model[options.key]']")
	public WebElement notificationLevelDropDown;

	@FindBy(xpath="//input[@placeholder='Please enter the name']")
	public WebElement nameTextField;

	@FindBy(xpath="//button[text()='Save']")
	public WebElement saveButton;

	@FindBy(xpath="//button[text()='Cancel']")
	public WebElement cancelButton;

	@FindBy(xpath="//a[text()='Recipes']")
	public WebElement recipesLink;

	@FindBy(xpath="//a[text()='Subscriptions']")
	public WebElement subscriptionsLink;

	@FindBy(xpath="//a[text()='Trigger Templates']")
	public WebElement triggerTemplatesLink;

	@FindBy(xpath="//a[text()='Action Templates']")
	public WebElement actionTemplatesLink;

	@FindBy(xpath="//span[@class='fa fa-plus']")
	public WebElement addSubscriptionButton;

	@FindBy(xpath="//select[@data-ng-model='sub.categoryId']")
	public WebElement categoryDropDown;

	@FindBy(xpath="//select[@data-ng-model='sub.notifTypeName']")
	public WebElement topicDropDown;

	@FindBy(xpath="//select[@data-ng-model='sub.levelName']")
	public WebElement levelDropDown;
	
	@FindBy(xpath="//button[text()='Presence & Movement']")
	public WebElement presenceAndMovementButton;
		
	@FindBy(xpath="//button[text()='Inventory Thresholds']")
	public WebElement inventoryThresholdsButton;
	
	@FindBy(xpath="//button[text()='Dwelltime Thresholds']")
	public WebElement dwelltimeThresholdsButton;
	
	@FindBy(xpath="//button[text()='Asset State Change']")
	public WebElement assetStateChangeButton;
	
	@FindBy(xpath="//button[text()='Asset Entry']")
	public WebElement assetEntryButton;
	
	@FindBy(xpath="//button[text()='Asset Exit']")
	public WebElement assetExitButton;
	
	@FindBy(xpath="//button[text()='Asset Move and Enter']")
	public WebElement assetMoveAndEnterButton;
	
	@FindBy(xpath="//button[text()='Asset Move and Visit']")
	public WebElement assetMoveAndVisitButton;
	
	
	public void clickAddRecipe()
	{
		isElementExists(addRecipeButton);	
		addRecipeButton.click();

	}

	public void createAssetEntryWebAlert(WebDriver driver,String siteName,String areaName,String notificationLevel,String alertName)
	{
		
		waitUntilElement(driver, addRecipeButton);
		isElementExists(addRecipeButton);	
		addRecipeButton.click();

		waitUntilElement(driver, presenceAndMovementButton);
		isElementExists(presenceAndMovementButton);
		presenceAndMovementButton.click();
		
		waitUntilElement(driver, assetEntryButton);
		assetEntryButton.click();

		waitUntilElement(driver, toSiteDropDown);
		Select siteDD = new Select(toSiteDropDown);
		siteDD.selectByVisibleText(siteName);
		Select areaDD = new Select(toAreaDropDown);
		areaDD.selectByVisibleText(areaName);
		nextButton.click();
		
		waitUntilElement(driver, notifyViaWebButton);

		notifyViaWebButton.click();

		waitUntilElement(driver, notificationLevelDropDown);
		//messageTextField.sendKeys("{{taggedAsset.asset.name}} entered!");
		Select notificationLevelDD = new Select(notificationLevelDropDown);
		notificationLevelDD.selectByVisibleText(notificationLevel);
		nextButton.click();
		
		waitUntilElement(driver, nameTextField);

		nameTextField.sendKeys(alertName);
		saveButton.click();


		System.out.println(alertName+ " is created successfully");
	}
	
	public void createAssetExitWebAlert(WebDriver driver,String siteName,String areaName,String notificationLevel,String alertName)
	{
		
		waitUntilElement(driver, addRecipeButton);
		isElementExists(addRecipeButton);	
		addRecipeButton.click();

		isElementExists(presenceAndMovementButton);
		presenceAndMovementButton.click();

		waitUntilElement(driver, assetEntryButton);
		assetExitButton.click();

		Select siteDD = new Select(toSiteDropDown);
		siteDD.selectByVisibleText(siteName);
		Select areaDD = new Select(toAreaDropDown);
		areaDD.selectByVisibleText(areaName);
		nextButton.click();

		notifyViaWebButton.click();

		waitUntilElement(driver, notificationLevelDropDown);
		//messageTextField.sendKeys("{{taggedAsset.asset.name}} entered!");
		Select notificationLevelDD = new Select(notificationLevelDropDown);
		notificationLevelDD.selectByVisibleText(notificationLevel);
		nextButton.click();

		nameTextField.sendKeys(alertName);
		saveButton.click();


		System.out.println(alertName+ " is created successfully");
	}

	public void createSubscription(WebDriver driver)
	{
		waitUntilElement(driver, subscriptionsLink);
		isElementExists(subscriptionsLink);
		subscriptionsLink.click();

		waitUntilElement(driver, addSubscriptionButton);
		isElementExists(addSubscriptionButton);	
		addSubscriptionButton.click();
		
		waitUntilElement(driver, categoryDropDown);

		Select categoryDD = new Select(categoryDropDown);
		categoryDD.selectByVisibleText("ALL");
		Select topicDD = new Select(topicDropDown);
		topicDD.selectByVisibleText("ALL");
		Select levelDD = new Select(levelDropDown);
		levelDD.selectByVisibleText("ALL");

		saveButton.click();

		System.out.println(" Subcription is created successfully");

	}

	public void clickTriggerTemplatesLink(WebDriver driver)
	{
		waitUntilElement(driver, triggerTemplatesLink);
		isElementExists(triggerTemplatesLink);
		triggerTemplatesLink.click();
	}

	public void clickactionTemplatesLink(WebDriver driver)
	{
		waitUntilElement(driver, actionTemplatesLink);
		isElementExists(actionTemplatesLink);
		actionTemplatesLink.click();
	}


}
