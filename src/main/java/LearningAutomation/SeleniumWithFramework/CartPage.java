package LearningAutomation.SeleniumWithFramework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
public CartPage(WebDriver driver) {
	
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

@FindBy(css=".cartSection h3")
List<WebElement>cartProducts;
@FindBy(css=".totalRow button")
WebElement checkout;
  public Boolean verifyProductDisplay(String productName)
  {
	  Boolean match=cartProducts.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productName));
  return match;
  }
  public CheckoutPage goToCheckout()
  {
	  checkout.click();
	  return new CheckoutPage(driver);
  }
		
  
}
