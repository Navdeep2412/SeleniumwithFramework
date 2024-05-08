package LearningAutomation.SeleniumWithFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;
	
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(css=".mb-3")
	List<WebElement> products;
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	@FindBy(css="#toast-container")
	WebElement toast;
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	public WebElement getProductByName(String productName)
	{
		WebElement product = products.stream()
				.filter(prod -> prod.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return product;
	}
	public void addProductToCart(String productName)
	{
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toast);
		waitForElementToDisAppear(spinner);
		
		
	}
	
	

	

	

	

}
