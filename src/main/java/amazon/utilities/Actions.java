package amazon.utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
		try {
			driver.navigate().to(URL);
			
			Reports.log("INFO", "Navigated to URL : "+URL);
			
		}
		
		catch(Exception e) {
			
		}
		
		return this;
	}

	public Actions Click(ObjectLocator locator) {

		findElement(locator).click();
		if (this.locatorDescription.isEmpty()) {
			this.locatorDescription = locator.locatorDescription;
		}
		Reports.log("info","Clicked on " + this.locatorDescription);
		this.locatorDescription = "";
		return this;
	}

	public Actions verifyElementPresent(ObjectLocator locator) {

		if (isElementPresent(locator)) {
			Reports.log("info", locator.locatorDescription+" is Present");

		} else {
			Reports.log("fail",locator.locatorDescription + "Element not present on the page with Locator "+locator.locatorValue);
		}
		return this;
	}

	public boolean isElementPresent(ObjectLocator locator) {

		WebElement checkElement = findElement(locator);

		if (checkElement != null) {
			if (checkElement.isDisplayed()) {
				Reports.log("info", locator.locatorDescription+" is Present");
				return true;
			} else {
				Reports.log("info", locator.locatorDescription+" is not Present");
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

	public Actions waitForWebElementToBeClickable(ObjectLocator locator) {
		try {
			element = (new WebDriverWait(driver, Duration.ofSeconds(max_LoadTime, 1))
					.until(ExpectedConditions.elementToBeClickable(locator.Locator)));
		}catch(Exception e) {
			Reports.log("FAIL", " wait for webElement failed with error "+e.getMessage());
		}
		return this;
	}
	
	public Actions waitForWebElementToBeVisible(ObjectLocator locator) {
		try {
			element = (new WebDriverWait(driver, Duration.ofSeconds(max_LoadTime, 1))
					.until(ExpectedConditions.visibilityOfElementLocated(locator.Locator)));
		}catch(Exception e) {
			Reports.log("FAIL", " wait for webElement failed with error "+e.getMessage());
		}
		return this;
	}

	public WebElement findElement(ObjectLocator locator) {

		WebElement retElement = null;

		waitForWebElementToBeVisible(locator);

		try {

			retElement = driver.findElement(locator.Locator);
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			// Handle exception if the element is not found
			Reports.log("fail","NoSuchElementException: The Object " + locator.locatorDescription + " not found! "
					+ ex.getMessage());
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			Waitforpageload();
			retElement = driver.findElement(locator.Locator);
		} catch (ElementNotVisibleException ex) {
			// Handle exception if the element is not found
			Reports.log("fail","ElementNotVisibleException: The Object " + locator.locatorDescription + " not found! "+ex.getMessage());
		} catch (WebDriverException ex) {
			// Handle exception if the element is not found
			Reports.log("fail","NoSuchElementException: The Object " + locator.locatorDescription + " not found! "
					+ ex.getMessage());
		}
		return retElement;
	}

	public List<WebElement> getAllElements(ObjectLocator locator) {

		List<WebElement> elements = driver.findElements(locator.Locator);
		return elements;

	}

	public Actions selectByVisibleText(ObjectLocator locator, String selecttext) {

		Select cmbSelect = new Select(findElement(locator));
		cmbSelect.selectByVisibleText(selecttext);
		return this;
	}

	public String getAttribute(ObjectLocator locator) {
		String value = null;

		value = findElement(locator).getAttribute("value");

		return value;
	}

	public String getSelectedValueFromDropDown(ObjectLocator locator) {

		Select cmbSelect = new Select(findElement(locator));
		String selectedText = cmbSelect.getFirstSelectedOption().getText();
		return selectedText;

	}

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
