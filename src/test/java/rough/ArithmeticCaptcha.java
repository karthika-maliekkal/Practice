package rough;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ArithmeticCaptcha {
	static int i;
	static int result;
	static int firstindex = 0;
	static char symbol = 0;
	static int firstAddend;
	static int secondAddend;
	static String firstNum;
	static String secNum;

	public static void main(String[] args) throws IOException, InterruptedException {

		WebDriver driver;

		driver = WebDriverManager.chromedriver().create();
		driver.get("https://timesofindia.indiatimes.com/poll.cms");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

		String equation = driver.findElement(By.id("mathq2")).getText();
		System.out.println("equation : " + equation);

		// finding the index of the arithmetic symbol
		if (equation.contains("+")) {
			firstindex = equation.indexOf("+");
			symbol = '+';
		} else if (equation.contains("-")) {
			firstindex = equation.indexOf("-");
			symbol = '-';
		} else if (equation.contains("/")) {
			firstindex = equation.indexOf("/");
			symbol = '/';
		} else if (equation.contains("*")) {
			firstindex = equation.indexOf("*");
			symbol = '*';
		}

		getSplittedNumbers(equation);
		getArithmeticSymbol(symbol);

		System.out.println(firstNum + "   " + secNum + " = " + result);
		driver.findElement(By.xpath("//*[@id=\"mathuserans2\"]")).sendKeys(Integer.toString(result));

		Thread.sleep(5000);

	}

	public static void getSplittedNumbers(String equation) {

		firstNum = equation.substring(0, firstindex - 1);
		secNum = equation.substring(firstindex + 2, equation.length() - 2);

		firstAddend = Integer.parseInt(firstNum);
		secondAddend = Integer.parseInt(secNum);

	}

	public static void getArithmeticSymbol(char symbol) {

		switch (symbol) {
		case '+':
			result = firstAddend + secondAddend;
			break;

		case '-':
			result = firstAddend - secondAddend;
			break;

		case '*':
			result = firstAddend * secondAddend;
			break;

		case '/':
			result = firstAddend / secondAddend;
			break;

		}
	}
}
