package week4day1;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Frames {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://leafground.com/pages/frame.html");
		WebElement frame1 = driver.findElement(By.xpath("(//div[@id='wrapframe']//iframe)[1]"));
		driver.switchTo().frame(frame1);
		WebElement clickMe = driver.findElement(By.xpath("//button[@id='Click']"));
		File scrnSht = clickMe.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/frames.png");
		FileUtils.copyFile(scrnSht, dst);
		driver.switchTo().defaultContent();
		List<WebElement> noOfFrames = driver.findElements(By.xpath("//div[@id='wrapframe']//iframe"));
		int size = noOfFrames.size();
		System.out.println("The total number of frames are:"+size);
		driver.close();
	}

}
