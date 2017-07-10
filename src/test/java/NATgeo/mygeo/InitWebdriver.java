package NATgeo.mygeo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InitWebdriver {

	protected WebDriver driver;
	
	
	
	WebDriver Browserdecider() throws IOException
	{
		
		String getbrowservalue = optionFileReader("browser");
		if(getbrowservalue.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "resources/Linux_drivers//chromedriver");
			
			driver = new ChromeDriver();

			return driver;
		}
		else if(getbrowservalue.equals("firefox"))
		{
			
			System.setProperty("webdriver.gecko.driver", "resources/Linux_drivers//geckodriver");
			
			driver = new FirefoxDriver();

			return driver;
		}
		
		
		return null;
		
	}
	
	String optionFileReader(String optionKey) throws IOException
	{
		File f = new File("resources/data.properties");

		BufferedReader b = new BufferedReader(new FileReader(f));

		String readLine = "";boolean flag = false;

		String arrsplit[],temp[] = null;
		while ((readLine = b.readLine()) != null) {
			arrsplit = readLine.split("=");

			if (arrsplit[0].equals(optionKey)) {
				flag=true;
				temp=readLine.split("=");
			}
	
		}

		
		if(flag==true)
		{
			
			return temp[1].trim().toLowerCase();
		}
		return null;

		
	}
	
	
	
	
	
}
