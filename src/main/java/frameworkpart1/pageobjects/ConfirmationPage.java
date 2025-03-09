package frameworkpart1.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkpart1.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent
{
	WebDriver driver;
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By confirmPage=By.cssSelector(".hero-primary");
	
	public String ConfirmationPage()
	{
		waitForElementToAppear(confirmPage);
		return driver.findElement(confirmPage).getText();
	}
	
	
}
