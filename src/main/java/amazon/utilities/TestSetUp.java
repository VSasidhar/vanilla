package amazon.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;

import amazon.factories.DriverFactory;

public class TestSetUp {
	
	/**
	 * This method  runs once before  execution of the @Test methods
	 * @throws MalformedURLException
	 */

	@BeforeTest (alwaysRun= true)

	public void initialise() throws MalformedURLException {

		DriverFactory.initializeDriver();

	}

	/**
	 * This method runs before each @test method
	 * @param method
	 */
	@BeforeMethod (alwaysRun= true)
	public void startreport(Method method) {

		Reports.logger = Reports.report.startTest(method.getAnnotation(Test.class).description());

	}

	/**
	 * This method runs after each @test method and add screen shots to failed test cases
	 * @param testResult
	 * @throws IOException
	 */
	@AfterMethod (alwaysRun= true)
	public void testResult(ITestResult testResult) throws IOException {

		if (testResult.getStatus() == 1) {
			Reports.log("pass", testResult.getName() + " passed ");
		}

		else if (testResult.getStatus() == 2) {
			Reports.log("fail", Reports.logger.addScreenCapture(capture(DriverFactory.getDriver())).concat(testResult.getName()+" Failed"));
		}

		Reports.report.endTest(Reports.logger);

	}

	/**
	 * Method to capture screen 
	 * @param driver
	 * @return
	 * @throws IOException
	 */
	public static String capture(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File("src/../ScreenShots/" + System.currentTimeMillis() + ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
	}

	/**
	 * This method is to start extent report
	 * @param itestContext
	 */
	
	@BeforeClass (alwaysRun= true)
	public static void startReport(ITestContext itestContext) {
		Reports.report = new ExtentReports(System.getProperty("user.dir")+"/ExtentReports/" + "ExtentReportResults.html");

	}

	/**
	 * method to clean close extent reports and Chrome driver
	 */
	@AfterClass (alwaysRun= true)
	public void cleanUp() {

		DriverFactory.closeDriver();
		DriverFactory.quitDriver();
		Reports.report.flush();

	}

}
