package framework.tests;

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

import frameworkpart1.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAlone {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage lpage=new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("aravindakumar1999@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Aravind@25");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*='mb-3']")));
		List<WebElement> products=driver.findElements(By.cssSelector("div[class*='mb-3']"));
		WebElement item=products.stream().filter(product -> 
		product.findElement(By.tagName("h5")).getText().equalsIgnoreCase("zara coat 3")).findFirst().orElse(null);
		item.findElement(By.cssSelector("button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-animating")));
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul[class*='ng-star']")));
		List<WebElement> cartitems=driver.findElements(By.cssSelector("ul[class*='ng-star']"));
		boolean present=cartitems.stream().anyMatch(cartitem -> cartitem.findElement(By.tagName("h3")).getText().equalsIgnoreCase("zara coat 3"));
	    Assert.assertTrue(present);
	    driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//select[@class='input ddl'])[1]")));
	    Select month=new Select(driver.findElement(By.xpath("(//select[@class='input ddl'])[1]")));
	    month.selectByVisibleText("10");
	    Select selectyears=new Select(driver.findElement(By.xpath("(//select[@class='input ddl'])[2]")));
	    List<WebElement> years=selectyears.getOptions();
	    WebElement syear=years.stream().filter(year -> year.getText().equals("24")).findFirst().orElse(null);
	    syear.click();
	    driver.findElement(By.xpath("(//input[@class='input txt'])[1]")).sendKeys("123");
	    driver.findElement(By.xpath("(//input[@class='input txt'])[2]")).sendKeys("Aravind");
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[class*='ng-star-inserted'] span")));
		List<WebElement> countries=driver.findElements(By.cssSelector("section[class*='ng-star-inserted'] span"));
		System.out.print(countries.size());
		WebElement country=countries.stream().filter(c -> c.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		country.click();
		
		/*
		 * CODE OF INSTRUCTOR
		 * Actions a=new Actions(driver);
		 * driver.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();
		 * driver.findElement(By.xpath("//to get the auto suggest list and select that using index")).click();
		 * 
		 */
		driver.findElement(By.cssSelector("a[class*='btnn action']")).click();
		

		

	}

}
