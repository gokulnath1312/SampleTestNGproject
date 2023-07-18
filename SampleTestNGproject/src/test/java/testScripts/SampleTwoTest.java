package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SampleTwoTest {
	@Test
	  public void CypressSearchTest() {
		WebDriver driver=new ChromeDriver();
		 driver.get("https://www.google.com");
		WebElement searchText=driver.findElement(By.name("q"));
		SoftAssert softassert=new SoftAssert();
				softassert.assertEquals(driver.getTitle(), "Cypres");
		searchText.sendKeys("Cypress");
		searchText.submit();
		
		Assert.assertEquals(driver.getTitle(), "Cypress - Google Search");
	softassert.assertAll();
	driver.close();
}
  }

