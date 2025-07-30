package producttest;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import genericlibrary.BaseConfig;
import pagerepository.CartPage;
import pagerepository.CheckoutOverviewPage;
import pagerepository.CheckoutPage;
import pagerepository.HomePage;
@Listeners(listenerlibrary.ListenerImplementation.class)
public class Example1Test extends BaseConfig{

	@Test(dataProvider="checkoutInfo")
	public void Detail(String firstname,String lastname,String zipcode)  {

         //verify The Page
		Assert.assertEquals(driver.getTitle(),"Swag Labs");
		// Create an object for Home page
		HomePage hpobj = new HomePage(driver);

		// Use the webElement from POM class
           
		// Click on the fourth product
		hpobj.getfourthproduct().click();
		//Verify that Fourth product is Added
		Assert.assertTrue(hpobj.getaddtocartbtn().isDisplayed());
		Assert.assertTrue(hpobj.getaddtocartbtn().isEnabled());
		// Click on Add To Cart button
		hpobj.getaddtocartbtn().click();
		//Verify that Fourth product is Added
		hpobj.getfourthproduct().click();
        // Click on Back To Products 
		hpobj.getbacktoproducts().click();
        // Click on the Third product
		hpobj.getthirdproduct().click();
		//Verify that Third Product Is Added
        Assert.assertTrue(hpobj.getaddtocartbtn1().isDisplayed());
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
		chkobj.getcontinuebtn().click();
		// Verify Checkout Complete Page
		Assert.assertEquals(driver.getTitle(), "Swag Labs");
		// Create an object for CheckOut-Overview page
		CheckoutOverviewPage ckovobj = new CheckoutOverviewPage(driver);
		// Verify Finish button is Displayed &is Enabled
		Assert.assertTrue(ckovobj.getfinishbtn().isDisplayed() && ckovobj.getfinishbtn().isEnabled());
		// Click on Finish button
		ckovobj.getfinishbtn().click();

		
		//Assert.fail();
		
		
	}

}