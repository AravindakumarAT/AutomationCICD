package frameworkpart1.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import frameworkpart1.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent
{
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	
	By cartItems=By.cssSelector("ul[class*='ng-star']");
	By checkout=By.xpath("//button[contains(text(),'Checkout')]");
	public List<WebElement> cartItems()
	{
		waitForElementToAppear(cartItems);
		return driver.findElements(cartItems);
	}
	
	public boolean getProductName(String productName)
	{
		return cartItems().stream().anyMatch(cartitem -> cartitem.findElement(By.tagName("h3")).getText().equalsIgnoreCase(productName));
	}
	
	public OrderDetails goTo()
	{
		driver.findElement(checkout).click();
		return new OrderDetails(driver);
	}
	
	
}
