package Tests.Google_Get_Access;

import static org.testng.Assert.fail;

import java.awt.Point;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

import Readers.OptionReader;
import utility.InitWebdriver;
import utility.Wait_for_element;

public class GetAccessKey {

	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private Wait_for_element waits;
	private OptionReader read;

	public GetAccessKey() throws Exception {
		driver = new InitWebdriver().Browserdecider();
		baseUrl = "https://developers.google.com/";
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		waits = new Wait_for_element();
		read = new OptionReader();
	}

	public void fetch_accesstoken() throws Exception {

		org.openqa.selenium.Point far = new org.openqa.selenium.Point(-10000, -10000);
		driver.manage().window().setPosition(far);
		driver.get(baseUrl + "/oauthplayground/");

		// waits.waits_by_id(driver,"step2TitlePrefix");
		Thread.sleep(2000);
		driver.findElement(By.id("step2TitlePrefix")).click();

		waits.waits_by(driver, "auth_code_id");
		driver.findElement(By.id("auth_code")).sendKeys(read.optionFileReader("google_auth_code"));
		driver.findElement(By.id("refresh_token")).sendKeys(read.optionFileReader("google_spreadsheet_refreshtoken"));
		driver.findElement(By.id("refreshAccessToken")).click();

		// System.out.println("hereeeeee
		// "+driver.findElement(By.id("responce")).getText());;

		waits.waits_contains_something(driver, "access_token_field_id");
		WebElement accesstoken = driver.findElement(By.id("access_token_field"));

		System.out.println(accesstoken.getAttribute("value"));

		read.writeit("accesstoken_googles_spreadsheet_api", accesstoken.getAttribute("value"));
		driver.close();
	}
}