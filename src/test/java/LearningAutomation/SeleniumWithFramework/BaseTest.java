package LearningAutomation.SeleniumWithFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	LandingPage landingpage;

	public WebDriver iniatializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"E:\\eclipse-workspace\\SeleniumWithFramework\\src\\test\\java\\Resources1\\GlobalData1.properties");
		prop.load(fis);
		String browsername=System.getProperty("browser")!=null ? 
				System.getProperty("browser"):prop.getProperty("browser"); //for maven build while doing in cmd and jenkins
	//	String browsername = prop.getProperty("browser");//only for globaldata properties file
	//commenting for automation
	//again
		if (browsername.contains("chrome")) 
		{
			ChromeOptions options=new ChromeOptions(); //for jenkins headless
			
			WebDriverManager.chromedriver().setup();
			if(browsername.contains("headless"))
			{
				options.addArguments("headless");
		} 
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if (browsername.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		}
		
		else if (browsername.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
		
	}

	@BeforeMethod
	public LandingPage launchApplication() throws IOException {

		WebDriver driver = iniatializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}

	public List<HashMap<String, String>> getJsondataToMap(String filepath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source,
				new File("E:\\eclipse-workspace\\SeleniumWithFramework\\src\\test\\java\\data\\reports" + testCaseName
						+ ".png"));
		return "E:\\eclipse-workspace\\SeleniumWithFramework\\src\\test\\java\\data\\reports" + testCaseName + ".png";
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		driver.quit();
	}

}
