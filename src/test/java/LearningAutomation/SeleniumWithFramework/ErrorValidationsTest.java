package LearningAutomation.SeleniumWithFramework;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationsTest extends BaseTest {
	

	@Test
	public void Loginwrong() throws IOException
	{
		String productName = "ZARA COAT 3";
		LandingPage landingpage = launchApplication();
		ProductCatalogue productcatalogue = landingpage.loginApp("ngill@email.com", "Patiala@12");
		Assert.assertEquals("Incorrect email or password",landingpage.getErrorMessage());
		
	}

}
