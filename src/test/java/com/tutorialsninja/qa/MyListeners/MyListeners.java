package com.tutorialsninja.qa.MyListeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialninja.qa.ExtentReports.ExtentReporter;


public class MyListeners implements ITestListener{
	
	public ExtentReports extentReport;
	public 	ExtentTest extentTest;
	public WebDriver driver;
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Operation started");
		try {
			 extentReport = ExtentReporter.generateExtentReports();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		//System.out.println(testName+ " has started");
		 extentTest =extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + "Has Started");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
	//	System.out.println(testName+ " has passed");
		 extentTest =extentReport.createTest(testName);
			extentTest.log(Status.PASS, testName + "Has Passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
	//	System.out.println(testName+ " has failed");
		//lets write the code for to capture a screenshot
		//surround with multi try catch block
	
		try {
			 driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			
			e.printStackTrace();
		}
		
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\test-output\\Screenshots\\"+testName+".png";
		                                                                         //have to create this manually, go to properties of testouput folder and name a new folder Screenshots
		try {
			FileHandler.copy(source,new File(destinationFile));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//i want to attach the screenshot into the extent report 
		extentTest.addScreenCaptureFromPath(destinationFile);
		extentTest =extentReport.createTest(testName);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName + "Has been skipped");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
	//	System.out.println(testName+ " has been skipped");
		 extentTest =extentReport.createTest(testName);
			extentTest.log(Status.SKIP, testName + "Has been skipped");
			
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName+ " with a sucess percentage of");
		System.out.println(result.getThrowable());
	
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName+ " has gotten a timout");
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
	System.out.println("Operation Ended");
		
	extentReport.flush();
	String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
	File extentReportPath = new File(pathOfExtentReport);
	try {
		Desktop.getDesktop().browse(extentReportPath.toURI());
		
	}catch(IOException e){
		e.printStackTrace();
	}
	//this will automatically open the extnt report when its done
	}

}
