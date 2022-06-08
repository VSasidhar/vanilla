package amazon.pageobjects;

import amazon.utilities.ObjectLocator;

public class AmazonProductDetailsPage {
	
	public static ObjectLocator AboutThisItem_SectionHeading = new ObjectLocator("XPATH", "//h1[normalize-space(.)='About this item']",
			"Xpath for About this item header");
	public static ObjectLocator AboutthisItem_Details = new ObjectLocator("XPATH", "//div[@id='feature-bullets']//ul",
			"Xpath for About this item details"); 
	
	
	

}
