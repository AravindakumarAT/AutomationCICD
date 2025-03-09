package frameworkpart1.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG 
{
	
	public static ExtentReports getReportObject()
	{
		String path=System.getProperty("user.dir")+"//reports//testreport.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path); //Give Meta Data(Like Report Information to the Report)
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Aravind");
		return extent;
	}
}
