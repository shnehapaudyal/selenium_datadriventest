package com.shuvayatra.basic;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.junit.Test;
import org.openqa.selenium.By;

public class Login_Test extends Main{
	private static final Logger LOG = Logger.getLogger("Main");
@Test
public void register() {
	//LOG.info("Started registration...");
	driver.get("http://35.198.239.229:8080/login");
	driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div/div/div/button/div/div/span")).click();
	String parent = driver.getWindowHandle();
	Set<String> s1 = driver.getWindowHandles();
	Iterator<String> I1 = s1.iterator();

	while (I1.hasNext()) {

		String child_window = I1.next();

		if (!parent.equals(child_window)) {
			driver.switchTo().window(child_window);
			driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys("spaudyal@hamropatro.com");
			driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).click();
			driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/content/span")).click();
			driver.findElement(By.xpath(""));
			driver.findElement(By.xpath(""));
			driver.findElement(By.xpath(""));
			System.out.println(driver.switchTo().window(child_window).getTitle());

			driver.close();
		}

	}
	driver.switchTo().window(parent);

	driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys("spaudyal@hamropatro.com");
	driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).click();
	driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/content/span")).click();
	driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys("nepal123!");
	driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/content/span"));
}
	
}
