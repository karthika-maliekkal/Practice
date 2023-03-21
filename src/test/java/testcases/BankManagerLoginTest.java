package testcases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import testbase.TestBase;

public class BankManagerLoginTest extends TestBase{
	
	@Test
	public void bankManagerLoginTest() {
		HomePage home=new HomePage(driver);
		Assert.assertTrue(home.bankMgrLogin().bankManagerPageLoadValidation(),"Bank manager login failed");
			
	}

}
