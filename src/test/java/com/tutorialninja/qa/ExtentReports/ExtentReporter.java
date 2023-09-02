package com.tutorialninja.qa.ExtentReports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	
	//make sure our mvn repository is there inside the pom.xml
	
//	step 1: create the object of our ExtentReportClass
//	ExtentReports extentReport = new ExtentReports();

// step 2: create the object of file class and pass the file of .html file in the constructor	                                                                                                  //we can name it whatever but the extension should be .html
//	File extentReportFile = new File(System.getProperty("user.dir")+ "//test-output//ExtentReports//extentReport.html");
	                                              //the folder where we want our extent report 


// step 3: create the object of extentSparkReporter Class and pass the File reference in the Constructor 
//	ExtentSparkReporter spark = new ExtentSparkReporter(extentReportFile);


	
public static ExtentReports generateExtentReports() throws IOException {
	ExtentReports extentReport = new ExtentReports();
	File extentReportFile = new File(System.getProperty("user.dir")+ "\\test-output\\ExtentReports\\extentReport.html");
	ExtentSparkReporter spark = new ExtentSparkReporter(extentReportFile);
	// this can also be sparkReporter
	
	
	//step 4: using the spark reporter reference, we can make a lot of changes and configure a lot of things
	
	spark.config().setTheme(Theme.DARK);
	//this just gives a dark theme
	
	spark.config().setReportName("Tutorials Ninja Automation Results");
	                                //this will name the bar on top of the page, like the header
	
	spark.config().setDocumentTitle("TNReportTitle|Automation|Humza|Ahemd");
	                                  // this will create the title of our page
	
	spark.config().setTimeStampFormat("mm/dd/yyyy hh:mm:ss");
	                              // this changes our time format to this style Month/Day/year hours:minutes:seconds
	
//step 5: lets attach the ExtentReport  with the spark Reporter
	extentReport.attachReporter(spark);
	
	             // -- additional information to add the specific information to the report, this is not mandatory 
		//step 6: create properties file and add the necessary details into the properties file
	
//	step 7: write the code to read data from the properties file here	 
	Properties prop = new Properties();
    FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\java\\com\\tutorialsninja\\qa\\config\\Config.properties");
    prop.load(ip);

	//what is the application url? BrowserName, Username, Password, OperatingSystem, Java Version, Name of the SDET? what if we want this information
    
    extentReport.setSystemInfo("Application url", prop.getProperty("url"));
    extentReport.setSystemInfo("Browser Name", prop.getProperty("browser"));
    extentReport.setSystemInfo("User Name", prop.getProperty("ValidUsername"));
    extentReport.setSystemInfo("Password ", prop.getProperty("ValidPassword"));
   
    extentReport.setSystemInfo("Operating system", System.getProperty("os.name"));
    extentReport.setSystemInfo("Operating version", System.getProperty("os.version"));
    extentReport.setSystemInfo("SDET name:", System.getProperty("user.name"));
        extentReport.setSystemInfo("Java Vendor", System.getProperty("java.vendor"));
        extentReport.setSystemInfo("Java version", System.getProperty("java.version"));
        
        
        //step: 8 return the extentReport 
        return extentReport;
        
        
        
        //Step 9 : write code in the onTestFailure /method to capture a screenshot
        
}

}                                                                      




