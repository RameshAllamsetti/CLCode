package com.cloudleaf.webautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AssetAdminPage extends Utility {
	
	AssetAdminPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='fa fa-plus']")
	public WebElement addAssetButton;
	
	@FindBy(xpath="//input[@data-ng-model='asset.externalId']")
	public WebElement assetIDTextField;
	
	@FindBy(xpath="//input[@data-ng-model='asset.name']")
	public WebElement assetNameTextField;
	
	
	//@FindBy(xpath="//select[@class='form-control ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched']")
	@FindBy(xpath="//select[@data-ng-model='asset.category']")
	public WebElement assetCategoryDropDown;
	
	@FindBy(xpath="//button[text()='Save']")
	public WebElement assetSaveButton;
	
	@FindBy(xpath="//button[text()='Cancel']")
	public WebElement assetCancelButton;
	
	
	@FindBy(xpath="//a[text()='Assets']")
	public WebElement assetsLink;
	
	
	@FindBy(xpath="//a[text()='Asset Categories']")
	public WebElement assetCategoriesLink;
	
	@FindBy(xpath="//span[@class='fa fa-plus']")
	public WebElement addCategoryButton;
	
	@FindBy(xpath="//input[@placeholder='Enter Category Name']")
	public WebElement categoryNameTextField;
	
	@FindBy(xpath="//input[@placeholder='Enter Category Description']")
	public WebElement descriptionTextField;
	
	@FindBy(xpath="//input[@placeholder='Enter Category Sort Order (0-99)']")
	public WebElement sortOrderTextField;
	
	@FindBy(xpath="//button[text()='Add All']")
	public WebElement addAllButton;
	
	@FindBy(xpath="//button[text()='Save']")
	public WebElement categorySaveButton;
	
	@FindBy(xpath="//button[text()='Cancel']")
	public WebElement categoryCancelButton;
	
	@FindBy(xpath="//span[@class='fa fa-angle-down']")
	public WebElement actionsButton;
	
	@FindBy(xpath="//i[@class='fa fa-link']")
	public WebElement linkIcon;
	
	@FindBy(xpath="//i[@class='fa fa-unlink']")
	public WebElement unlinkIcon;
	
	//@FindBy(xpath="//i[@class='fa fa-trash-o']")
	//public WebElement deleteIcon;
	
	@FindBy(name="tagId")
	public WebElement tagIdTextField;
	
	@FindBy(xpath="//button[text()='Bind']")
	public WebElement bindButton;
	
	@FindBy(xpath="//button[text()='Cancel']")
	public WebElement cancelButton;
	
	
	
	@FindBy(xpath="//button[@data-toggle='dropdown']")
	public WebElement categoryButton;

	
	@FindBy(xpath="//select[@class='form-control ng-pristine ng-untouched ng-valid ng-not-empty']")
	public WebElement categoryDropDown;
	
	
	
	/*@FindBy(xpath="//div[text()='"+assetName+"']")
	public WebElement assetCheckbox;*/
	
	
	public void clickAddAssetButton(WebDriver driver)
	{
		waitUntilElement(driver, addAssetButton);
		isElementExists(addAssetButton);
		addAssetButton.click();
		
	}
	
	public void addAsset(WebDriver driver,String assetID,String assetName,String assetCatgory)
	{
		waitUntilElement(driver, assetIDTextField);
		isElementExists(assetNameTextField);
		assetIDTextField.sendKeys(assetID);
		assetNameTextField.sendKeys(assetName);
		Select sel = new Select(assetCategoryDropDown);
		sel.selectByVisibleText(assetCatgory);
		assetSaveButton.click();
		
		System.out.println(assetID+ " is added successfully");
	}
	
	public void clickAssetsLink(WebDriver driver)
	{
		waitUntilElement(driver, assetsLink);
		isElementExists(assetsLink);
		assetsLink.click();
	}
	
	public void clickAssetCategoryLink(WebDriver driver)
	{
		waitUntilElement(driver, assetCategoriesLink);
		isElementExists(assetCategoriesLink);
		assetCategoriesLink.click();
	}
	
	public void clickAddCategoriesButton(WebDriver driver)
	{
		waitUntilElement(driver, addCategoryButton);
		isElementExists(addCategoryButton);
		addCategoryButton.click();
	}
	
	public void addCategory(WebDriver driver,String categoryName,String description,String sortOrder)
	{
		waitUntilElement(driver, categoryNameTextField);
		isElementExists(categoryNameTextField);
		categoryNameTextField.sendKeys(categoryName);
		descriptionTextField.sendKeys(description);
		sortOrderTextField.sendKeys(sortOrder);
		addAllButton.click();
		categorySaveButton.click();
		
		System.out.println(categoryName+ " is added successfully");
		
	}
	
	/*public void selectAsset(String assetName)
	{
		driver.findElement(By.xpath("//div[text()='"+assetName+"']")).click();
		assetCheckbox
	}*/
	
	public void linkAsset(WebDriver driver,String tagId) throws InterruptedException
	{
		waitUntilElement(driver, actionsButton);
		isElementExists(actionsButton);
		actionsButton.click();
		waitUntilElement(driver, linkIcon);
		linkIcon.click();
		waitUntilElement(driver, tagIdTextField);
		tagIdTextField.sendKeys(tagId);
		waitUntilElement(driver, bindButton);
		bindButton.click();
		
		System.out.println(tagId + " is  successfully Linked ");
	}
	
	public void selectCategory(WebDriver driver,String categoryName) throws InterruptedException
	{
		waitUntilElement(driver, categoryButton);
		categoryButton.click();
		Thread.sleep(3000);
		
	/*	Select catSelect = new Select(categoryDropDown);
		
		catSelect.selectByVisibleText(categoryName);
		Thread.sleep(3000);*/
		
		/*driver.findElement(By.xpath("//a[text()='"+categoryName+"']")).click();
		Thread.sleep(3000);
		System.out.println(categoryName + "Selected");*/
	}

}
