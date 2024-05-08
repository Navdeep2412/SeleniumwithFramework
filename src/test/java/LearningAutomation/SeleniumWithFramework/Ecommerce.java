package LearningAutomation.SeleniumWithFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Ecommerce {

	public static void main(String[] args) throws InterruptedException {
		String productName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("ngill@email.com");
		driver.findElement(By.id("userPassword")).sendKeys("Patiala@11");
		driver.findElement(By.id("login")).click();
		
		List<WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
		WebElement product=products.stream().filter(prod->
		prod.findElement(By.cssSelector("b")).getText()
		.equalsIgnoreCase(productName)).findFirst().orElse(null);
		
		product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		//cartpage
		List<WebElement>cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartProducts.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//checkoutpage
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ta-results")));
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("window.scroll(0,500)");
	
		driver.findElement(By.xpath("//div[@class='actions']/a[contains(@class,'action__submit')]")).click();
		
		//confirmation
		String message=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(message.equalsIgnoreCase("ThankYou for the Order."));
		driver.close();
		
		
		

	}

}
