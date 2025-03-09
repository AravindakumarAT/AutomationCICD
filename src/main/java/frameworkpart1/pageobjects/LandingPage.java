package frameworkpart1.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkpart1.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent
{
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail") //The code will create a above code.
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*=flyInOut]")
	WebElement errorMessage;

	public void goTo()
	{
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	public ProductCatalogue loginApplication(String email,String pass)
	{
		userEmail.sendKeys(email);
		password.sendKeys(pass);
		submit.click();
		return new ProductCatalogue(driver);
	}
	
}
