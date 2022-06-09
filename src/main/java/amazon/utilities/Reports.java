package amazon.utilities;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reports {
	
	public static ExtentTest test;
	public static ExtentReports report;

	@BeforeClass
	public static void startReport()
	{
		report = new ExtentReports(System.getProperty("user.dir")+"ExtentReportResults.html");
		test = report.startTest("ExtentDemo");
	}
	
	
	public static void log(String status, String Description ) {
		
		switch(status.toUpperCase()) {
		
		case "INFO":
			
			test.log(LogStatus.INFO, "<p Style= color:rgb(138,139,138);>"+Description+"</p>");
			break;

		case "PASS":
		
			test.log(LogStatus.PASS, "<p Style= color:green;>"+Description+"</p>");
			break;
			
		case "FAIL":
			
			test.log(LogStatus.FAIL, "<p Style= color:red;>"+Description+"</p>");
			break;
			
		 default:
			
			test.log(LogStatus.PASS, "<p Style= color:green;>"+Description+"</p>");
			break;

		}
		
	}
	
	@AfterClass
	
	public static void closeReports() {
		report.endTest(test);
		report.flush();
		
	}
	
	
	
}
