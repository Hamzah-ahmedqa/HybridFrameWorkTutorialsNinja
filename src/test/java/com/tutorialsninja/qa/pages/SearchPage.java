package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	public WebDriver driver;
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(linkText="HP LP3065")
    private WebElement textOfValidProduct;
	
	@FindBy(xpath="//p[text()='There is no product that matches the search criteria.']")
	private WebElement noProductMatchText;
	
	
	public boolean verifyValidProductIsDisplayed() {
		boolean HpProductLinkText = textOfValidProduct.isDisplayed();
		return HpProductLinkText;
	}
	
	public boolean verifyInValidProductTextIsDisplayed() {
		boolean noProductMatch = noProductMatchText.isDisplayed();
		return noProductMatch;
	}
}
