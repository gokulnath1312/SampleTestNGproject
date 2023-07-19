package testScripts;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class LoginInTest {
	Properties prop;
	WebDriver driver;
	 @BeforeMethod
	 public void setup() throws FileNotFoundException {
		 prop=new Properties();
		 String path=System.getProperty("user.dir")+"//src//test//resources//configFiles//config.properties";
		FileInputStream fin=new FileInputStream(path);
		try {
			prop.load(fin);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		String browsername=prop.getProperty("browser");
		if(browsername.equalsIgnoreCase("chrome"))
{
	driver=new ChromeDriver();
}
		else
		{
			driver=new EdgeDriver();
		}
		 
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 }
	 
	 @Test (dataProvider="loginData")
	 public void validLogin(String username, String password){	 
		 
		 driver.get(prop.getProperty("url"));
		 driver.findElement(By.cssSelector("#username")).sendKeys(username);
		 driver.findElement(By.id("password")).sendKeys(password);
		 driver.findElement(By.xpath("//button[@class='radius']")).submit();
		 boolean isValidUser=driver.findElement(By.cssSelector("div.flash.success")).isDisplayed();
		 assertTrue(isValidUser);
		 
}
	 @DataProvider(name="loginData")
	 public Object[][] getdate() throws CsvValidationException, IOException{
		 String path2=System.getProperty("user.dir")+"//src//test//resources//dataFiles//userDate.csv";
		 CSVReader reader=new CSVReader(new FileReader(path2));
		 String cols[];
		 ArrayList<Object> dataList=new ArrayList<Object>();
		 while ((cols=reader.readNext())!=null)
		 {
			 Object record[]= {cols[0],cols[1]};
			 dataList.add(record);
		 }
		 return dataList.toArray(new Object[dataList.size()][]);
			 
		 }
	 
	 @AfterMethod
	 public void teardown()
	 {
		 driver.close();
	 }
	 }
	 
