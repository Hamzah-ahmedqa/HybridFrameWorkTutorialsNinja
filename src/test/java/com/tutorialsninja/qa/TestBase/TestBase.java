package com.tutorialsninja.qa.TestBase;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.tutorialsNinja.qa.utilities.Utils;

public class TestBase {
public ChromeOptions Coptions;
public EdgeOptions Eoptions;
public FirefoxOptions Foptions;
public WebDriver driver;
public FileInputStream fip;
public FileInputStream ip;
public Properties prop;
public Properties dataProp;


public TestBase() throws Exception { //create a constructor
	 prop = new Properties();
	 fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\tutorialsninja\\qa\\config\\Config.properties");
	prop.load(fip);
	
	dataProp = new Properties();
	ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\tutorialsninja\\qa\\TestData\\dataProp.properties");
	dataProp.load(ip);
	
}
     
         //returning our webdriver
public WebDriver openApplication(String browserName) {
	
	if(browserName.equalsIgnoreCase("chrome")) {
		Coptions = new ChromeOptions();
		Coptions.addArguments("--start-maximized");
		Coptions.addArguments("--incognito");
		Coptions.setExperimentalOption("excludeSwitches",Arrays.asList("enable-automation","disable-infobars"));
	//	Coptions.addArguments("--no-sandbox");
    //  Coptions.addArguments("headless");	
        Coptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		driver = new ChromeDriver(Coptions);
		
		
	}
	else if(browserName.equalsIgnoreCase("edge")) {
		Eoptions = new EdgeOptions();
		Eoptions.addArguments("--start-maximized");
		Eoptions.addArguments("--inprivate");
		Eoptions.setExperimentalOption("excludeSwitches",Arrays.asList("enable-automation","disable-infobars"));
		Eoptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		driver = new EdgeDriver(Eoptions);
		
	}
	
	else if(browserName.equalsIgnoreCase("fireFox")) {
		Foptions = new FirefoxOptions();
		Foptions.addArguments("--start-maximized");
		Foptions.addArguments("--inprivate");
		Foptions.addPreference("browser.tabs.remote.autostart", false);
		Foptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		driver = new FirefoxDriver(Foptions);
		
	}
	
	else if (browserName.equalsIgnoreCase("brave")) {
	    Coptions = new ChromeOptions();
		Properties prop = new Properties();
		Coptions.setBinary(prop.getProperty("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe"));
		Coptions.setExperimentalOption("excludeSwitches",Arrays.asList("enable-automation","disable-infobars"));
		Coptions.setPageLoadStrategy(PageLoadStrategy.NONE);
		Coptions.addArguments("--incognito");
		Coptions.addArguments("--start-maximized");
		driver = new ChromeDriver(Coptions);
		
		
	}
	// driver = new ChromeDriver(); <- dont keep this 
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utils.Implicit_wait));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utils.PAGELOAD_TIMEOUT));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Utils.SCRIPT_TIMEOUT));
	//	driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		
		return driver;
}




}
