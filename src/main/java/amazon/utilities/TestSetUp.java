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

	@BeforeTest

	public void initialise() throws MalformedURLException {

		DriverFactory.initializeDriver();

	}

	@BeforeMethod
	public void startreport(Method method) {

		Reports.test = Reports.report.startTest(method.getAnnotation(Test.class).description());

	}

	@AfterMethod
	public void testResult(ITestResult testResult) throws IOException {

		if (testResult.getStatus() == 1) {
			Reports.log("pass", testResult.getName() + " passed ");
		}

		else if (testResult.getStatus() == 2) {
			Reports.log("fail", capture(DriverFactory.getDriver()));
		}

		Reports.report.endTest(Reports.test);

	}

	public static String capture(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File("src/../ScreenShots/" + System.currentTimeMillis() + ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
	}

	@BeforeClass
	public static void startReport(ITestContext itestContext) {
		Reports.report = new ExtentReports(System.getProperty("user.dir")+"/.." + "ExtentReportResults.html");

	}

	@AfterClass
	public void cleanUp() {

		DriverFactory.closeDriver();
		DriverFactory.quitDriver();
		Reports.report.flush();

	}

}
