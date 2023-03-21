package testcases;

//test comment to check commiting

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import pages.BankManagerPage;
import pages.HomePage;
import testbase.TestBase;
import utilities.ExcelReader;

public class AddCustomerTest extends TestBase {

	@Test(dataProvider = "dp")
	
	public void addCustomerTest(String firstName, String lastName, String postalCode)
			throws InterruptedException, IOException {
    
		setUp("chrome");
		
		test=extent.createTest("Add Customer Test");

		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.bankMgrLogin().bankManagerPageLoadValidation(), "Bank manager login failed");

		BankManagerPage bankMgrPage = new BankManagerPage(driver);
		Assert.assertTrue(bankMgrPage.addCustomer(firstName, lastName, postalCode));

	}

	@DataProvider(name="dp")
	public Object[][] getCustomerData(Method m) throws IOException {
		
		ExcelReader excel=new ExcelReader("C:\\Users\\TOSHIBA\\eclipse-workspace\\POM_Practice_Project\\src\\test\\resources\\excel\\TestData.xlsx");

		String sheetName="addCustomerTest";
		//String sheetName=m.getName();
		int rowCount=excel.getRowCount(sheetName);
		int colCount=excel.getColumnCount(sheetName);
		System.out.println("row count :"+rowCount+" colCount :"+colCount);
		
		Object[][] data=new Object[rowCount-1][colCount];
		
		for(int rowNo=2;rowNo<=rowCount;rowNo++) {
			System.out.println("rowNo : "+rowNo);
			for(int colNo=0;colNo<colCount;colNo++) {
				data[rowNo-2][colNo]=excel.getCellData(sheetName, colNo, rowNo);
				System.out.println(rowNo+" "+colNo+" "+data[rowNo-2][colNo]);
			}
		}
		
		System.out.println(data);
		
		return data;
	}

}
