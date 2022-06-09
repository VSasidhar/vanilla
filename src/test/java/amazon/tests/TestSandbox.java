package amazon.tests;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import amazon.config.EnvFactory;
import amazon.factories.DriverFactory;
import amazon.pagefactory.PageFactory;
import amazon.utilities.Reports;

import org.testng.ITestResult;
import org.testng.annotations.*;

import com.typesafe.config.Config;


public class TestSandbox extends Reports{
    private static Config config = EnvFactory.getInstance().getConfig();
    private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");
    
    PageFactory pageFactory = new PageFactory();
	
    
    @BeforeTest
    
    public void initialise() {
    	
    	DriverFactory.initializeDriver();
    	
    }
    
    @Test
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
    
    @AfterMethod
    public void testResult(ITestResult testResult) {
    	
    	if(testResult.getStatus()==2) {
    		Reports.log("fail", testResult.getName()+" Failed ");
    	}else if(testResult.getStatus()==1) {
    		Reports.log("pass", testResult.getName()+" passed ");
    	}
    	
    }
    
    @AfterTest
    
    public void cleanUp() {
    	
    	DriverFactory.closeDriver();
    	DriverFactory.quitDriver();
    	
    }
    

}
