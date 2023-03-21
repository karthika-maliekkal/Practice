package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.BankManagerPage;
import pages.HomePage;
import testbase.TestBase;

public class OpenAccountTest extends TestBase{

	
	@Test
	public void openAccountTest() throws InterruptedException {
		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.bankMgrLogin().bankManagerPageLoadValidation(), "Bank manager login failed");

		BankManagerPage bankMgrPage = new BankManagerPage(driver);
		Assert.assertTrue(bankMgrPage.openAccount().contains("Account created successfully with account Number :"));
		
		
	}
}
