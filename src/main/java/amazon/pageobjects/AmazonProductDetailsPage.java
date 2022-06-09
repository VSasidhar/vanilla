package amazon.pageobjects;


import java.util.List;

import org.openqa.selenium.WebElement;

import amazon.pagefactory.PageFactory;
import amazon.utilities.Actions;
import amazon.utilities.ObjectLocator;
import amazon.utilities.Reports;

public class AmazonProductDetailsPage {
	
	public static ObjectLocator AboutThisItem_SectionHeading = new ObjectLocator("XPATH", "//h1[normalize-space(.)='About this item']",
			"Xpath for About this item header");
	public static ObjectLocator AboutthisItem_Details = new ObjectLocator("XPATH", "//div[@id='feature-bullets']//ul/li",
			"Xpath for About this item details"); 
	
	Actions actions = new Actions();
	PageFactory pageFactory = new PageFactory();
	
	
	public AmazonProductDetailsPage verifyisAboutThisItemSection() {
		
		if(actions.isElementPresent(AboutThisItem_SectionHeading)) {
			
			Reports.log("PASS", "About This Item Section is  Present");
		}else {
			Reports.log("FAIL", "About This Item Section is not Present");
		}
		return this;
	}
	
	public AmazonProductDetailsPage printAboutThisSectionsText() {
		
		verifyisAboutThisItemSection();

		if(actions.isElementPresent(AboutThisItem_SectionHeading)) {
			String abouThisItem ="";
			
			List<WebElement> webElements = actions.getAllElements(AboutthisItem_Details);
			
			for(WebElement web: webElements) {
				
				abouThisItem+= "<p Style= color:rgb(0,13,255);>"+web.getText().toString()+"</p>";
				
			}
			
			Reports.log("INFO", abouThisItem);
		}
		return this;
		
	}
	
	public AmazonProductDetailsPage switchToProductDetailsPage() {
		
		actions.switchtoChildWindow();
		
		return this;
	}
	

}
