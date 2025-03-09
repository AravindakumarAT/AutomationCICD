package framework.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.testcomponents.BaseTest;
import frameworkpart1.pageobjects.CartPage;
import frameworkpart1.pageobjects.ConfirmationPage;
import frameworkpart1.pageobjects.LandingPage;
import frameworkpart1.pageobjects.OrderDetails;
import frameworkpart1.pageobjects.OrderPage;
import frameworkpart1.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest extends BaseTest
{

	     //Updated Comment for CICD
		String name="Zara Coat 3";
		@Test(dataProvider="getData",groups={"Purchase"})
		public void submitOrder(HashMap<String,String> inp) throws IOException
		{
			ProductCatalogue productobj=landingPage.loginApplication(inp.get("email"),inp.get("password"));
			List<WebElement> products=productobj.getProductsList();
			WebElement item=productobj.getProductName(inp.get("name"));
			productobj.addProductToCart(inp.get("name"));
			
			CartPage cart=productobj.goToCartPage();
			
			boolean present=cart.getProductName(inp.get("name"));
		    Assert.assertTrue(present);
		    
		    OrderDetails orderDetails=cart.goTo();
		    orderDetails.creditCardDetails("12", "24", "123", "Aravind");
		    orderDetails.addressDetails("India");
		    
		    
		    ConfirmationPage cPage=orderDetails.goTo();
		    Assert.assertEquals(cPage.ConfirmationPage(),"THANKYOU FOR THE ORDER.");
		
		/*
		 * CODE OF INSTRUCTOR
		 * Actions a=new Actions(driver);
		 * driver.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();
		 * driver.findElement(By.xpath("//to get the auto suggest list and select that using index")).click();
		 * 
		 */
		}
		
		@Test(dependsOnMethods= {"submitOrder"})
		public void OrderHistoryTest()
		{
			ProductCatalogue productobj=landingPage.loginApplication("aravindakumar1999@gmail.com","Aravind@25");
			OrderPage orderPage=productobj.goToOrders();
			Assert.assertEquals(true, orderPage.getOrderName(name));
			
		}
		
		@DataProvider
		public Object[][] getData() throws IOException
		{
			List<HashMap<String,String>> data=JSONDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\framework\\data\\PurchaseOrder.json");
			
			return new Object[][] {{data.get(0)}};	
			
			/*HashMap<String,String> map=new HashMap<String,String>();
			map.put("email","aravindakumar1999@gmail.com");
			map.put("password", "Aravind@25");
			map.put("name","ZARA COAT 3");*/
		}
		

}
