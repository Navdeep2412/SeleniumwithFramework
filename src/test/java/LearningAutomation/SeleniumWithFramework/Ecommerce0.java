package LearningAutomation.SeleniumWithFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Ecommerce0 {

	public static void main(String[] args) throws InterruptedException {
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		LandingPage landingpage = new LandingPage(driver);
		landingpage.goTo();
		landingpage.loginApp("ngill@email.com", "Patiala@11");

		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		productcatalogue.addProductToCart(productName);
		productcatalogue.goToCartPage();

		CartPage cartpage = new CartPage(driver);
		Boolean match = cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		cartpage.goToCheckout();

		CheckoutPage checkoutpage = new CheckoutPage(driver);
		checkoutpage.selectCountry();
		checkoutpage.goToConfirmation();
		
		ConfirmationPage confirmationpage=new ConfirmationPage(driver);
		String messg=confirmationpage.verifyConfirmMessage();
		Assert.assertTrue(messg.equalsIgnoreCase("ThankYou for the Order."));
		
		driver.close();

	}

}
