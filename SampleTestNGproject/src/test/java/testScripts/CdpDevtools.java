package testScripts;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.emulation.Emulation;
import org.openqa.selenium.devtools.v114.network.Network;
import org.openqa.selenium.devtools.v114.network.model.Headers;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class CdpDevtools {
	ChromeDriver driver;
	DevTools devTools;
	
	@BeforeTest
	public void setup() {
		driver=new ChromeDriver();
		devTools=driver.getDevTools();
		devTools.createSession();
		
	}
	
	
  //@Test
  public void deviceModeTest() {
	  Map deviceMetrics = new HashMap() {{
		  put("width",600);
		  put("height", 600);
		  put("deviceScaleFactor",50);
		  put("mobile", true);
	  }};
	  driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
	  driver.get("https://www.selenium.dev/");
	  
  }
  
 // @Test
  public void setGeoLocation() {
	  devTools.send(Emulation.setGeolocationOverride(Optional.of(31.968599), Optional.of(-99.901810), Optional.of(100)));
	  driver.get("https://oldnavy.gap.com/stores");
  }
  
  @Test
  public void basicAuthTest() {
	  devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
	  Map<String, Object> headers=new HashMap<>();
	  
	  
	  String basicAuth="Basic "+new String(new Base64().encode(String.format("%s:%s", "admin", "admin").getBytes()));
	  
	  System.out.println("Auth Token is..."+basicAuth);
	  headers.put("Authorization", basicAuth);
	  devTools.send(Network.setExtraHTTPHeaders(new  Headers(headers)));
	  driver.get("http://the-internet.herokuapp.com/basic_auth");
	  String strmsg=driver.findElement(By.cssSelector("div.example p")).getText();
	  Assert.assertEquals(strmsg, "Congratulations! You must have the proper credentials.");
  }
  
  @AfterTest
  public void tearDown() {
	  driver.close();
  }
}
