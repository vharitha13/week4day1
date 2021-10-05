package week4day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ClassroomWindows {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.irctc.co.in/nget/train-search");
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		driver.findElement(By.linkText("FLIGHTS")).click();
		Set<String> winHandle = driver.getWindowHandles();
		List<String> name=new ArrayList<String>(winHandle);
		driver.switchTo().window(name.get(1));
		String text = driver.findElement(By.xpath("(//a[contains(@class,'d-block')])[1]")).getText();
		System.out.println("Customer Care email id is:"+text);
		driver.switchTo().window(name.get(0));
		driver.close();
		
	}

}
