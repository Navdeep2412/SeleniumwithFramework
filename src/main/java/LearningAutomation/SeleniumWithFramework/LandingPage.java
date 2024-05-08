package LearningAutomation.SeleniumWithFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	public WebDriver driver;
	public LandingPage(WebDriver driver) {
	super(driver);
		this.driver=driver;
	PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="userEmail")
	WebElement email;
	@FindBy(id="userPassword")
	WebElement passwordE;
	@FindBy(id="login")
	WebElement login;
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	 public ProductCatalogue loginApp(String uemail,String password)
	 {
	email.sendKeys(uemail);
	passwordE.sendKeys(password);
	login.click();
	return new ProductCatalogue(driver);
	 }
	 
	 public void goTo()
	 {
		 driver.get("https://rahulshettyacademy.com/client");
		 
	 }
	 public String getErrorMessage()
	  {
		 waitForElementToAppear(errorMsg);
		return  errorMsg.getText();
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
	 }
	

}
