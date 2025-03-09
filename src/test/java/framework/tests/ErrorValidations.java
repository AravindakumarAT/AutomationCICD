package framework.tests;

import java.io.IOException;
import java.time.Duration;
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
import org.testng.annotations.Test;

import framework.testcomponents.BaseTest;
import framework.testcomponents.Retry;
import frameworkpart1.pageobjects.CartPage;
import frameworkpart1.pageobjects.ConfirmationPage;
import frameworkpart1.pageobjects.LandingPage;
import frameworkpart1.pageobjects.OrderDetails;
import frameworkpart1.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidations extends BaseTest
{

	
		// TODO Auto-generated method stub
		@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
		public void LoginPageValidation()
		{
				
			ProductCatalogue productobj=landingPage.loginApplication("aravindakumar1999@gmail.com","Ar@25");
			Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
		
		
		/*
		 * CODE OF INSTRUCTOR
		 * Actions a=new Actions(driver);
		 * driver.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();
		 * driver.findElement(By.xpath("//to get the auto suggest list and select that using index")).click();
		 * 
		 */
		}
		@Test
		public void SubmitOrderValidation()
		{
			ProductCatalogue productobj=landingPage.loginApplication("aravindakumar1999@gmail.com","Aravind@25");
			String name="Zara Coat 3";
			List<WebElement> products=productobj.getProductsList();
			WebElement item=productobj.getProductName(name);
			productobj.addProductToCart(name);
			
			CartPage cart=productobj.goToCartPage();
			
			boolean present=cart.getProductName("Zara Coat 35");
		    Assert.assertFalse(present);
		}

}
