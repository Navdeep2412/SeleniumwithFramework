package LearningAutomation.SeleniumWithFramework;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Ecommerce1 {

	public static void main(String[] args)  throws InterruptedException {
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		LandingPage landingpage = new LandingPage(driver);
		landingpage.goTo();
		ProductCatalogue productcatalogue=landingpage.loginApp("ngill@email.com", "Patiala@11");
		productcatalogue.addProductToCart(productName);
		CartPage cartpage=productcatalogue.goToCartPage();
		Boolean match = cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectCountry();
		ConfirmationPage confirmationpage=checkoutpage.goToConfirmation();
		String messg=confirmationpage.verifyConfirmMessage();
		Assert.assertTrue(messg.equalsIgnoreCase("ThankYou for the Order."));
		
		driver.close();

	}

}
