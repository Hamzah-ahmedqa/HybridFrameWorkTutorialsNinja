package com.tutorialsninja.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
public WebDriver driver;

public LoginPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

@FindBy(id="input-email")
private WebElement emailTextBoxField;

@FindBy(id="input-password")
private WebElement passwordTextBoxField;

       //only have to write css not cssSelector
@FindBy(css="input.btn.btn-primary")
private WebElement loginButton;

@FindBy(xpath="//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]")
private WebElement warningMessageForEmailAndPasswordMismatch;

public void enterEmailInTextBoxField(String emailText) {
	 emailTextBoxField.sendKeys(emailText);
}

public void enterPasswordInTextBoxField(String passwordText) {
	passwordTextBoxField.sendKeys(passwordText);
}

public void clickOnLoginButton() {
	loginButton.click();
}

public String mismatchEmailAndPasswordWarningMessageText() {
	String warningMessage = warningMessageForEmailAndPasswordMismatch.getText();
	return warningMessage;
}

}
