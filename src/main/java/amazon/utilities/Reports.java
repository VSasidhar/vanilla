package amazon.utilities;

import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reports {
	
	public static ExtentTest logger;
	public static ExtentReports report;

	
	
	
	public static void log(String status, String Description ) {
		
		switch(status.toUpperCase()) {
		
		case "INFO":
			
			logger.log(LogStatus.INFO, "<p Style= color:rgb(138,139,138);>"+Description+"</p>");
			break;

		case "PASS":
		
			logger.log(LogStatus.PASS, "<p Style= color:green;>"+Description+"</p>");
			break;
			
		case "FAIL":
			
			logger.log(LogStatus.FAIL, "<p Style= color:red;>"+Description+"</p>");
			Assert.fail(Description);
			break;
			
		 default:
			
			logger.log(LogStatus.PASS, "<p Style= color:green;>"+Description+"</p>");
			break;

		}
		
	}
	
	
	
	
	
}
