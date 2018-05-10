package com.shuvayatra.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.logging.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.Table.Cell;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.BeforeClass;
import org.junit.Test;

public class Main {
	private static Logger LOG = Logger.getLogger("Configuration");
	protected static WebDriver driver;
	 HSSFWorkbook workbook;
	     HSSFSheet sheet;
	     HSSFCell cell;


	@Test
	public void invokeBrowser() {
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			LOG.info("Finished maximizing....");
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			LOG.info("Started registration...");
			driver.get("http://35.198.239.229:8080/home");
			driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div/div/div/button/div/div/span")).click();
			String parent = driver.getWindowHandle();

			// This will return the number of windows opened by Webdriver and will return
			// Set of Strings
			Set<String> s1 = driver.getWindowHandles();

			// Now we will iterate using Iterator
			Iterator<String> I1 = s1.iterator();

			while (I1.hasNext()) {

				String child_window = I1.next();

				// Here we will compare if parent window is not equal to child window then we
				// will close

				if (!parent.equals(child_window)) {
					driver.switchTo().window(child_window);
					LOG.info("Started child window...");
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys("spaudyal@hamropatro.com");

					driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).click();
					driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/content/span")).click();

					driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys("Nepal123!");
					LOG.info("Finished writing password ...");
					driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/content/span")).click();
					/*
					 * WebDriverWait wait=new WebDriverWait(driver,20); WebElement
					 * element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					 * "//*[@id=\"passwordNext\"]/content/span")));
					 */
					LOG.info("clicked Next button.....");
				}

			}
			// once all pop up closed now switch to parent window
			driver.switchTo().window(parent);
			LOG.info("Switched to window parent.....");
			Thread.sleep(10000);
			driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div/div/div/div[3]/div[2]/div/div")).click();
			Thread.sleep(10000);
			LOG.info("clicked FSD.....");
			driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div/div/div[1]/button[2]/div/div")).click();
			LOG.info("clicked provider.....");
			driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div/div/div[3]/div[2]/div/div[2]/div/div[1]/div[2]/div/button/div/div/span")).click();
					LOG.info("Added new provider....");
			
					 File src=new File("C:\\text.xlsx");
					 
					  // Load the file
					 
					  FileInputStream finput=new FileInputStream(src);
					 
					   // load the workbook
					 
					  workbook = new HSSFWorkbook(finput);
					  // get the sheet which you want to modify or create
					  sheet= workbook.getSheetAt(0);

					  for(int i=1; i<sheet.getLastRowNum(); i++)
					     {
					         // Import data for Email.
					         cell = sheet.getRow(i).getCell(1);        
					         cell.setCellType(Cell.CELL_TYPE_STRING);
					         driver.findElement(By.id("login-email")).sendKeys(cell.getStringCellValue());
					         // Import data for password.
					cell = sheet.getRow(i).getCell(2);
 
					         cell.setCellType(Cell.CELL_TYPE_STRING);
					         driver.findElement(By.id("login-password")).sendKeys(cell.getStringCellValue());
					        }

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Main myObj = new Main();
		myObj.invokeBrowser();
				}
	}
	
