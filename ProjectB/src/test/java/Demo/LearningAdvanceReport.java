package Demo;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class LearningAdvanceReport {

	public static void main(String[] args) {
		
		
		//Create The SparkReport
		ExtentSparkReporter spark= new ExtentSparkReporter("./AdvanceReports/report.html");
		
		
		//Configure The SparkReport Information
		spark.config().setDocumentTitle("Regression TEsting For The SwagLabs");
		spark.config().setReportName("RegressionSuite");
		spark.config().setTheme(Theme.STANDARD);
		//Create Extent Report
		ExtentReports report = new ExtentReports();
		//Attach The Spark Report And ExtentReport
		report.attachReporter(spark);
		
		
		//Configure The System Information In ExtentRepport
		report.setSystemInfo("DeviceName:","DESKTOP-GQMNKUV");
		report.setSystemInfo("OperatingSystem:","WINDOWS 10");
		report.setSystemInfo("Browser:","chrome");
		report.setSystemInfo("BrowserVersion:","138.0.7204.158");
		
		//Create The Test Information
		ExtentTest test = report.createTest("Regressiontest");
		//Steps Information
		
		test.log(Status.INFO,"Step1: Launching The Browser Sucessfull");
		test.log(Status. INFO,"Step2: Navigating To The Application Via URL sucesssfull");
		test.log(Status.PASS,"step3: Verified The Webpage Sucessfull");
		if(true==true) {
			test.log(Status.PASS,"Step4:Verified The WebElements Is Displayed");	
		}
		else {
		test.log(Status.FAIL,"Step4:Verified The WebElements Is Not Displayed");
		
		}
		
		test.log(Status.SKIP,"Step6:Element Is Hidden");
		
		
		//FLush The Report
		report.flush();
		
		System.out.println("execution done");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
