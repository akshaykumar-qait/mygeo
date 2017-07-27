package Tests.Login;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Readers.JsonReader;
import Readers.OptionReader;
import utility.Datadecider;
import utility.PathSetter;
import utility.Wait_for_element;
import utility.WebElementUse;

public class Login_help {
	
	WebElementUse useElements;
	Wait_for_element waitElements;
	Datadecider read;
	
	public Login_help() throws IOException {
		// TODO Auto-generated constructor stub
	useElements = new WebElementUse();
	waitElements = new Wait_for_element();
	read = new Datadecider();
	new PathSetter().setPath("resource/LoginTest/");
	
	}

	// useful
	public WebDriver open_the_login_page(WebDriver driver,String url) throws IOException {
		useElements.webElement_open_url(driver, url);

		waitElements.waits_by(driver, read.readit("edulogo_id", "locators"));

		useElements.webElement_click(driver, read.readit("edulogo_id", "locators"));

		waitElements.waits_by(driver, read.readit("edutextf_css", "locators"));

		return driver;
	}

	// useful
	public WebDriver login(WebDriver driver, String id, String password) throws InterruptedException, IOException {

		new Wait_for_element().waits_by(driver, read.readit("edutextf_css", "locators"));

		
		
		    driver.findElement(By.cssSelector("input.txtField")).clear();

		useElements.webElement_fill(driver, read.readit("edutextf_css", "locators"), id);

		useElements.webElement_fill(driver, read.readit("edupass_id", "locators"), password);

		useElements.webElement_click(driver, read.readit("edulogin_id","locators"));

		return driver;

	}

}
