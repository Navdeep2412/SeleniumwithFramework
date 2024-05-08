package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import LearningAutomation.SeleniumWithFramework.CartPage;
import LearningAutomation.SeleniumWithFramework.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	
	public void waitForElementToAppear(WebElement el)
	{
		wait.until(ExpectedConditions.visibilityOf(el));
	}
	public void waitForElementToDisAppear(WebElement ele)
	{
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public CartPage goToCartPage()
	{
		cart.click();
		return new CartPage(driver);
		
	}
	public OrderPage goToOrderPage()
	{
		orderHeader.click();
		return new OrderPage(driver);
		
	}
}
