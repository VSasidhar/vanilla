import amazon.config.EnvFactory;
import amazon.factories.DriverFactory;
import amazon.pagefactory.PageFactory;

import org.testng.annotations.*;

import com.typesafe.config.Config;


public class TestSandbox {
    private static Config config = EnvFactory.getInstance().getConfig();
    private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");
    
    PageFactory pageFactory = new PageFactory();
	

//    @Tag("smokeTest")
//    @DisplayName("This test is for demo purpose only to show that the basic code works." +
//            "You have to use the best practices that you normally use to design your tests")
    
    @BeforeTest
    
    public void initialise() {
    	
    	DriverFactory.initializeDriver();
    	
    }
    
    @Test
    void assertThatHomePageTitleIsCorrect() {
    	
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
    
    @AfterTest
    
    public void cleanUp() {
    	
    	DriverFactory.closeDriver();
    	DriverFactory.quitDriver();
    	
    }
}
