package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import base.basePage;

public class HomePage extends basePage {

	@FindBy(xpath = "//button[contains(text(),'Customer Login')]")
	public WebElement CusLogingBtn;

	@FindBy(xpath = "//button[contains(text(),'Bank Manager Login')]")
	public WebElement bankMngrLoginBtn;

	
	public HomePage(WebDriver driver) {

		super(driver);
	}

	public CustomerLoginPage customerLogin() {
		
		CusLogingBtn.click();
	    return new CustomerLoginPage(driver);
	}
	
	public BankManagerPage bankMgrLogin() {
		
		bankMngrLoginBtn.click();
		return new BankManagerPage(driver);
	} 

	
}
