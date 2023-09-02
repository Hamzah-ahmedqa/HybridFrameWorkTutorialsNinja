package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	//first create a reference of our WEbdiver
	public WebDriver driver;
	
	public HomePage(WebDriver driver) {
		//create the constructor of this page class 
        //this needs to be done
		this.driver =driver;
		PageFactory.initElements(driver, this); //can do either one 
//		PageFactory.initElements(driver, HomePage.class);
		
	}//this will help us initilize that object
	
	//private becuase it only belongs to homepage 
	@FindBy(linkText="My Account") 	//there is an annotation 
	//this will help us identify each webelement 
	private WebElement MyAccountLink;
	//we want the access limited to that particular page only
	
	@FindBy(linkText = "Login")
	private WebElement LoginLink;
	
	@FindBy(linkText="Register")
	private WebElement registerLink;
	
    @FindBy(name="search")
    public  WebElement search;
    
    @FindBy(xpath="//button[contains(@class,'btn-default')]")
    private WebElement SearchButton;
	
	//actions performed by those objects
	
	public void clickOnMyAccountLink() {
		MyAccountLink.click();
		
	}
	
	public LoginPage clickOnLoginLink() {
		     //from void
		LoginLink.click();
		return new LoginPage(driver); // this is new
	}
	
	public void clickOnRegisterLink() {
		registerLink.click();
		
	}

	public void clickOnSearchButton() {
		SearchButton.click();
	}
	
	
	
}
