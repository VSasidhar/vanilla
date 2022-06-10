package amazon.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ObjectLocator {
	public WebElement element;
	public String locatorType = "";
	public String locatorValue = "";
	public String locatorDescription;
	public static String xpath = "";
	public static String id = "";
	public static String Name = "";
	By Locator;

	public ObjectLocator(String locatorType, String locatorDescription)

	{
		this.locatorType = locatorType;
		this.locatorDescription = locatorDescription;
	}

	
	/**
	 *  return By Locator
	 * @param locatorType
	 * @param locatorValue
	 * @return
	 */
	public By getObjectLocator(String locatorType, String locatorValue) {

		switch (locatorType.toLowerCase()) {
		case ("linktext"):
			Locator = By.linkText(locatorValue);
			break;
		case ("partiallinktext"):
			Locator = By.partialLinkText(locatorValue);
			break;
		case ("id"):
			Locator = By.id(locatorValue);
			break;
		case ("css"):
			Locator = By.cssSelector(locatorValue);
			break;
		case ("name"):
			Locator = By.name(locatorValue);
			break;
		case ("xpath"):
			Locator = By.xpath(locatorValue);
			break;
		case ("tagname"):
			Locator = By.tagName(locatorValue);
			break;
		}
		return Locator;

	}

	/**
	 * 
	 * @param locatorType
	 * @param locatorValue
	 * @param locatorDescription
	 */
	public ObjectLocator(String locatorType, String locatorValue, String locatorDescription)

	{
		this.locatorType = locatorType;
		this.locatorValue = locatorValue;
		this.Locator = getObjectLocator(locatorType, locatorValue);
		this.locatorDescription = locatorDescription;

	}

	/**
	 * Method to replace $ values from locator 
	 * @param replaceString
	 * @return
	 */
	public ObjectLocator replaceLocator(String replaceString)

	{
		this.locatorValue = locatorValue.replace("$", replaceString);
		this.locatorDescription = locatorDescription.concat(" :: " + replaceString);

		this.Locator = getObjectLocator(locatorType, this.locatorValue);

		Reports.log("INFO", this.locatorValue);

		return this;

	}

}
