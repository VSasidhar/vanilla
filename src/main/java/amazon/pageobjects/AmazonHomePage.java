package amazon.pageobjects;

import amazon.utilities.Actions;
import amazon.utilities.ObjectLocator;

public class AmazonHomePage {
	
	public static ObjectLocator HamBurgerIcon = new ObjectLocator("ID", "nav-hamburger-menu",
			"ID of the hamburger menu"); 
	
	//TV, Appliances, Electronics
	public static ObjectLocator MenuOption_TV_Appliances = new ObjectLocator("XPATH", "//a[@class='hmenu-item']//div[normalize-space(.)='$']",
			"Xpath for menu Option"); 
	//Televisions
	public static ObjectLocator MenuOption_Products = new ObjectLocator("XPATH", "//a[@class='hmenu-item' and normalize-space(.)='$']",
			"Xpath for Secondary menu option of the product Type"); 
	
	//Samsung
	public static ObjectLocator Filter_CheckBoxOption = new ObjectLocator("XPATH", "//span[normalize-space(.)='$']/preceding-sibling::div//i[@class='a-icon a-icon-checkbox']",
			"Xpath for filter - check box"); 
	
	public static ObjectLocator Sortby_Dropdown = new ObjectLocator("ID", "s-result-sort-select",
			"Xpath for filter - check box");
	
	public static ObjectLocator nth_highestCostItem = new ObjectLocator("XPATH", "((//*[@data-component-type='s-search-result'])[$]//a)[1]",
			"Xpath for second highest Cost Item");
	
	
	
	Actions actions = new Actions();
	
	
	
	public AmazonHomePage() {
		
	}
	
	public AmazonHomePage clickonHamBurger() {
		actions.Click(HamBurgerIcon);

		return this;
	}
	
	public AmazonHomePage filterByDepartmentAndProductType(String sortByDepartment, String productType) {
		actions.Click(MenuOption_TV_Appliances.ReplaceLocator(sortByDepartment));
		actions.Click(MenuOption_Products.ReplaceLocator(productType));
		return this;
	}
	
	public AmazonHomePage narrowDownFilterwithBrand(String brnadName) {
		
		actions.Click(Filter_CheckBoxOption.ReplaceLocator(brnadName));
		
		return this;
	}
	
	public AmazonHomePage sortByfilter(String sortByOption) {
		
		actions.SelectByVisibleText(Sortby_Dropdown, sortByOption);
		
		return this;
	}
	
	public AmazonHomePage selectnthHighestCostItem(String nthHighestItem) {
		
		actions.Click(nth_highestCostItem.ReplaceLocator(nthHighestItem));
		
		return this;
		
	}
	
	public AmazonHomePage navigateToHomePage(String url) {
		actions.OpenURl(url);
		return this;
		
	}

}
