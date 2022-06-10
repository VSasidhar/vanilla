package amazon.pagefactory;

import amazon.pageobjects.AmazonHomePage;
import amazon.pageobjects.AmazonProductDetailsPage;

public class PageFactory {
	
	AmazonHomePage amazonHomePage;
	AmazonProductDetailsPage amazonProductDetailsPage;
	
	public AmazonHomePage getHomePageObject() {
		
		if(amazonHomePage ==null) {
			return new AmazonHomePage();
		}
		else {
			return amazonHomePage;
		}
	}
	
public AmazonProductDetailsPage amazonProductDetailsPageObject() {
		
		if(amazonHomePage ==null) {
			return new AmazonProductDetailsPage();
		}
		else {
			return amazonProductDetailsPage;
		}
	}
	

}
