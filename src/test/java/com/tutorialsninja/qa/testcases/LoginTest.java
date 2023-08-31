package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialsninja.qa.TestBase.TestBase;
import com.tutorialsninja.qa.TestData.DataprovidersForExcel;

public class LoginTest extends TestBase{
	
	public LoginTest() throws Exception {
		super();//super will always be called first 
		       //so when we call this code it will go to the parent class and call the constructor 
		     //that will read the properties file and then it will move forward in our code
		
	}


	WebDriver driver;
	SoftAssert softAssert = new SoftAssert();
	
	@BeforeMethod
	public void setUp() {

	driver = openApplication("Chrome");//this is a parent can be called directly
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		
		
		
	}
	
	@Test(priority=1)
	public void verifyLoginWithValidCredentials() {

		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("ValidEmail"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("ValidPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		softAssert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		softAssert.assertAll();
		
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		
		driver.findElement(By.id("input-email")).sendKeys(dataProp.getProperty("invalidUserName"));
		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]")).getText();
		String expectedWarningMessage = dataProp.getProperty("emailAndPasswordMismatchWarning");
	softAssert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	softAssert.assertAll();
	
	}
	
	@Test(priority=3)
	public void verifyLoginWithValidUserAndInValidPassword() {
		
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("ValidEmail"));
		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]")).getText();
		String expectedWarningMessage = dataProp.getProperty("emailAndPasswordMismatchWarning");
	softAssert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	softAssert.assertAll();
		
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidPasswordAndInvalidUserName() {
		
		driver.findElement(By.id("input-email")).sendKeys(dataProp.getProperty("invalidUserName"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("ValidPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]")).getText();
		String expectedWarningMessage = dataProp.getProperty("emailAndPasswordMismatchWarning");
	softAssert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	softAssert.assertAll();
	}
	
	@Test(priority=5)
	public void verifyLoginWithNoCredentials() {
		
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]")).getText();
		String expectedWarningMessage = dataProp.getProperty("emailAndPasswordMismatchWarning");
	softAssert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	softAssert.assertAll();
		
	}
	@Test(priority=6,dataProvider = "TutorialsNinjaLogin",dataProviderClass = DataprovidersForExcel.class)
	public void loginWithValidCredentialsUsingExcel(String email, String password) {
		
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		if(driver.findElement(By.linkText("Edit your account information")).isDisplayed()) {
			System.out.println("This account is correct :"+email);
			softAssert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
			softAssert.assertAll();
			
		}else {
			System.out.println("This email wont login, there is an error :"+email);
		}
		
		
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	
}
