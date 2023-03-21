package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.basePage;

public class CustomerLoginPage extends basePage {

	public CustomerLoginPage(WebDriver driver) {
		super(driver);
			}

	@FindBy(xpath = "//label[contains(text(),'Your Name :')]")
	public WebElement customerLoginPageValid;

	public boolean cutomerLoginpageValidation() {
		return customerLoginPageValid.isDisplayed();

	}

}
