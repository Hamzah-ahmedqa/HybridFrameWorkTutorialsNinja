package com.tutorialsninja.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
public WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="input#input-firstname") 
	private WebElement firstNameTextBox;
	
	@FindBy(css="input#input-lastname")
	private WebElement lastNameTextBox;
	
	@FindBy(css="input#input-email")
	private WebElement emailTextBox;
	
	@FindBy(css="input#input-telephone")
	private WebElement telephoneTextBox;
	
	@FindBy(css="input#input-password")
	private WebElement passwordTextBox;

	@FindBy(css="input#input-confirm")
	private WebElement confirmPasswordTextBox;
	
	@FindBy (xpath="//input[@name='agree']")
	private WebElement agreeButton;
	
	@FindBy(css="input.btn.btn-primary")
	private WebElement continueButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement alreadyUsedEmailWarningText;
	
	@FindBy(xpath="//input[@name='newsletter' and @value='0']")
	private WebElement newsLetterButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
    private WebElement privacyPolicyWarningMessage;
	
	@FindAll({@FindBy(xpath = "//div[@class='text-danger']")})
	public List<WebElement> warningMessagesForEmptyTextBoxFields; //= driver.findElements(By.xpath(null))
	//can make public if this is easier to use
	
	public void enterFirstNameInTextBoxField(String firstName) {
		firstNameTextBox.click();
		firstNameTextBox.sendKeys(firstName);
	}
	
	public void enterLastNameInTextBoxField(String lastName) {
		lastNameTextBox.click();
		lastNameTextBox.sendKeys(lastName);
	}
	
	public void enterEmailInTextBoxField(String email) {
		emailTextBox.click();
		emailTextBox.sendKeys(email);
	}
	
	public void enterTelephoneInTextBoxField(String telephone) {
		telephoneTextBox.click();
		telephoneTextBox.sendKeys(telephone);
	}
	
	public void enterPasswordInTextBoxField(String password) {
		passwordTextBox.click();
		passwordTextBox.sendKeys(password);
	}	
	
	public void enterConfirmPasswordInTextBoxField(String Confirmpassword) {
		confirmPasswordTextBox.click();
		confirmPasswordTextBox.sendKeys(Confirmpassword);
	}	
	
	public void clickOnAgreeButton() {
		agreeButton.click();
	}	
	
	public void clickOnContinueButton() {
		continueButton.click();
	}
	
	public String emailAlreadyRegisteredWarningMessageIsDisplayed() {
		String alreadyUsedEmailWarningMessage =   alreadyUsedEmailWarningText.getText();
		return alreadyUsedEmailWarningMessage;
	}
	
	public void clickOnNewsLetterButton() {
		newsLetterButton.click();
	}
	
	public String retrievePrivacyPolicyWarningMessage() {
		
		String PrivacyPolicyWarningMessage = privacyPolicyWarningMessage.getText();
		return PrivacyPolicyWarningMessage;
	}
	
	
}
