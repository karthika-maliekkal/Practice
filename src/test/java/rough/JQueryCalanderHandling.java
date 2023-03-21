package rough;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JQueryCalanderHandling {

	static int targetDay = 0, targetMonth = 0, targetYear = 0;

	static int currentDay = 0, currentMonth = 0, currentYear = 0;

	static int jumpMonthsBy = 0;

	static boolean increment = true;

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = WebDriverManager.chromedriver().create();
		driver.get("https://demoqa.com/date-picker");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		String dateToSet = "12/07/2023";
		getCurrentDateMonthAndTear();

		System.out.println(currentDay + " " + currentMonth + " " + currentYear);

		getTargetDateMonthAndYear(dateToSet);
		System.out.println(targetDay + " " + targetMonth + " " + targetYear);

		calculcateHowManyMonthsToJump();
		System.out.println("jumpMonthsBy :" + jumpMonthsBy);

		driver.findElement(By.xpath("//input[@id='datePickerMonthYearInput']")).click();

		for (int i = 0; i < jumpMonthsBy; i++) {
			if (increment)
				driver.findElement(By.xpath("//button[contains(text(),'Next Month')]")).click();
			else
				driver.findElement(By.xpath("//button[contains(text(),'Previous Month')]")).click();

		}

		Thread.sleep(5000);
	}

	public static void getCurrentDateMonthAndTear() {

		Calendar cal = Calendar.getInstance();
		currentDay = cal.get(Calendar.DAY_OF_MONTH);
		currentMonth = cal.get(Calendar.MONTH) + 1;
		currentYear = cal.get(Calendar.YEAR);

	}

	public static void getTargetDateMonthAndYear(String dateString) {

		int firstIndex = dateString.indexOf("/");
		int secondIndex = dateString.lastIndexOf("/");

		String day = dateString.substring(0, firstIndex);
		targetDay = Integer.parseInt(day);

		String month = dateString.substring(firstIndex + 1, secondIndex);
		targetMonth = Integer.parseInt(month);

		String year = dateString.substring(secondIndex + 1, dateString.length());
		targetYear = Integer.parseInt(year);

	}

	public static void calculcateHowManyMonthsToJump() {

		if ((targetMonth - currentMonth) > 0) {
			jumpMonthsBy = targetMonth - currentMonth;

		} else {
			jumpMonthsBy = currentMonth - targetMonth;
			increment = false;
		}
	}

}
