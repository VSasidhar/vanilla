package amazon.pageobjects;

import amazon.utilities.Actions;
import amazon.utilities.ObjectLocator;

public class AmazonHomePage {
	
	public static ObjectLocator HamBurgerIcon = new ObjectLocator("ID", "nav-hamburger-menu",
			"ID of the hamburger menu"); 
	
	public static ObjectLocator MenuOption_TV_Appliances = new ObjectLocator("XPATH", "//a[@class='hmenu-item']//div[normalize-space(.)='TV, Appliances, Electronics']",
			"Xpath for menu Option"); 
	
	public static ObjectLocator MenuOption_Products = new ObjectLocator("XPATH", "//a[@class='hmenu-item' and normalize-space(.)='Televisions']",
			"Xpath for Secondary menu option of the product Type"); 
	
	public static ObjectLocator Filter_Option = new ObjectLocator("XPATH", "//span[normalize-space(.)='Samsung']/preceding-sibling::div//input[@type='checkbox']",
			"Xpath for filter - check box"); 
	
	public static ObjectLocator Sortby_Dropdown = new ObjectLocator("ID", "s-result-sort-select",
			"Xpath for filter - check box");
	
	public static ObjectLocator Second_highestCostItem = new ObjectLocator("XPATH", "((//*[@data-component-type='s-search-result'])[2]//a)[1]",
			"Xpath for second highest Cost Item");
	
	
	
	Actions actions = new Actions();
	
	
	public AmazonHomePage clickonHamBurger() {
		actions.Click(HamBurgerIcon);

		return new AmazonHomePage();
	}
	
	public AmazonHomePage filterItemDetails(String sortByDepartment, String productType) {
		actions.Click(HamBurgerIcon);

		return new AmazonHomePage();
	}

}
