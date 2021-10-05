package week4day1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://dev81069.service-now.com/");
		WebElement frame1 = driver.findElement(By.name("gsft_main"));
		driver.switchTo().frame(frame1);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("Haritha@13");
		driver.findElement(By.id("sysverb_login")).click();
		driver.switchTo().defaultContent();
		WebElement search = driver.findElement(By.id("filter"));
		search.click();
		search.sendKeys("incident");
		driver.findElement(By.xpath("(//a//div//div[text()='All'])[2]")).click();
		WebElement frame2 = driver.findElement(By.id("gsft_main"));
		driver.switchTo().frame(frame2);
		driver.findElement(By.id("sysverb_new")).click();
		driver.findElement(By.xpath("//button[@id='lookup.incident.caller_id']//span")).click();
		driver.switchTo().defaultContent();
		Set<String> winHandles = driver.getWindowHandles();
		List<String> list = new ArrayList	<String>(winHandles);
		driver.switchTo().window(list.get(1));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("(//a[@role='button' and @class='glide_ref_item_link'])[1]")).click();
		driver.switchTo().window(list.get(0));
		driver.switchTo().frame(frame2);
		driver.findElement(By.id("incident.short_description")).sendKeys("first contact");
		String incNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println("The incident number is:"+incNumber);
		driver.findElement(By.id("sysverb_insert_bottom")).click();
		WebElement search1 = driver.findElement(By.xpath("//span//input[@class='form-control']"));
		search1.click();
		search1.sendKeys(incNumber,Keys.ENTER);
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/incNumber.png");
		FileUtils.copyFile(src, dst);
		driver.close();
	}
}