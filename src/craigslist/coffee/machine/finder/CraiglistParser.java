package craigslist.coffee.machine.finder;

import java.util.List;
import java.util.Scanner; 

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

public class CraiglistParser {
	 public static void main(String args[]) throws InterruptedException {  
	     System.out.println(createUrl());  
	     
	     System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
	     WebDriver driver = new ChromeDriver();
	     driver.get(createUrl());
	     Thread.sleep(5000);  
	     List<WebElement> Listings = driver.findElements(By.className("results-row"));
	     System.out.println(Listings.size()); 
	     Thread.sleep(5000);  
	     driver.quit();
	    }  
	 
		
	 public static String createUrl(){  
		 System.out.println("Hello! To get started, please enter your search parameters below :)");
		 Scanner scan = new Scanner(System.in); 
			System.out.println("Enter item name you are searching for:"); 
			String ITEM_LIST = scan.nextLine(); 
			ITEM_LIST = ITEM_LIST.replaceAll(" ","+");
			System.out.println("Enter your ZIP code:"); 
			String MY_ZIPCODE = scan.nextLine(); 
			System.out.println("Enter preferred radius in miles:"); 
			String RADIUS = scan.nextLine(); 
			System.out.println("Enter minimum price in $US:"); 
			String MIN_PRICE = scan.nextLine();
			String MIN_PRICE_COMPLETE = "&min_price=" + MIN_PRICE; 
			if (MIN_PRICE.length() == 0){ 
				MIN_PRICE_COMPLETE = ""; 
			}
			System.out.println("Enter maximum price in $US:"); 
			String MAX_PRICE = scan.nextLine(); 
			String MAX_PRICE_COMPLETE = "&max_price=" + MAX_PRICE;
			if (MAX_PRICE.length() != 0){ 
				MAX_PRICE_COMPLETE = ""; 
			} 
			
//		System.out.println("Enter interval length in minutes for search renewal:"); 
//			String WAIT_TIME = scan.nextLine(); 
			
			System.out.println("Enter item brand or model:"); 
			String MAKE_AND_MODEL_COMPLETE = ""; 
			String MAKE_AND_MODEL = scan.nextLine();
			int a = MAKE_AND_MODEL.length(); 
			if (a >= 1){ 
				MAKE_AND_MODEL_COMPLETE = "&auto_make_model=" + MAKE_AND_MODEL; 
				MAKE_AND_MODEL_COMPLETE.replaceAll(" ","+");

			}
	
			String CONDITION = "";
			String CONDITION_NEW = ""; 
			String CONDITION_LIKE_NEW = "";
			String CONDITION_EXCELLENT = ""; 
			String CONDITION_GOOD = ""; 
			String CONDITION_FAIR = ""; 
			String CONDITION_SALVAGE = ""; 
			
			
			Scanner scan2 = new Scanner(System.in);
			
			System.out.println(
					"Add item condition(s). Enter 'new', 'like+new', 'excellent', 'good', 'fair', 'salvage' or nothing at all:");

			String reply = scan.nextLine();
			System.out.println(reply);

			if (reply.contains("new")) {
				CONDITION_NEW = "&condition=10";
			} if (reply.contains("like+new")) {
				CONDITION_LIKE_NEW = "&condition=20";
			} if (reply.contains("excellent")) {
				CONDITION_EXCELLENT = "&condition=30";
			} if (reply.contains("good")) {
				CONDITION_GOOD = "&condition=30";
			} if (reply.contains("fair")) {
				CONDITION_FAIR = "&condition=40";
			} if (reply.contains("salvage")) {
				CONDITION_SALVAGE = "&condition=50";
			}

			
			CONDITION = CONDITION_NEW + CONDITION_LIKE_NEW + CONDITION_EXCELLENT + CONDITION_GOOD + CONDITION_FAIR + CONDITION_SALVAGE;
	
	
			String URL = "https://sfbay.craigslist.org/search/sss?query=" + ITEM_LIST + "&sort=rel&search_distance=" + RADIUS + "&postal=" + 
					MY_ZIPCODE + MIN_PRICE_COMPLETE + MAX_PRICE_COMPLETE + MAKE_AND_MODEL_COMPLETE + CONDITION;
			
	 
			return URL;       
		 		
		
}
}