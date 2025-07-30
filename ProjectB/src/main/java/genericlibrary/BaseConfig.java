package genericlibrary;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import pagerepository.LoginPage;
import pagerepository.LogoutPage;

public class BaseConfig {

	public WebDriver driver;
	public static WebDriver staticDriver;
	
	public String url;
	public String username;
	public String password;
	public String firstname;
	public String lastname;
	public String zipcode;
	@Parameters("BrowserName")
	@BeforeClass
	public void browserSetup() {
		String browser="edge";
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
				WebDriverLibrary.elementClick(logoutobj.getlogout());
				

				Reporter.log("Logout is done", true);

			}

			@AfterClass
			public void browserTerminate() {

				// Close the Browser
			WebDriverLibrary.closeAllWindow();

			}
			@DataProvider
			public Object[][] checkoutInfo() {
			Object[][]data = new Object[1][3];
			data[0][0]=ExcelLibrary.readsingledata("Sheet1", 1, 0);
			data[0][1]=ExcelLibrary.readsingledata("Sheet1", 1, 1);
			data[0][2]=ExcelLibrary.readsingledata("Sheet1", 1, 2);
			return data;
			}	
			
			
			
			
			
			
			

		}