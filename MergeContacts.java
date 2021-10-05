package week4day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContacts {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.xpath("//a[contains(text(),'CRM/SFA')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Contacts')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Merge Contacts')]")).click();
		driver.findElement(By.xpath("(//img[contains(@alt,'Lookup')])[1]")).click();
		Set<String> winHandles = driver.getWindowHandles();
		List<String> name=new ArrayList<String>(winHandles);
		driver.switchTo().window(name.get(1));
		driver.findElement(By.xpath("//table[@class='x-grid3-row-table']//a")).click();
		driver.switchTo().window(name.get(0));
		driver.findElement(By.xpath("(//img[contains(@alt,'Lookup')])[2]")).click();
		Set<String> winHandles2 = driver.getWindowHandles();
		List<String> name2=new ArrayList<String>(winHandles2);
		driver.switchTo().window(name2.get(1));
		driver.findElement(By.xpath("(//table[contains(@class,'x-grid3')])[2]//a")).click();
		driver.switchTo().window(name2.get(0));
		driver.findElement(By.linkText("Merge")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		String title = driver.getTitle();
		System.out.println("Title is:"+title);
	}

}
