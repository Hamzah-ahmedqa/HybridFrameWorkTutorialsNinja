package com.tutorialsninja.qa.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialsNinja.qa.utilities.Utils;
import com.tutorialsninja.qa.TestBase.TestBase;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;

public class RegisterTest extends TestBase {
	
public RegisterTest() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}



public WebDriver driver;
public SoftAssert softAssert = new SoftAssert();

@BeforeMethod
public void setUp() {

 
      driver = openApplication("Chrome");
  	HomePage homePage = new HomePage(driver); //have to pass the driver
  	homePage.clickOnMyAccountLink();
	//driver.findElement(By.linkText("My Account")).click();
	//driver.findElement(By.linkText("Register")).click();
  	homePage.clickOnRegisterLink();
}
@Test(priority=1)
public void registerAccountWithMandatoryFields() {
	RegisterPage registerPage = new RegisterPage(driver);
	//driver.findElement(By.cssSelector("input#input-firstname")).sendKeys(dataProp.getProperty("firstName"));
	registerPage.enterFirstNameInTextBoxField(dataProp.getProperty("firstName"));
	//driver.findElement(By.cssSelector("input#input-lastname")).sendKeys(dataProp.getProperty("lastName"));
	                                                                   //call this method for constant changing, dynamic should be added at all of them
	registerPage.enterLastNameInTextBoxField(dataProp.getProperty("lastName"));
	//driver.findElement(By.cssSelector("input#input-email")).sendKeys(Utils.emailWithDateTimeStamp());
	registerPage.enterEmailInTextBoxField(Utils.emailWithDateTimeStamp());
	//driver.findElement(By.cssSelector("input#input-telephone")).sendKeys(dataProp.getProperty("telephone"));
	registerPage.enterTelephoneInTextBoxField(dataProp.getProperty("telephone"));
//	driver.findElement(By.cssSelector("input#input-password")).sendKeys(prop.getProperty("ValidPassword"));
	registerPage.enterPasswordInTextBoxField(prop.getProperty("ValidPassword"));
//	driver.findElement(By.cssSelector("input#input-confirm")).sendKeys(prop.getProperty("ValidPassword"));
	registerPage.enterConfirmPasswordInTextBoxField(prop.getProperty("ValidPassword"));
	//driver.findElement(By.xpath("//input[@name='agree']")).click();
	registerPage.clickOnAgreeButton();
//	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	registerPage.clickOnContinueButton();
//	softAssert.assertTrue(driver.findElement(By.xpath("//p[text()='Congratulations! Your new account has been successfully created!']")).isDisplayed());
	AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
	softAssert.assertTrue(accountSuccessPage.verifyCongratulationsTextIsDisplayed());
//	softAssert.assertTrue(registerPage.);
	softAssert.assertAll();
	
	
	
}
@Test(priority=2)
public void registerAccountWithExistingEmailID() {
	RegisterPage registerPage = new RegisterPage(driver);
	registerPage.enterFirstNameInTextBoxField(dataProp.getProperty("firstName"));
	registerPage.enterLastNameInTextBoxField(dataProp.getProperty("lastName"));
	registerPage.enterEmailInTextBoxField(prop.getProperty("ValidEmail"));
	registerPage.enterTelephoneInTextBoxField(dataProp.getProperty("telephone"));
	registerPage.enterPasswordInTextBoxField(prop.getProperty("ValidPassword"));
	registerPage.enterPasswordInTextBoxField(prop.getProperty("ValidPassword"));
	registerPage.clickOnAgreeButton();
	registerPage.clickOnContinueButton();
	String actualWarningMessage = registerPage.emailAlreadyRegisteredWarningMessageIsDisplayed();
	String expectedWarningMEssage = dataProp.getProperty("expectedWarningMessageForDuplicateEmail");
	softAssert.assertTrue(actualWarningMessage.contains(expectedWarningMEssage));
	softAssert.assertAll();
	
}


@Test(priority=3)
public void registerAccountWithAllFields() {
	RegisterPage registerPage = new RegisterPage(driver);
	registerPage.enterFirstNameInTextBoxField(dataProp.getProperty("firstName"));
	registerPage.enterLastNameInTextBoxField(dataProp.getProperty("lastName"));
	registerPage.enterEmailInTextBoxField(Utils.emailWithDateTimeStamp());
	registerPage.enterTelephoneInTextBoxField(dataProp.getProperty("telephone"));
	registerPage.enterPasswordInTextBoxField(prop.getProperty("ValidPassword"));
	registerPage.enterConfirmPasswordInTextBoxField(prop.getProperty("ValidPassword"));
//	driver.findElement(By.xpath("//input[@name='newsletter' and @value='0']")).click();
	registerPage.clickOnNewsLetterButton();
	registerPage.clickOnAgreeButton();
	registerPage.clickOnContinueButton();
	AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
	softAssert.assertTrue(accountSuccessPage.verifyCongratulationsTextIsDisplayed());
	softAssert.assertAll();
	
}

@Test(priority=4)
public void registerWithoutFillingAnyFields() throws Exception {
	RegisterPage registerPage = new RegisterPage(driver);
	//driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	registerPage.clickOnContinueButton();
	Thread.sleep(1000);
//	List<WebElement> warningMessages = driver.findElements(By.xpath("//div[@class='text-danger']"));
	List<WebElement> warningMessages = registerPage.warningMessagesForEmptyTextBoxFields;
	
	System.out.println("The number of warning messages are :"+warningMessages.size());
	
	String actualPrivacyWarningMessage =registerPage.retrievePrivacyPolicyWarningMessage();
	//driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
	String expectedPrivacyWarningMessage = dataProp.getProperty("ExpectedPrivacyWarningMessage");
	softAssert.assertTrue(actualPrivacyWarningMessage.contains(expectedPrivacyWarningMessage));
	
	for (int i = 0; i < warningMessages.size(); i++) {
	    String expectedWarningMessage = ""; // Initialize the expected warning message

	    // Determine the expected warning message based on the index
	    switch (i) {
	        case 0://start from 0 because the index starts from 0
	            expectedWarningMessage = dataProp.getProperty("firstNameWarningMessage");
	            break;
	        case 1:
	            expectedWarningMessage = dataProp.getProperty("lastNameWarningMessage");
	            break; 
	        case 2:
	            expectedWarningMessage = dataProp.getProperty("emailWarningMessage");
	            break;
	        case 3:
	            expectedWarningMessage = dataProp.getProperty("telePhoneWarningMessage");
	            break;
	        case 4:
	            expectedWarningMessage = dataProp.getProperty("passwordWarningMessage");
	            break;
	    }

	    // Assert the expected warning message against the actual warning message
	    softAssert.assertTrue(warningMessages.get(i).getText().contains(expectedWarningMessage));
	}

	// After the loop, perform the final assertion
	softAssert.assertAll();
	
for(int i = 0 ; i <warningMessages.size() ; i++) {
		System.out.println(warningMessages.get(i).getText());
	}
	                    

} 


@Test(priority=5)
public void registerAccountWithOutOfBoundsCharacters() {
	
	System.out.println("Hi");
}



@AfterMethod
public void tearDown() {
	
	driver.quit();
}

}
