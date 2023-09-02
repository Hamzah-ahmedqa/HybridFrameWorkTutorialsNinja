package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
public WebDriver driver;

	public AccountPage(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Edit your account information")
	private WebElement editYourAccountLink;
	
	
	public boolean verifyEditAccountInforamtionTextIsDisplayed() {
		boolean editAccountDisplayStatus = editYourAccountLink.isDisplayed();
		return editAccountDisplayStatus;
	}
	
	

	
}
