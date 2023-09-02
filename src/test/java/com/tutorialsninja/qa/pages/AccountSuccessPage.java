package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {
public WebDriver driver;
	
	public AccountSuccessPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//p[text()='Congratulations! Your new account has been successfully created!']")
	private WebElement congratulationsText;
	
	public boolean verifyCongratulationsTextIsDisplayed() {
		boolean congratulationsDisplayStatus = congratulationsText.isDisplayed();
		return congratulationsDisplayStatus;
	}
}
