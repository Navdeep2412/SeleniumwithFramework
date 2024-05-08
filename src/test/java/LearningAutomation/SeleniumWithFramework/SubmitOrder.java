package LearningAutomation.SeleniumWithFramework;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrder extends BaseTest {
	


	//String productName = "ZARA COAT 3";
	//NN
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String>input) throws IOException, InterruptedException {
		
		LandingPage landingpage = launchApplication();
		ProductCatalogue productcatalogue = landingpage.loginApp(input.get("email"),input.get("pwd"));
		productcatalogue.addProductToCart(input.get("product"));
		CartPage cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry();
		ConfirmationPage confirmationpage = checkoutpage.goToConfirmation();
		String messg = confirmationpage.verifyConfirmMessage();
		Assert.assertTrue(messg.equalsIgnoreCase("ThankYou for the Order."));
		Thread.sleep(3000);	
	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() throws InterruptedException
	{
		String productName = "ZARA COAT 3";
	//	ProductCatalogue productcatalogue = landingpage.loginApp(input.get("email"),input.get("pwd"));
		ProductCatalogue productcatalogue = landingpage.loginApp("ngill@email.com", "Patiala@11");
		OrderPage orderpage=productcatalogue.goToOrderPage();
		Thread.sleep(4000);
	Assert.assertTrue(orderpage.verifyOrdertDisplay(productName));
	
		
	}
	//simple method and in submit order use(String email,String pwd, String productName) as arguments
	/*@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"ngill@email.com","Patiala@11","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	}*/
	
	
	//with hash map  use (HashMap<String,String>input) as an arguments and to call ,, input.get("email")
	/*@DataProvider
	public Object[][] getData()
	{
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("email","ngill@email.com");
		map.put("pwd","Patiala@11");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email","shetty@gmail.com");
		map1.put("pwd","Iamking@000");
		map1.put("product", "ADIDAS ORIGINAL");
		return new Object[][] {{map},{map1}};
	} */
	
	
	//with json file
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String,String>> data=getJsondataToMap("E:\\eclipse-workspace\\SeleniumWithFramework\\src\\test\\java\\data\\PurchaseOrder1.json");
		
		return new Object[][]{{data.get(0)},{data.get(1)}};
	}


}
