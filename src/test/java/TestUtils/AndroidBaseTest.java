package TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.RSA.AppiumFrameworkDesign.FormPage;
import com.RSA.AppiumFrameworkDesign.utils.AppiumUtils;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndroidBaseTest extends AppiumUtils{
	public AndroidDriver driver;
	
	public FormPage formPage;
	
	@BeforeClass(alwaysRun=true)
	public void coinfigureAppium() throws URISyntaxException, IOException{
		//code to start server
				//Adnroid Driver -> Appium Server --> mobile
				
				
		//To invoke Appium server 
		Properties prop =new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//data.properties");
		String ipAddress = System.getProperty("ipAddress")!=null? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		prop.load(fis);
		//String ipAddress = prop.getProperty("ipAdress");
		String port = prop.getProperty("port");
		service=startAppiumServer(ipAddress,Integer.parseInt(port));
		
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("AndroidDeviceNames"));
		options.setChromedriverExecutable("D://Softwares//ChromeDriver//Chromedriver_83//chromedriver.exe");
		options.setApp("D://Appium//AppiumFrameWork//AppiumQA//src//test//resources//resources//General-Store.apk");
		//options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\resourcess\\General-Store.apk");
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);
		
	}
	public void longPressAction(WebElement ele) {
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),
						"duration",2000));
	}
	public void scrollToEndAction() {
		 boolean canScrollMore;
		 do{
			 canScrollMore= (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
		 
				 "left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));
	}while(canScrollMore);
				 
	}
	
	public void swipeAction(WebElement ele, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile:swipeGesture", ImmutableMap.of(
				"elementId",((RemoteWebElement)ele).getId(),
			
			 "direction", direction,
			 "percent", 0.75
			 ));
	}
	
	public Double getFormattedAmount(String amount)
	{
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}
	
	
	
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.quit();
		service.close();
		//stop server
			
	}

}
