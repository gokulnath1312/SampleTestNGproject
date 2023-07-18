package testScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class GooglePageTest {
	
		 WebDriver driver;
		// @BeforeMethod
		 @BeforeTest
		 public void setup() {
			 driver=new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 }
		 
		 @Test(priority=1)
		  public void javaSearchTest() {
			
			driver.get("https://www.google.com");
			WebElement searchText=driver.findElement(By.name("q"));
			searchText.sendKeys("Java Tutorial");
			searchText.submit();
			Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
	 }
  @Test(alwaysRun=true, dependsOnMethods = "appiumSearchTest")
  public void gitSearchTest() {

		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.google.com");
		WebElement searchText1=driver.findElement(By.name("q"));
		searchText1.sendKeys("Git hub");
		searchText1.submit();
		Assert.assertEquals(driver.getTitle(), "Git hub - Google Search");
		
  }
  
  @Test(priority=3)
  public void appiumSearchTest() {

		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.google.com");
		WebElement searchText1=driver.findElement(By.name("q"));
		searchText1.sendKeys("Appium");
		searchText1.submit();
		Assert.assertEquals(driver.getTitle(), "Appium - Google Search");
		
  }
  //@AfterMethod
  @AfterTest
  public void teardown() {
	  driver.close();
  
  }
  
  
}
