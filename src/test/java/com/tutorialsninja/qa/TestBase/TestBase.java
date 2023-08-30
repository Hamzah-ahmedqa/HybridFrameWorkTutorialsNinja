package com.tutorialsninja.qa.TestBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsNinja.qa.utilities.Utils;

public class TestBase {
	
public WebDriver driver;
public FileInputStream fip;
public Properties prop;

public TestBase() throws Exception { //create a constructor
	 prop = new Properties();
	FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\tutorialsninja\\qa\\config\\Config.properties");
	prop.load(fip);
	
}
     
         //returning our webdriver
public WebDriver openApplication(String browserName) {
	
	if(browserName.equalsIgnoreCase("chrome")) {
		//can add options here 
		driver = new ChromeDriver();
		
		
	}
	else if(browserName.equalsIgnoreCase("edge")) {
		
		driver = new EdgeDriver();
		
	}
	
	else if(browserName.equalsIgnoreCase("fireFox")) {
		
		driver = new FirefoxDriver();
		
	}
	
	else if (browserName.equalsIgnoreCase("brave")) {
		ChromeOptions options = new ChromeOptions();
		Properties prop = new Properties();
		options.setBinary(prop.getProperty("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe"));
		options.setPageLoadStrategy(PageLoadStrategy.NONE);
		options.addArguments("--incognito");
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		
		
	}
	 driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utils.Implicit_wait));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utils.PAGELOAD_TIMEOUT));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Utils.SCRIPT_TIMEOUT));
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		
		return driver;
}




}
