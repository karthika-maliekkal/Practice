package testbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	
	public ExtentReports extent;
	public ExtentSparkReporter htmlReporter;
	public ExtentTest test;
	
	// public static HomePage home=new HomePage(driver);
	// IMP NOTE : Adding above commented statement would result in error, as the
	// whole code flow changes.


	public void setUp(String browser) throws IOException {
		
	//	WebDriver driver=null;
		
		if(browser=="chrome")
		{
			driver = WebDriverManager.chromedriver().create();
		} else if (browser=="firefox") {
			driver=WebDriverManager.firefoxdriver().create();
		}
			
		FileInputStream fis = new FileInputStream(
		System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
		Properties config = new Properties();
		config.load(fis);

		driver.get(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	
		
		//Extend reports setup
		
		htmlReporter=new ExtentSparkReporter("./reports/extend.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Demo test extent reports");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent=new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("Automation Tester","Karthika Anila");

		extent.setSystemInfo("Build no","1234");

		extent.setSystemInfo("organization", "Kefi");

		
		
	}

	@AfterMethod
	public void tearDown(ITestResult result, Method m) {
		Object[] parameters=result.getParameters();
		if(result.getStatus()==ITestResult.SUCCESS)
			test.pass(m.getName()+ " Input data : ["+parameters[0]+","+parameters[1]+","+parameters[2]+"] 	:	 PASS ");
		
	driver.quit();
	extent.flush();
	
	}
}
