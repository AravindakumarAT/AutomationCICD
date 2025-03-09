package frameworkpart1.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import frameworkpart1.pageobjects.CartPage;
import frameworkpart1.pageobjects.ConfirmationPage;
import frameworkpart1.pageobjects.OrderPage;

public class AbstractComponent 
{
	WebDriver driver;
	public AbstractComponent(WebDriver driver)
	{
		this.driver=driver;
	}
	By cart=By.cssSelector("button[routerlink='/dashboard/cart']");
	By orders=By.cssSelector("button[routerlink='/dashboard/myorders']");
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForElementToDisapper(By findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	public void waitForWebElementToAppear(WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public CartPage goToCart()
	{
		driver.findElement(cart).click();
		return new CartPage(driver);
	}
	public OrderPage goToOrders()
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(orders));
		driver.findElement(orders).click();
		return new OrderPage(driver);
	}
}
