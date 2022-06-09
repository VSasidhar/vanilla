package amazon.utilities;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.logging.log4j.core.util.Assert;
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
	private static Logger LOGGER = Logger.getLogger("InfoLogging");
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Actions.class);

//    private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");

	public Actions() {

		this.driver = DriverFactory.getDriver();
		if (driver != null) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(max_LoadTime));
		}
	}

	public Actions OpenURl(String URL) {
		driver.navigate().to(URL);
		return this;
	}

	public Actions Click(ObjectLocator locator) {

		FindElement(locator).click();
		if (this.locatorDescription.isEmpty()) {
			this.locatorDescription = locator.locatorDescription;
		}
		ActionLog("Clicked on " + this.locatorDescription);
		this.locatorDescription = "";
		return this;
	}

	public Actions verifyElementPresent(ObjectLocator locator) {

		if (IsElementPresent(locator)) {
			ActionLog(locator.locatorDescription + " found successfully");

		} else {
			AssertFail(locator.locatorDescription + "Element not present on the page");
		}
		return this;
	}

	public boolean IsElementPresent(ObjectLocator locator) {

		WebElement checkElement = FindElement(locator);

		if (checkElement != null) {
			if (checkElement.isDisplayed()) {
				ActionLog(locator.locatorDescription + "element present");
				return true;
			} else {
				ActionLog(locator.locatorDescription + "element not present");
				return false;

			}
		} else {
			return false;
		}

	}

	public Actions Waitforpageload() {
		new WebDriverWait(driver, Duration.ofSeconds(max_LoadTime))
				.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
						.executeScript("return document.readyState").equals("complete"));
		return this;

	}

	public Actions WaitForWebElement(ObjectLocator locator) {

		element = (new WebDriverWait(driver, Duration.ofSeconds(60, 1))
				.until(ExpectedConditions.elementToBeClickable(locator.Locator)));
		return this;
	}

	public WebElement FindElement(ObjectLocator locator) {

		WebElement retElement = null;

		WaitForWebElement(locator);

		try {

			retElement = driver.findElement(locator.Locator);
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			// Handle exception if the element is not found
			ActionLog("NoSuchElementException: The Object " + locator.locatorDescription + " not found! "
					+ ex.getMessage());
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			Waitforpageload();
			retElement = driver.findElement(locator.Locator);
		} catch (ElementNotVisibleException ex) {
			// Handle exception if the element is not found
			ActionLog("ElementNotVisibleException: The Object " + locator.locatorDescription + " not found! ");
		} catch (WebDriverException ex) {
			// Handle exception if the element is not found
			ActionLog("NoSuchElementException: The Object " + locator.locatorDescription + " not found! "
					+ ex.getMessage());
		}
		return retElement;
	}

	public List<WebElement> GetAllElements(ObjectLocator locator) {

		List<WebElement> elements = driver.findElements(locator.Locator);
		return elements;

	}

	public Actions SelectByVisibleText(ObjectLocator locator, String selecttext) {

		Select cmbSelect = new Select(FindElement(locator));
		cmbSelect.selectByVisibleText(selecttext);
		return this;
	}

	public String GetAttribute(ObjectLocator locator) {
		String value = null;

		value = FindElement(locator).getAttribute("value");

		return value;
	}

	public String getSelectedValueFromDropDown(ObjectLocator locator) {

		Select cmbSelect = new Select(FindElement(locator));
		String selectedText = cmbSelect.getFirstSelectedOption().getText();
		return selectedText;

	}

	public String getText(ObjectLocator locator) {

		String tagName = FindElement(locator).getTagName();
		String value = null;

		if (tagName.toLowerCase().equals("input")) {

			value = GetAttribute(locator);
		}

		else if (tagName.toLowerCase().equals("select")) {

			value = getSelectedValueFromDropDown(locator);
		} else {

			value = FindElement(locator).getText();
		}

		return value;
	}

	public Actions ActionLog(String message) {
		log.info(message);
		LOGGER.info(message);
		return this;
	}

	public void AssertFail(String message) {

		// Assert.assertEquals("", message,message);

		ActionLog("[FAILURE] " + message);
//		softAssert.fail(message);

	}
	
	public Set<String> getAllWindowHandles() {
		
		return driver.getWindowHandles();
	}
	
	
	
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
