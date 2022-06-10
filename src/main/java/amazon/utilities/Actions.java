package amazon.utilities;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.typesafe.config.Config;

import amazon.config.EnvFactory;
import amazon.factories.DriverFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Actions {

	private WebDriver driver;
	public WebElement element;
	public String locatorDescription = "";
	private static Config config = EnvFactory.getInstance().getConfig();
	private static int max_LoadTime = Integer.parseInt(config.getString("MAX_WAIT"));
	

	public Actions() {

		this.driver = DriverFactory.getDriver();
		if (driver != null) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(max_LoadTime));
		}
	}
	
	/**
	 *  method to navigate to URL
	 * @param URL
	 * @return
	 */

	public Actions OpenURl(String URL) {
		try {
			driver.navigate().to(URL);
			
			Reports.log("INFO", "Navigated to URL : "+URL);
			
		}
		
		catch(Exception e) {
			
			Reports.log("FAIL", "Navigated to URL : "+URL);
			e.printStackTrace();
			
		}
		
		return this;
	}

	/**
	 *  method to click
	 * @param locator
	 * @return
	 */
	public Actions click(ObjectLocator locator) {

		findElement(locator).click();
		Reports.log("info","Clicked on " + this.locatorDescription);
		return this;
	}

	/**
	 *  method to check if element is present or not and return true or false
	 * @param locator
	 * @return
	 */

	public boolean isElementPresent(ObjectLocator locator) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

		return  getAllElements(locator).size()!=0;

	}
	
	/**
	 * method to wait for page to load
	 * @return
	 */

	public Actions Waitforpageload() {
		new WebDriverWait(driver, Duration.ofSeconds(max_LoadTime))
				.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
						.executeScript("return document.readyState").equals("complete"));
		return this;

	}
	
	/**
	 * Method to wait till element to be click-able
	 * @param locator
	 * @return
	 */

	public Actions waitForWebElementToBeClickable(ObjectLocator locator) {
	
			element = (new WebDriverWait(driver, Duration.ofSeconds(max_LoadTime, 1))
					.until(ExpectedConditions.elementToBeClickable(locator.Locator)));
		
		return this;
	}
	
	/**
	 *  Method to wait till element is visible
	 * @param locator
	 * @return
	 */
	public Actions waitForWebElementToBeVisible(ObjectLocator locator) {
		try {
			element = (new WebDriverWait(driver, Duration.ofSeconds(max_LoadTime, 1))
					.until(ExpectedConditions.visibilityOfElementLocated(locator.Locator)));
		}catch(Exception e) {
			Reports.log("FAIL", locator.locatorDescription+" <br /> wait for webElement failed with error <br />"+e.getMessage());
			e.printStackTrace();
		}
		return this;
	}

	
	/**
	 *  Find element  -includes  wait time  and return web-element
	 * @param locator
	 * @return
	 */
	public WebElement findElement(ObjectLocator locator)  {

		WebElement retElement = null;

		waitForWebElementToBeVisible(locator);

		try {

			retElement = driver.findElement(locator.Locator);
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			// Handle exception if the element is not found
			Reports.log("fail","NoSuchElementException: The Object " + locator.locatorDescription + " not found! "
					+ ex.getMessage());
			ex.printStackTrace();
		
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			Waitforpageload();
			retElement = driver.findElement(locator.Locator);
		} catch (ElementNotVisibleException ex) {
			// Handle exception if the element is not found
			Reports.log("fail","ElementNotVisibleException: The Object " + locator.locatorDescription + " not found! "+ex.getMessage());
			ex.printStackTrace();
		} catch (WebDriverException ex) {
			// Handle exception if the element is not found
			Reports.log("fail","NoSuchElementException: The Object " + locator.locatorDescription + " not found! "
					+ ex.getMessage());
			ex.printStackTrace();
		}
		return retElement;
	}

	/**
	 * return list of web-elements
	 * @param locator
	 * @return
	 */
	public List<WebElement> getAllElements(ObjectLocator locator) {

		List<WebElement> elements = driver.findElements(locator.Locator);
		return elements;

	}

	/**
	 * Method to select drop down by visible test
	 * @param locator
	 * @param selecttext
	 * @return
	 */
	public Actions selectByVisibleText(ObjectLocator locator, String selecttext) {

		Select cmbSelect = new Select(findElement(locator));
		cmbSelect.selectByVisibleText(selecttext);
		return this;
	}

	/**
	 *  Method to get text from value attribute
	 * @param locator
	 * @return
	 */
	public String getAttribute(ObjectLocator locator) {
		String value = null;

		value = findElement(locator).getAttribute("value");

		return value;
	}

	/**
	 * Method to select drop down by value
	 * @param locator
	 * @return
	 */
	public String getSelectedValueFromDropDown(ObjectLocator locator) {

		Select cmbSelect = new Select(findElement(locator));
		String selectedText = cmbSelect.getFirstSelectedOption().getText();
		return selectedText;

	}

	/**
	 * method to get text from input text box or dropdown - default value
	 * @param locator
	 * @return
	 */
	public String getText(ObjectLocator locator) {

		String tagName = findElement(locator).getTagName();
		String value = null;

		if (tagName.toLowerCase().equals("input")) {

			value = getAttribute(locator);
		}

		else if (tagName.toLowerCase().equals("select")) {

			value = getSelectedValueFromDropDown(locator);
		} else {

			value = findElement(locator).getText();
		}

		return value;
	}

	
	/**
	 *  returns set of window titles
	 * @return
	 */
	public Set<String> getAllWindowHandles() {
		
		return driver.getWindowHandles();
	}
	
	
	/**
	 * Method to switch child with when 2 windows are opened
	 */
	public void switchtoChildWindow() {
		
		String mainWindow = driver.getWindowHandle();
		Set<String> handles = getAllWindowHandles();
		
		Iterator<String> iterator = handles.iterator();
		
		while(iterator.hasNext()) {
			
			String childWindow = iterator.next();
			if(!mainWindow.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);
				break;
			}
		}
	
	}
	
	
	

}
