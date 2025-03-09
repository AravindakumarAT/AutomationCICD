package frameworkpart1.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import frameworkpart1.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent
{
	WebDriver driver;
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By orderItems=By.cssSelector("tr[class*='ng-star']");
	
	public List<WebElement> orderItems()
	{
		waitForElementToAppear(orderItems);
		return driver.findElements(orderItems);
	}
	
	public boolean getOrderName(String productName)
	{
		return orderItems().stream().anyMatch(cartitem -> cartitem.findElement(By.cssSelector("td:nth-child(3)")).getText().equalsIgnoreCase(productName));
	}
}
