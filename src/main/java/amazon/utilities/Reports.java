package amazon.utilities;


import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reports {

	public static ExtentTest logger;
	public static ExtentReports report;
	
	/**
	 * 
	 * @param status
	 * @param Description
	 */

	public static void log(String status, String Description) {

		switch (status.toUpperCase()) {

		case "INFO":

			logger.log(LogStatus.INFO, "<p Style= color:rgb(138,139,138);>" + Description + "</p>");
			break;

		case "PASS":

			logger.log(LogStatus.PASS, "<p Style= color:green;>" + Description + "</p>");
			break;

		case "FAIL":

			logger.log(LogStatus.FAIL, "<p Style= color:red;>" + Description + "</p>");

			break;

		default:

			logger.log(LogStatus.PASS, "<p Style= color:green;>" + Description + "</p>");
			break;

		}

	}
	
	/**
	 *  custom method to log in extent reports and assert
	 * @param flag1
	 * @param flag2
	 * @param description
	 */

	public static void assertEquals(boolean flag1, boolean flag2, String description) {

		if (flag1 == flag2) {

			log("PASS", description);
		} else {

			log("FAIL", description + " Not Present ");
			Assert.assertEquals(flag1, flag2, description);

		}

	}

	/**
	 * custom method to log in extent reports and assert
	 * @param flag1
	 * @param flag2
	 * @param description
	 */
	public static void assertEquals(String flag1, String flag2, String description) {

		if (flag1 == flag2) {

			log("PASS", description);
		} else {

			log("FAIL", description + " Not Present ");
			Assert.assertEquals(flag1, flag2, description);

		}

	}

}
