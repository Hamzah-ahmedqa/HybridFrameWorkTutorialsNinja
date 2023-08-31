package com.tutorialsninja.qa.TestData;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataprovidersForExcel {

	public static FileInputStream ip;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	
	@DataProvider(name = "TutorialsNinjaRegister")
	public Object[][] readDataFromExcelForTutNinjaRegister()throws Throwable{
		Object[][] data = DataprovidersForExcel.readingTheDataFromExcel("Register");
		return data;
		
	}
	
	@DataProvider(name = "TutorialsNinjaLogin")
	public static Object[][]readDataExcelForTutninjaLogin() throws Throwable{
		Object[][]data = DataprovidersForExcel.readingTheDataFromExcel("Login");
	    return data;	
	}
	
	public static Object[][] readingTheDataFromExcel(String sheetName) throws IOException{
		
		ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\tutorialsninja\\qa\\TestData\\ThisisOursheet.xlsx");  
		workbook = new XSSFWorkbook(ip);
		sheet=workbook.getSheet(sheetName);
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][]data = new Object[rows][cols];
		
		for(int i=0 ; i<rows ; i++) {
			XSSFRow row = sheet.getRow(i+1);
			
			for(int j=0 ; j<cols ; j++) {
				XSSFCell cell = row.getCell(j);
				
				CellType celltype = cell.getCellType();
				
				switch(celltype) {
				
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
					
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;
					
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					break;
				}//switch loop
				}//inner loop
		        }//outerloop loop
		   
		      return data;
	}//class bracket
	
}
