package Stepdefinations;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import LearningAutomation.SeleniumWithFramework.BaseTest;
import LearningAutomation.SeleniumWithFramework.CartPage;
import LearningAutomation.SeleniumWithFramework.CheckoutPage;
import LearningAutomation.SeleniumWithFramework.ConfirmationPage;
import LearningAutomation.SeleniumWithFramework.LandingPage;
import LearningAutomation.SeleniumWithFramework.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Stepdefinitions_for_SO extends BaseTest {
public WebDriver driver;
	public LandingPage landingpage;
	public ProductCatalogue productcatalogue;
	public CartPage cartpage;
	public CheckoutPage checkoutpage;
	public ConfirmationPage confirmationpage;

	@Given("I landed on EcommercePage")
	public void I_Landed_On_EcommercePage() throws IOException {

		landingpage = launchApplication();

	}

	@Given("^Logged in with username (.*) and password (.*)$")
	public void Logged_In_With_Username_And_Password(String username, String password) {

		productcatalogue = landingpage.loginApp(username, password);

	}

	@When("^I add the product (.*) from cart$")
	public void I_Add_The_Product_From_Cart(String productName) {

		productcatalogue.addProductToCart(productName);

	}

	@And("^checkout (.*) and submit the order$")
	public void checkout_And_Submit_The_Order(String productName) throws InterruptedException {
		cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry();
		confirmationpage = checkoutpage.goToConfirmation();

	}

	@Then("{string} message is displayed on Confirmation Page")
	public void message_Is_Displayed_On_Confirmation_Page(String string) {

		String messg = confirmationpage.verifyConfirmMessage();
		Assert.assertTrue(messg.equalsIgnoreCase(string));

	}
	@Then("{string} message is displayed ")
	public void message_Is_Displayed(String string) {

		
		Assert.assertEquals(string,landingpage.getErrorMessage());

	}
}