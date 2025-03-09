package frameworkpart1.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkpart1.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent
{
	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	
	/*@FindBy(css="div[class*='mb-3']")
	List<WebElement> products;*/
	
	By productsBy=By.cssSelector("div[class*='mb-3']");
	By addToCart=By.cssSelector("button:last-of-type");
	By toastMessage=By.id("toast-container");
	By spinner=By.className("ng-animating");
	
	
	public List<WebElement> getProductsList()
	{
		waitForElementToAppear(productsBy);
		return driver.findElements(productsBy);
	}
	public WebElement getProductName(String productName)
	{
		return getProductsList().stream().filter(product -> 
		product.findElement(By.tagName("h5")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
	}
	
	public void addProductToCart(String productName)
	{
		getProductName(productName).findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisapper(spinner);
	}
	
	public CartPage goToCartPage()
	{
		return goToCart();
	}
}

