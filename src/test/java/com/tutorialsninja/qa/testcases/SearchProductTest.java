package com.tutorialsninja.qa.testcases;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialsninja.qa.TestBase.TestBase;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchProductTest extends TestBase {
	
	public SearchProductTest() throws Exception {
		super();
		
	}
	public WebDriver driver;
	public SoftAssert softAssert;
	public WebElement search;
	
	@BeforeMethod
	public void setUp() {
	
		driver = openApplication("Chrome");
		softAssert = new SoftAssert();
		HomePage homePage = new HomePage(driver);
		search = homePage.search;
	  // search = driver.findElement(By.name("search"));
	       search.click();
	       
	}
	
	
	@Test(priority=1)
	public void searchWithValidProductName() {
		search.sendKeys(dataProp.getProperty("validProduct"));
		search.sendKeys(Keys.ENTER);
		SearchPage sp = new SearchPage(driver);
		softAssert.assertTrue(sp.verifyValidProductIsDisplayed());
//softAssert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
        softAssert.assertAll();
		
	}
	
	@Test(priority=2)
	public void searchWithInvalidProductName() {
		HomePage homePage = new HomePage(driver);
		SearchPage sp = new SearchPage(driver);
		search.sendKeys(dataProp.getProperty("InvalidProduct"));
		homePage.clickOnSearchButton();
	//	driver.findElement(By.xpath("//button[contains(@class,'btn-default')]")).click();
	//	softAssert.assertTrue(driver.findElement(By.xpath("//p[text()='There is no product that matches the search criteria.']")).isDisplayed());
		softAssert.assertTrue(sp.verifyInValidProductTextIsDisplayed());
		softAssert.assertAll();
		Assert.fail();//this is so we can see a screenshot
	}
	
	@Test(priority=3)
	public void searchWithNoProduct() {
		HomePage homePage = new HomePage(driver);
		SearchPage sp = new SearchPage(driver);
		search.sendKeys(Keys.ENTER);
		softAssert.assertTrue(sp.verifyInValidProductTextIsDisplayed());
		softAssert.assertAll();
	//	softAssert.assertTrue(driver.findElement(By.xpath("//p[text()='There is no product that matches the search criteria.']")).isDisplayed());
	//	softAssert.assertAll();
		
	}
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
}
