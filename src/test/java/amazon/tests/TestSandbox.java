package amazon.tests;
import org.testng.annotations.Test;
import amazon.config.EnvFactory;
import amazon.pagefactory.PageFactory;
import amazon.utilities.TestSetUp;

import com.typesafe.config.Config;


public class TestSandbox extends TestSetUp{
    private static Config config = EnvFactory.getInstance().getConfig();
    private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");
    
    PageFactory pageFactory = new PageFactory();
	
    
   
    
    @Test(description = "Verify About this Item sections and print Details")
    void verifyAbhoutThisItemSecation() {
    	
    	pageFactory.getHomePageObject()
    	.navigateToHomePage(HOME_PAGE_URL)
    	.clickonHamBurger()
    	.filterByDepartmentAndProductType("TV, Appliances, Electronics", "Televisions")
    	.narrowDownFilterwithBrand("Samsung")
    	.sortByfilter("Price: High to Low")
    	.selectnthHighestCostItem("2");
    	
    	pageFactory.amazonProductDetailsPageObject()
    	.switchToProductDetailsPage()
    	.printAboutThisSectionsText();
    	
    }
  
    
   
    

}
