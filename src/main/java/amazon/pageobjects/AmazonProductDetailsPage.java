package amazon.pageobjects;


import amazon.pagefactory.PageFactory;
import amazon.utilities.Actions;
import amazon.utilities.ObjectLocator;

public class AmazonProductDetailsPage {
	
	public static ObjectLocator AboutThisItem_SectionHeading = new ObjectLocator("XPATH", "//h1[normalize-space(.)='About this item']",
			"Xpath for About this item header");
	public static ObjectLocator AboutthisItem_Details = new ObjectLocator("XPATH", "//div[@id='feature-bullets']//ul",
			"Xpath for About this item details"); 
	
	Actions actions = new Actions();
	PageFactory pageFactory = new PageFactory();
	
	
	public AmazonProductDetailsPage verifyisAboutThisItemSection() {
		
		if(actions.IsElementPresent(AboutThisItem_SectionHeading)) {
			
			actions.ActionLog("About This Item Section is  Present");
		}else {
			actions.AssertFail("About This Item Section is not Present");
		}
		return pageFactory.amazonProductDetailsPageObject();
	}
	
	public AmazonProductDetailsPage printAboutThisSectionsText() {
		
		verifyisAboutThisItemSection();
		
		if(actions.IsElementPresent(AboutThisItem_SectionHeading)) {
			
			actions.ActionLog(actions.getText(AboutthisItem_Details));
		}
		return pageFactory.amazonProductDetailsPageObject();
		
	}
	
	public AmazonProductDetailsPage switchToProductDetailsPage() {
		
		actions.switchtoChildWindow();
		
		return pageFactory.amazonProductDetailsPageObject();
	}
	

}
