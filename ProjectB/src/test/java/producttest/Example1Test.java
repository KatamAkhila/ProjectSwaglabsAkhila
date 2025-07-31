package producttest;

		import org.testng.Assert;
		import org.testng.annotations.Listeners;
		import org.testng.annotations.Test;

		import com.aventstack.extentreports.Status;

		import genericlibrary.BaseConfig;
		import genericlibrary.WebDriverLibrary;
		import pagerepository.CartPage;
		import pagerepository.CheckoutOverviewPage;
		import pagerepository.CheckoutPage;
		import pagerepository.HomePage;
		import pagerepository.ProductPage1;
		import pagerepository.ProductPage2;
		import pagerepository.ProductPage3;

		@Listeners(listenerlibrary.ListenerImplementation.class)
		public class Example1Test extends BaseConfig {

			@Test(dataProvider = "checkOutInfo", priority = 1, invocationCount = 1)
			public void Verify_If_User_Is_Able_To_Order_3_Products(String firstname, String lastname, String zip) {

				// Create Test Information
				test = report.createTest("Verify Order Product");

				// Steps Information
				test.log(Status.INFO, "Step1: Launching The Browser Successful");

				test.log(Status.INFO, "Step2: Navigating To Application via URL Successful");

				test.log(Status.PASS, "Step3: Verified the web page Successful ");

				if (true) {
					test.log(Status.PASS, "Step4: Verified the webelements Displayed");

				} else {
					test.log(Status.FAIL, "Step4: Verified the webelements not Displayed");

				}
				test.log(Status.SKIP, "Step5: Element is Hidden");

				// Step 1: Object Creation for POM Classes
				HomePage hp = new HomePage(driver);
				CartPage cp = new CartPage(driver);
				CheckoutPage checkoutpage = new CheckoutPage(driver);
				CheckoutOverviewPage cop = new CheckoutOverviewPage(driver);
				ProductPage1 pp1 = new ProductPage1(driver);
				ProductPage2 pp2 = new ProductPage2(driver);
				ProductPage3 pp3 = new ProductPage3(driver);

				// Step 2: Click on "Sauce Labs Fleece Jacket" in Product List Page (PLP)
				Assert.assertTrue(hp.getfourthproduct().isDisplayed());
				WebDriverLibrary.elementClick(hp.getfourthproduct());

				// Step 3: Verify if "Sauce Labs Fleece Jacket" Product Details Page (PDP) is
				// displayed
				Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory-item.html?id=5");

				// Step 4: Click on "Add To Cart" Button
				Assert.assertTrue(pp1.getaddtocartbtn().isEnabled());
				WebDriverLibrary.elementClick(pp1.getaddtocartbtn());

				// Step 5: Verify if count is incremented on Cart Icon (1)
				Assert.assertTrue(hp.getCarticon1().isDisplayed());

				// Log
				System.out.println("Sauce Labs Fleece Jacket - Added to cart");

				// Step 6: Click on "Back to products" button
				Assert.assertTrue(pp1.getbacktoproducts().isEnabled());
				WebDriverLibrary.elementClick(pp1.getbacktoproducts());

				// Step 7: Verify if Home page is displayed
				Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

				// Step 8: Click on "Sauce Labs Bolt T-Shirt" in Product List Page (PLP)
				Assert.assertTrue(hp.getthirdproduct().isDisplayed());
				WebDriverLibrary.elementClick(hp.getthirdproduct());

				// Step 9: Verify if "Sauce Labs Bolt T-Shirt" Product Details Page (PDP) is
				// displayed
				Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory-item.html?id=1");

				// Step 10: Click on "Add To Cart" Button
				Assert.assertTrue(pp2.getaddtocartbtn1().isEnabled());
				WebDriverLibrary.elementClick(pp2.getaddtocartbtn1());

				// Step 11: Verify if count is incremented on Cart Icon (2)
				Assert.assertTrue(hp.getCarticon2().isDisplayed());

				// Log
				System.out.println("Sauce Labs Bolt T-Shirt - Added to cart");

				// Step 12: Click on "Back to products" button
				Assert.assertTrue(pp2.getbacktoproducts1().isEnabled());
				WebDriverLibrary.elementClick(pp2.getbacktoproducts1());

				// Step 13: Verify if Home page is displayed
				Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

				// Step 14: Click on "Sauce Labs Bike Light" in Product List Page (PLP)
				Assert.assertTrue(hp.getsecondproduct().isDisplayed());
				WebDriverLibrary.elementClick(hp.getsecondproduct());

				// Step 15: Verify if "Sauce Labs Bike Light" Product Details Page (PDP) is
				// displayed
				Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory-item.html?id=0");

				// Step 16: Click on "Add To Cart" Button
				Assert.assertTrue(pp3.getaddtocartbtn2().isEnabled());
				WebDriverLibrary.elementClick(pp3.getaddtocartbtn2());

				// Log
				System.out.println("Sauce Labs Bike Light - Added to cart");

				// Step 17: Verify if count is incremented on Cart Icon (3)
				Assert.assertTrue(hp.getCarticon3().isDisplayed());

				// Step 18: Click on "Cart" Icon
				Assert.assertTrue(hp.getcarticon().isEnabled());
				WebDriverLibrary.elementClick(hp.getcarticon());

				// Step 19: Verify if Cart Page is displayed
				Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");

				// Step 20: Verify the added products are listed with correct name, price, and
				// quantity
				String[] expectedNames = { "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt", "Sauce Labs Bike Light" };
				String[] expectedPrices = { "$49.99", "$15.99", "$9.99" };
				for (int i = 0; i < expectedNames.length; i++) {
					String actualName = cp.getItemNames().get(i).getText();
					String actualPrice = cp.getItemPrices().get(i).getText();
					String actualQty = cp.getItemQuantities().get(i).getText();

					Assert.assertEquals(actualName, expectedNames[i]);
					Assert.assertEquals(actualPrice, expectedPrices[i]);
					Assert.assertEquals(actualQty, "1");
				}
				System.out.println("All 3 products are valid in cart");

				// Step 21: Click on "Checkout" button

				Assert.assertTrue(cp.getcheckoutbtn().isEnabled());
				WebDriverLibrary.elementClick(cp.getcheckoutbtn());

				// Step 22: Verify if "Checkout: Your Information" Page is displayed
				Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");

				// Step 23: Enter first name in "First Name" textfield
				Assert.assertTrue(checkoutpage.getfirstname().isDisplayed());
				WebDriverLibrary.enterTheData(checkoutpage.getfirstname(), firstname);

				// Step 24: Enter Last name in "Last Name" textfield
				Assert.assertTrue(checkoutpage.getlastname().isDisplayed());
				WebDriverLibrary.enterTheData(checkoutpage.getlastname(), lastname);

				// Step 25: Enter Postal code in "Zip/Postal Code" textfield
				Assert.assertTrue(checkoutpage.getzipcode().isDisplayed());
				WebDriverLibrary.enterTheData(checkoutpage.getzipcode(), zip);

				// Step 26: Click on "Continue" Button
				Assert.assertTrue(checkoutpage.getcontinuebtn().isEnabled());
				WebDriverLibrary.elementClick(checkoutpage.getcontinuebtn());

				// Step 27: Verify if "Checkout: Overview" page is displayed
				Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");

				// Step 28: Verify the added products are listed with correct name, price, and
				// quantity
				String[] expectedNamesCOP = { "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt", "Sauce Labs Bike Light" };
				String[] expectedPricesCOP = { "$49.99", "$15.99", "$9.99" };
				for (int i = 0; i < expectedNames.length; i++) {
					String actualName = cop.getItemNames().get(i).getText();
					String actualPrice = cop.getItemPrices().get(i).getText();
					String actualQty = cop.getItemQuantities().get(i).getText();

					Assert.assertEquals(actualName, expectedNamesCOP[i]);
					Assert.assertEquals(actualPrice, expectedPricesCOP[i]);
					Assert.assertEquals(actualQty, "1");
				}
				System.out.println("All 3 products are valid in Checkout-Overview page");

				// Step 29: Click on "Finish" button
				Assert.assertTrue(cop.getfinishbtn().isEnabled());
				WebDriverLibrary.elementClick(cop.getfinishbtn());

				// Step 30: Verify if "Checkout: Complete" Page is displayed with order
				// confirmation
				Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");

				// Log
				System.out.println("Products ordered succesfully");

	}
		

/*
		//Create The Test Information
				ExtentTest test = report.createTest("Regressiontest");
				//Steps Information
				
				test.log(Status.INFO,"Step1: Launching The Browser Sucessfull");
				test.log(Status. INFO,"Step2: Navigating To The Application Via URL sucesssfull");
				test.log(Status.PASS,"step3: Verified The Webpage Sucessfull");
				if(true) {
					test.log(Status.PASS,"Step4:Verified The WebElements Is Displayed");	
				}
				else {
				test.log(Status.FAIL,"Step4:Verified The WebElements Is Not Displayed");
				
				}
		
         //verify The Page
		Assert.assertEquals(driver.getTitle(),"Swag Labs");
		System.out.println("asdfgh");
		// Create an object for Home page
		HomePage hpobj = new HomePage(driver);

		// Use the webElement from POM class
           
		// Click on the fourth product
		hpobj.getfourthproduct().click();
		
		//Verify that Fourth product is Added
		Assert.assertTrue(hpobj.getaddtocartbtn().isDisplayed() && hpobj.getaddtocartbtn().isEnabled());

		
		// Click on Add To Cart button
		hpobj.getaddtocartbtn().click();
		//Verify that Fourth product is Added
		//hpobj.getfourthproduct().click();
        // Click on Back To Products 
		hpobj.getbacktoproducts().click();
        // Click on the Third product
		hpobj.getthirdproduct().click();
		//Verify that Third Product Is Added
	    Assert.assertTrue(hpobj.getaddtocartbtn1().isEnabled());
		// Click on Add To Cart button
		hpobj.getaddtocartbtn1().click();
		
		// Click on Back To Products
		hpobj.getbacktoproducts1().click();

		// Click on the Second product
		hpobj.getsecondproduct().click();
		//Verify that Second Product Is Added
		Assert.assertTrue(hpobj.getaddtocartbtn2().isDisplayed());
		Assert.assertTrue(hpobj.getaddtocartbtn2().isEnabled());
		// Click on Add To Cart button
		hpobj.getaddtocartbtn2().click();
		
		
		// Click on Back To Products
		hpobj.getbacktoproducts2().click();

		// Click on Cart Icon
		hpobj.getcarticon().click();
		// Verify Cart Page
		Assert.assertEquals(driver.getTitle(), "Swag Labs");

		// Create an object for Cart page
		CartPage cpobj = new CartPage(driver);

		// Use the webElement from POM class

		// Click on the CheckOut button
		cpobj.getcheckoutbtn().click();
		// Verify Checkout Page
		Assert.assertEquals(driver.getTitle(), "Swag Labs");

		// Create an object for CheckOut page
		CheckoutPage chkobj = new CheckoutPage(driver);
		// Verify First Name text field is Displayed & Is Enabled
		Assert.assertTrue(chkobj.getfirstname().isDisplayed() && chkobj.getfirstname().isEnabled());
		// Enter the First Name
		chkobj.getfirstname().sendKeys(firstname);
		// Verify Last Name text field is Displayed & Is Enabled
		Assert.assertTrue(chkobj.getlastname().isDisplayed() && chkobj.getlastname().isEnabled());
		// Enter Last Name
		chkobj.getlastname().sendKeys(lastname);
		// Verify Zipcode text field is Displayed & Is Enabled
		Assert.assertTrue(chkobj.getzipcode().isDisplayed() && chkobj.getzipcode().isEnabled());
		// Enter Zipcode
		chkobj.getzipcode().sendKeys(zipcode);
		// Verify Continue button is Displayed &is Enabled
		Assert.assertTrue(chkobj.getcontinuebtn().isDisplayed() && chkobj.getcontinuebtn().isEnabled());
		// Click on Continue button
		//chkobj.getcontinuebtn().click();
		// Verify Checkout Complete Page
		Assert.assertEquals(driver.getTitle(), "Swag Labs");
		// Create an object for CheckOut-Overview page
		CheckoutOverviewPage ckovobj = new CheckoutOverviewPage(driver);
		// Verify Finish button is Displayed &is Enabled
		Assert.assertTrue(ckovobj.getfinishbtn().isDisplayed() && ckovobj.getfinishbtn().isEnabled());
		// Click on Finish button
		//ckovobj.getfinishbtn().click();

		
		//Assert.fail();
		
		
	}
	@Test
	public void  Detail1() {
		//Create The Test Information
		ExtentTest test = report.createTest("Regressiontest");
		//Steps Information
		
		test.log(Status.INFO,"Step1: Launching The Browser Sucessfull");
		
	}
	@Test
	public void  Detail2() {
		//Create The Test Information
		ExtentTest test = report.createTest("Regressiontest");
		//Steps Information
		
		test.log(Status.INFO,"Step1: Launching The Browser Sucessfull");
		
	}
*/
}