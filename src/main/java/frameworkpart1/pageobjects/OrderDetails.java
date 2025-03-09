package frameworkpart1.pageobjects;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import frameworkpart1.AbstractComponents.AbstractComponent;

public class OrderDetails extends AbstractComponent
{
	WebDriver driver;
	
	public OrderDetails(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="(//select[@class='input ddl'])[1]")
	WebElement month;
	@FindBy(xpath="(//select[@class='input ddl'])[2]")
	WebElement year;
	@FindBy(xpath="(//input[@class='input txt'])[1]")
	WebElement cvv;
	@FindBy(xpath="(//input[@class='input txt'])[2]")
	WebElement name;
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	@FindBy(css="a[class*='btnn action']")
	WebElement placeOrder;
	
	By byMonth=By.xpath("(//select[@class='input ddl'])[1]");
	By byYear=By.xpath("(//select[@class='input ddl'])[2]");
	By byCountryDropDown=By.cssSelector("section[class*='ng-star-inserted'] span");
	public void creditCardDetails(String iM,String iY,String iCvv,String iName)
	{
		waitForElementToAppear(byMonth);
		Select mon=new Select(month);
	    mon.selectByVisibleText(iM);
	    Select selectyears=new Select(year);
	    List<WebElement> years=selectyears.getOptions();
	    WebElement syear=years.stream().filter(year -> year.getText().equals(iY)).findFirst().orElse(null);
	    syear.click();
	    cvv.sendKeys(iCvv);
	    name.sendKeys(iName);
	}
	
	public void addressDetails(String iCountry)
	{
		country.sendKeys(iCountry);
		waitForElementToAppear(byCountryDropDown);
		List<WebElement> countries=driver.findElements(byCountryDropDown);
		WebElement country=countries.stream().filter(c -> c.getText().equalsIgnoreCase(iCountry)).findFirst().orElse(null);
		country.click();
	}
	public ConfirmationPage goTo()
	{
		placeOrder.click();
		return new ConfirmationPage(driver);
	}
}
