package Tests.Google_Sheet1;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Base64;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import utility.InitWebdriver;
import utility.Wait_for_element;
import utility.WebElementUse;

public class JustCheck {

	WebElementUse use;

	@Test
	void just_check() throws IOException, InterruptedException, AWTException {
		use = new WebElementUse();
		WebDriver my = new InitWebdriver().Browserdecider();

		Robot myr = new Robot();
		myr.keyPress(20);
		my.get("https://s-www.myngconnect.com/selfRegistration/trialAccountSignUp.spr");

		new Wait_for_element().waits_by_id(my, "countryInput");

		WebElement mySelectElement = my.findElement(By.id("countryInput"));
		Select dropdown = new Select(mySelectElement);
		ArrayList<WebElement> mylist = (ArrayList<WebElement>) dropdown.getOptions();

		dropdown.selectByVisibleText("India");

		// my.findElement(By.id("edg2Ed")).click();

		my.navigate().refresh();

		// new Wait_for_element().waits_by_id(my, "stateInput");

		new Select(my.findElement(By.id("stateInput"))).selectByVisibleText("Kansas");
		;

		mySelectElement = my.findElement(By.id("countryInput"));
		dropdown = new Select(mySelectElement);

		dropdown.selectByVisibleText("India");
		
		
		
	
		
	}

	public static void main(String... S) throws IOException, ParseException {

		URL url = new URL("https://s-www.myngconnect.com/selfRegistration/trialAccountSignUp.spr");

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("GET");

		BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
		
		String line = br.readLine();
		while(line!=null)
		{
		
		
		
		if(line.contains("id"))
		{
			System.out.println(line.startsWith("id="));
			
		}
		
		line=br.readLine();
		

		}
		
		
		
	}
	
	
	
	

}
