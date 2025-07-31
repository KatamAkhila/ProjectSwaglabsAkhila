

package genericlibrary;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import pagerepository.CheckoutCompletePage;
import pagerepository.LoginPage;
import pagerepository.LogoutPage;

public class BaseConfig {
	public WebDriver driver;
	public static WebDriver staticDriver;
	public ExtentReports report;
	public ExtentSparkReporter spark;
	public ExtentTest test;

	@BeforeTest
	public void ReportSetup() {
		
		// Create The SparkReport
		spark = new ExtentSparkReporter("./AdvanceReports/report.html");

		// Configure the Sparkreport Information
		spark.config().setDocumentTitle("Regression testing For The Swag Labs");
		spark.config().setReportName("Regression Suite");
		spark.config().setTheme(Theme.DARK);

		// Create the Extent Report
		report = new ExtentReports();

		// Attach the spark report ansd extent report
		report.attachReporter(spark);

		// Configure the system information
		report.setSystemInfo("Device Name", "Ananth");
		report.setSystemInfo("Operating System", "WINDOWS 11");
		report.setSystemInfo("Browser", "Chrome");
		report.setSystemInfo("Browser Version", "138.0.7204.184");

	}

	
	// Step 1: Launch the browser
	@Parameters("BrowserName")
	@BeforeClass
	public void browserSetup(String browser) {
		// Step 1.1: Read browser from config

		// Step 1.2: Open browser
		driver = WebDriverLibrary.openBrowser(browser);
		staticDriver = driver;

		// Step 1.3: Maximize browser
		WebDriverLibrary.maximizeBrowser();

		// Step 1.4: Apply wait statement
		WebDriverLibrary.waitstatement();

		// Step 1.5: Navigate to application URL
		WebDriverLibrary.navToApp(PropertiesLibrary.readData("url"));

		// Step 1.6: Verify title
		Assert.assertEquals(driver.getTitle(),"Swag Labs" );

		// Step 1.7: Log setup
		Reporter.log("Browser setup successful", true);
	}

	// Step 2: Login
	@BeforeMethod
	public void login() {
		// Step 2.1: Wait for elements
		WebDriverLibrary.waitstatement();

		LoginPage lp = new LoginPage(driver);

		// Step 2.2: Verify username text field displayed
		Assert.assertTrue(lp.getusernametextfield().isDisplayed());

		// Step 2.3: Enter username

		WebDriverLibrary.enterTheData(lp.getusernametextfield(), PropertiesLibrary.readData("username"));

		// Step 2.4: Verify password text field displayed
		Assert.assertTrue(lp.getpasswordtextfield().isDisplayed());

		// Step 2.5: Enter password
		WebDriverLibrary.enterTheData(lp.getpasswordtextfield(), PropertiesLibrary.readData("password"));

		// Step 2.6: Verify login button is enabled
		Assert.assertTrue(lp.getloginbutton().isEnabled());

		// Step 2.7: Click login button
		WebDriverLibrary.elementClick(lp.getloginbutton());

		// Step 2.8: Verify home page
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

		// Step 2.9: Log login
		Reporter.log("Application Login successful", true);
	}

	// Step 3: Logout
	@AfterMethod
	public void logout() {
		// Step 3.1: Wait for elements
		WebDriverLibrary.waitstatement();

		LogoutPage lop = new LogoutPage(driver);
		CheckoutCompletePage ccp = new CheckoutCompletePage(driver);

		// Step 3.2: Click 'Back to Home'
		Assert.assertTrue(ccp.getback2prod().isEnabled());
		WebDriverLibrary.elementClick(ccp.getback2prod());

		// Step 3.3: Verify home page
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

		// Step 3.4: Click hamburger menu
		Assert.assertTrue(lop.getMenubar().isEnabled());
		WebDriverLibrary.elementClick(lop.getMenubar());

		// Step 3.5: Click logout
		Assert.assertTrue(lop.getLogout().isEnabled());
		WebDriverLibrary.elementClick(lop.getLogout());

		// Step 3.6: Log logout
		Reporter.log("Application log out successful", true);
	}

	// Step 4: Terminate browser
	@AfterClass
	public void browserterminate() {
		// Step 4.1: Close all browser windows
		WebDriverLibrary.closeAllWindow();

		// Step 4.2: Log termination
		Reporter.log("Browser Termination successful", true);
	}

	
	@AfterTest
	public void ReportTerminate() {
		// Flush the report Information
		report.flush();

	}
	// Step 5: Provide checkout data
	@DataProvider
	public Object[][] checkOutInfo() {
		// Step 5.1: Read data from Excel
		Object[][] data = new Object[1][3];
		data[0][0] = ExcelLibrary.readsingledata("CO_Details", 1, 0);
		data[0][1] = ExcelLibrary.readsingledata("CO_Details", 1, 1);
		data[0][2] = ExcelLibrary.readsingledata("CO_Details", 1, 2);
		return data;
	}

}




































/*package genericlibrary;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import pagerepository.LoginPage;
import pagerepository.LogoutPage;

public class BaseConfig {

	public WebDriver driver;
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;
public static WebDriver staticDriver;
	
	public String url;
	public String username;
	public String password;
	public String firstname;
	public String lastname;
	public String zipcode;
	@BeforeTest
	public void ReportSetup() {
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
	}	
	
	@Parameters("browserName")
	@BeforeClass
	public void browserSetup(String browser) {
		
		//  Open the Browser
		driver=WebDriverLibrary.openBrowser(browser);
	    staticDriver=driver;
	    
	    SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss");
		String currentDate = sdf.format(new Date());
		System.out.println(currentDate);
		// Maximize the Browser
		WebDriverLibrary.maximizeBrowser();
		
		// Wait Statement
		WebDriverLibrary.waitstatement();
        // Navigate to the application via URL
		WebDriverLibrary.navToApp(PropertiesLibrary.readData("url"));
        //Verify The Page Using Title
		Assert.assertEquals("Swag Labs",driver.getTitle());
	}

	@BeforeMethod
	public void LogIn() {
		// Wait Statement
				WebDriverLibrary.waitstatement();
		//Create An object For LoginPage
		 LoginPage loginobj=new LoginPage(driver);
		 
		//Validate The UserNameTextField		
			Assert.assertTrue(loginobj.getusernametextfield().isDisplayed());	
				
	    // Enter UserName in UserName TextField	
			WebDriverLibrary.enterTheData(loginobj.getusernametextfield(),PropertiesLibrary.readData("Username"));
			
			
		//Validate The UserNameTextField		
		Assert.assertTrue(loginobj.getusernametextfield().isDisplayed());	
				
	    // Enter Password in Password TextField	
			WebDriverLibrary.enterTheData(loginobj.getpasswordtextfield(),PropertiesLibrary.readData("Password"));
			
		//Validate The Login Button		
			Assert.assertTrue(loginobj.getusernametextfield().isDisplayed());	
				
	    // Enter UserName in UserName TextField	
			WebDriverLibrary.elementClick(loginobj.getloginbutton());	
			Reporter.log("Login is done", true);

		
		
			}

			@AfterMethod
			public void Logout() {

				// Wait Statement
				WebDriverLibrary.waitstatement();
			   //create An Object For LogoutPage
				LogoutPage logoutobj=new LogoutPage(driver);
				//Validate The logoutMenu
				//Assert.assertTrue(logoutobj.getmenubar().isDisplayed());
				//Click On LogoutMenu
				WebDriverLibrary.elementClick(logoutobj.getmenubar());
				// Wait Statement
				WebDriverLibrary.waitstatement(logoutobj.getlogout());
				//Validate The LogoutLink
				Assert.assertTrue(logoutobj.getlogout().isDisplayed());
				//Click On logoutLink
				//WebDriverLibrary.elementClick(logoutobj.getlogout());
				

				Reporter.log("Logout is done", true);

			}

			@AfterClass
			public void browserTerminate() {

				// Close the Browser
			//WebDriverLibrary.closeAllWindow();

			}
			@DataProvider
			public Object[][] checkoutInfo() {
			Object[][]data = new Object[1][3];
			data[0][0]=ExcelLibrary.readsingledata("Sheet1", 1, 0);
			data[0][1]=ExcelLibrary.readsingledata("Sheet1", 1, 1);
			data[0][2]=ExcelLibrary.readsingledata("Sheet1", 1, 2);
			return data;
			}	
			@AfterTest
			public void ReportTerminate() {
			report.flush();
			
			
			
			}			
}	*/
			
	
		