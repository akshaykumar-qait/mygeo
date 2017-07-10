package utility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Readers.OptionReader;

public class InitWebdriver {

	protected WebDriver driver;
	
	
	
	public WebDriver Browserdecider() throws IOException
	{
		
		String getbrowservalue = new OptionReader().optionFileReader("browser");
		if(getbrowservalue.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "resources/Linux_drivers/chromedriver");
			
			driver = new ChromeDriver();

			return driver;
		}
		else if(getbrowservalue.equals("firefox"))
		{
			
			System.setProperty("webdriver.gecko.driver", "resources/Linux_drivers/geckodriver");
			
			driver = new FirefoxDriver();

			return driver;
		}
		
		
		return null;
		
	}
	
	
	
	
	
	
}
