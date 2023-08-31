package com.tutorialsninja.qa.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialsNinja.qa.utilities.Utils;
import com.tutorialsninja.qa.TestBase.TestBase;

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
	driver.findElement(By.linkText("My Account")).click();
	driver.findElement(By.linkText("Register")).click();
}
@Test(priority=1)
public void registerAccountWithMandatoryFields() {
	
	driver.findElement(By.cssSelector("input#input-firstname")).sendKeys(dataProp.getProperty("firstName"));
	driver.findElement(By.cssSelector("input#input-lastname")).sendKeys(dataProp.getProperty("lastName"));
	                                                                   //call this method for constant changing, dynamic should be added at all of them
	driver.findElement(By.cssSelector("input#input-email")).sendKeys(Utils.emailWithDateTimeStamp());
	driver.findElement(By.cssSelector("input#input-telephone")).sendKeys(dataProp.getProperty("telephone"));
	driver.findElement(By.cssSelector("input#input-password")).sendKeys(prop.getProperty("ValidPassword"));
	driver.findElement(By.cssSelector("input#input-confirm")).sendKeys(prop.getProperty("ValidPassword"));
	driver.findElement(By.xpath("//input[@name='agree']")).click();
	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	softAssert.assertTrue(driver.findElement(By.xpath("//p[text()='Congratulations! Your new account has been successfully created!']")).isDisplayed());
	softAssert.assertAll();
	
	
	
}
@Test(priority=2)
public void registerAccountWithExistingEmailID() {
	
	driver.findElement(By.cssSelector("input#input-firstname")).sendKeys(dataProp.getProperty("firstName"));
	driver.findElement(By.cssSelector("input#input-lastname")).sendKeys(dataProp.getProperty("lastName"));
	driver.findElement(By.cssSelector("input#input-email")).sendKeys(prop.getProperty("ValidEmail"));
	driver.findElement(By.cssSelector("input#input-telephone")).sendKeys(dataProp.getProperty("telephone"));
	driver.findElement(By.cssSelector("input#input-password")).sendKeys(prop.getProperty("ValidPassword"));
	driver.findElement(By.cssSelector("input#input-confirm")).sendKeys(prop.getProperty("ValidPassword"));
	driver.findElement(By.xpath("//input[@name='agree']")).click();
	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
	String expectedWarningMEssage = dataProp.getProperty("expectedWarningMessageForDuplicateEmail");
	softAssert.assertTrue(actualWarningMessage.contains(expectedWarningMEssage));
	softAssert.assertAll();
	
}


@Test(priority=3)
public void registerAccountWithAllFields() {
	
	driver.findElement(By.cssSelector("input#input-firstname")).sendKeys("Humza");
	driver.findElement(By.cssSelector("input#input-lastname")).sendKeys("Automation");
	driver.findElement(By.cssSelector("input#input-email")).sendKeys(Utils.emailWithDateTimeStamp());
	driver.findElement(By.cssSelector("input#input-telephone")).sendKeys(dataProp.getProperty("telephone"));
	driver.findElement(By.cssSelector("input#input-password")).sendKeys(prop.getProperty("ValidPassword"));
	driver.findElement(By.cssSelector("input#input-confirm")).sendKeys(prop.getProperty("ValidPassword"));
	driver.findElement(By.xpath("//input[@name='newsletter' and @value='0']")).click();
	driver.findElement(By.xpath("//input[@name='agree']")).click();
	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	softAssert.assertTrue(driver.findElement(By.xpath("//p[text()='Congratulations! Your new account has been successfully created!']")).isDisplayed());
	softAssert.assertAll();
	
}

@Test(priority=4)
public void registerWithoutFillingAnyFields() throws Exception {
	
	driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
	Thread.sleep(1000);
	List<WebElement> warningMessages = driver.findElements(By.xpath("//div[@class='text-danger']"));
	System.out.println("The number of warning messages are :"+warningMessages.size());
	
	String actualPrivacyWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
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
