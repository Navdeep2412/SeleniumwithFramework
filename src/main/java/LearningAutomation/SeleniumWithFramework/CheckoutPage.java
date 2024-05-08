package LearningAutomation.SeleniumWithFramework;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
 WebDriver driver;
 
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	@FindBy(css=".ta-results")
	WebElement results;
	
@FindBy(xpath="(//button[@type='button'])[2]")
WebElement selectcountry;

@FindBy(xpath=("//div[@class='actions']/a[contains(@class,'action__submit')]"))
WebElement submit;
	public void selectCountry() throws InterruptedException
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, "india").build().perform();
		waitForElementToAppear(results);
		selectcountry.click();
		Thread.sleep(3000);
		waitForElementToDisAppear(results);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,500)");
	}
	public ConfirmationPage goToConfirmation()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}
	
	
	
	
	
	
	
	
	
}
