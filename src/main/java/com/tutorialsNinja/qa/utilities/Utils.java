package com.tutorialsNinja.qa.utilities;

import java.util.Date;

public class Utils {

	public static String emailWithDateTimeStamp() {
		//we are going to use this everywhere hence the static
		//and we dont want to create this method in the other code
		Date date = new Date();
		String emailWithDateTimeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "HumzaAutomation"+emailWithDateTimeStamp+"@gmail.com";
		
	}
	
	public final static int Implicit_wait = 100;
	public final static int PAGELOAD_TIMEOUT = 100;
	public final static int SCRIPT_TIMEOUT = 1000;
	//we can call this so it wont be hard coded in our TestBaseClass

}
