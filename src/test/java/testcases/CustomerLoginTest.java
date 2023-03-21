package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CustomerLoginPage;
import pages.HomePage;
import testbase.TestBase;

public class CustomerLoginTest extends TestBase {

	@Test
	public void customerLoginTest() {

		// Assert.assertTrue(home.customerLogin().cutomerLoginpageValidation());

		System.out.println("---start of customerLoginTest()  inside CustomerLoginTest.java---");
		HomePage home = new HomePage(driver);
		

		Assert.assertTrue(home.customerLogin().cutomerLoginpageValidation());

		System.out.println("---End of customerLoginTest()  inside CustomerLoginTest.java---");
	}

}
