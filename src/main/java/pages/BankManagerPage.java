package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.basePage;

public class BankManagerPage extends basePage {

	public BankManagerPage(WebDriver driver) {

		super(driver);

	}

	@FindBy(xpath = "//body/div[3]/div[1]/div[2]/div[1]/div[1]/button[1]")
	public WebElement BankManagPageValid;

	@FindBy(xpath = "//body/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/input[1]")
	public WebElement firstName;

	@FindBy(xpath = "//body/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/input[1]")
	public WebElement lastName;

	@FindBy(xpath = "//body/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/form[1]/div[3]/input[1]")
	public WebElement postCode;

	@FindBy(xpath = "//body/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/form[1]/button[1]")
	public WebElement addCustomersubmitBtn;

	@FindBy(xpath = "//body/div[3]/div[1]/div[2]/div[1]/div[1]/button[1]")
	public WebElement addCustomerBtn;

	@FindBy(xpath = "//body/div[3]/div[1]/div[2]/div[1]/div[1]/button[2]")
	public WebElement openAccountBtn;

	@FindBy(css = "#userSelect")
	public WebElement selectCustomerDropDown;

	@FindBy(css = "body.ng-scope:nth-child(2) div.ng-scope:nth-child(3) div.container-fluid.ng-scope div.ng-scope div.border.box.padT20.ng-scope div.ng-scope div.ng-scope div.marTop form.ng-pristine.ng-invalid.ng-invalid-required div.form-group:nth-child(1) select.form-control.ng-pristine.ng-invalid.ng-invalid-required.ng-touched:nth-child(2) > option.ng-binding.ng-scope:nth-child(8)")
	public WebElement customerDropDown;

	@FindBy(css = "#currency")
	public WebElement selectCurrencyDropDown;

	@FindBy(css = "body.ng-scope:nth-child(2) div.ng-scope:nth-child(3) div.container-fluid.ng-scope div.ng-scope div.border.box.padT20.ng-scope div.ng-scope div.ng-scope div.marTop form.ng-pristine.ng-invalid.ng-invalid-required div.form-group:nth-child(2) select.form-control.ng-pristine.ng-invalid.ng-invalid-required.ng-touched:nth-child(2) > option:nth-child(4)")
	public WebElement currencyDropDown;

	@FindBy(xpath = "//button[contains(text(),'Process')]")
	public WebElement processBtn;

	public boolean bankManagerPageLoadValidation() {
		return BankManagPageValid.isDisplayed();

	}

	public boolean addCustomer(String first_Name, String last_Name, String postalCode) throws InterruptedException {
		addCustomerBtn.click();
		firstName.sendKeys(first_Name);
		lastName.sendKeys(last_Name);
		postCode.sendKeys(String.valueOf(postalCode));
		addCustomersubmitBtn.click();
		Thread.sleep(5000);
		Alert alert = driver.switchTo().alert();
		return alert.getText().contains("Customer added successfully with customer id :");
	}

	public String openAccount() throws InterruptedException {

		openAccountBtn.click();
		Select custDropDown = new Select(selectCustomerDropDown);

		custDropDown.selectByIndex(3);
		Thread.sleep(5000);
		Select currencyDropDown = new Select(selectCurrencyDropDown);

		currencyDropDown.selectByIndex(2);
		Thread.sleep(5000);

		processBtn.click();

		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println(alertText);
		System.out.println(alertText.substring(50, alertText.length()));
		alert.accept();

		return alertText;	

	}
}
