package com.tutorialsninja.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialsninja.qa.TestBase.TestBase;

public class LoginTest extends TestBase{
	
	public LoginTest() throws Exception {
		super();
		
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
		
		driver.findElement(By.id("input-email")).sendKeys("WhatsupDoc1111111111@Gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("CarrotFan22222222321");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
	softAssert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	softAssert.assertAll();
	
	}
	
	@Test(priority=3)
	public void verifyLoginWithValidUserAndInValidPassword() {
		
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("ValidEmail"));
		driver.findElement(By.id("input-password")).sendKeys("CarrotFan322000002221");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
	softAssert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	softAssert.assertAll();
		
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidPasswordAndInvalidUserName() {
		
		driver.findElement(By.id("input-email")).sendKeys("WhatsupDoc00000000000000@Gmail.com");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("ValidPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
	softAssert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	softAssert.assertAll();
	}
	
	@Test(priority=5)
	public void verifyLoginWithNoCredentials() {
		
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
	softAssert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	softAssert.assertAll();
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	
}
