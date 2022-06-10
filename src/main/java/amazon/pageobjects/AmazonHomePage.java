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
			"Xpath for filter - check box "); 
	
	public static ObjectLocator Sortby_Dropdown = new ObjectLocator("ID", "s-result-sort-select",
			"ID of the Sort By DropDown  in Product List");
	
	public static ObjectLocator nth_highestCostItem = new ObjectLocator("XPATH", "((//div[@data-component-type='s-search-result'])[$]//a)[1]",
			"Xpath for second highest Cost Item");
	
	
	
	Actions actions = new Actions();
	
	/**
	 *  Click on Home burger Menu
	 * @return
	 */
	
	public AmazonHomePage clickonHamBurger() {
		actions.click(HamBurgerIcon);

		return this;
	}
	
	/**
	 *  Method to click on 
	 * @param sortByDepartment
	 * @param productType
	 * @return
	 */
	
	public AmazonHomePage filterByDepartmentAndProductType(String sortByDepartment, String productType) {
		actions.click(MenuOption_TV_Appliances.replaceLocator(sortByDepartment));
		actions.click(MenuOption_Products.replaceLocator(productType));
		return this;
	}
	
	/**
	 *  filter the check box - brand
	 * @param brnadName
	 * @return
	 */
	
	public AmazonHomePage narrowDownFilterwithBrand(String brnadName) {
		
		actions.Waitforpageload();
		actions.click(Filter_CheckBoxOption.replaceLocator(brnadName));
		
		return this;
	}
	
	/**
	 *  Method to Sort By filter 
	 * @param sortByOption
	 * @return
	 */
	public AmazonHomePage sortByfilter(String sortByOption) {
		
		actions.selectByVisibleText(Sortby_Dropdown, sortByOption);
		
		return this;
	}
	
	
	/**
	 *  method to click on nth Highest element in the product list
	 * @param nthHighestItem
	 * @return
	 */
	public AmazonHomePage selectnthHighestCostItem(String nthHighestItem) {
		
		actions.click(nth_highestCostItem.replaceLocator(nthHighestItem));
		
		return this;
		
	}
	
	/**
	 *  method to navigate to Home page url
	 * @param url
	 * @return
	 */
	
	public AmazonHomePage navigateToHomePage(String url) {
		actions.OpenURl(url);
		return this;
		
	}

}
