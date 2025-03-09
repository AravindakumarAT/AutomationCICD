package framework.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import frameworkpart1.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest 
{
	public WebDriver driver;
	public 	LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException
	{
		//Properties Class
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\frameworkpart1\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName=null;
		if(System.getProperty("browser")!=null)
		{
			browserName=System.getProperty("browser");
		}
		else
		{
			browserName=System.getProperty("browser");
		}
		if(browserName.contains("chrome"))
		{
			ChromeOptions options=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver=new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browserName.contains("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();

		}
	//	driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver=initializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}
	
	public List<HashMap<String,String>> JSONDataToMap(String filePath) throws IOException
	{
		//JSON TO STRING
		String json=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//STRING TO HASHMAP
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(json,new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File destination=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source,destination);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	/*@AfterMethod
	public void close()
	{
		driver.close();
	}*/
	
}

