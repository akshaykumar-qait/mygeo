package Tests.Google_Get_Access;

import static org.testng.Assert.fail;

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
	  
	  
	  @BeforeClass(alwaysRun = true)
	  public void setUp() throws Exception {
	    driver = new InitWebdriver().Browserdecider();
	    baseUrl = "https://developers.google.com/";
	  //  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    waits = new Wait_for_element();
	  read = new OptionReader();
	  }

	  @Test
	  public void testHeya() throws Exception {
	    driver.get(baseUrl + "/oauthplayground/");
	    
	    	waits.waits_by_id(driver,"step2");
	    driver.findElement(By.id("step2")).click();
	    
	    
	    driver.findElement(By.id("auth_code")).sendKeys(read.optionFileReader("google_auth_code"));
	    driver.findElement(By.id("refresh_token")).sendKeys(read.optionFileReader("google_spreadsheet_refreshtoken"));
	    driver.findElement(By.id("refreshAccessToken")).click();
	    
	    //System.out.println("hereeeeee "+driver.findElement(By.id("responce")).getText());;
	    WebElement name = driver.findElement(By.id("access_token_field"));
	    Thread.sleep(4000);
	    System.out.println("hereeee"+name.getAttribute("value"));
	    
	    
//	    waits.waits_by_id(driver, "authorizeApisButton");
//
//	    
//	    driver.findElement(By.id("authorizeApisButton")).click();
//	    
//	   
//	    waits.waits_by_id(driver, "exchangeCode");
//
//	    
//	    driver.findElement(By.id("exchangeCode")).click();
//	    driver.findElement(By.id("exchangeCode")).click();
//	    driver.findElement(By.id("step2Title")).click();
	  }

//	  @AfterClass(alwaysRun = true)
//	  public void tearDown() throws Exception {
//	    driver.quit();
//	    String verificationErrorString = verificationErrors.toString();
//	    if (!"".equals(verificationErrorString)) {
//	      fail(verificationErrorString);
//	    }
//	  }/?code=4/J-R-0MjEsy_MY-ecbpMb2ZP_6GwZhFtUzGZW3i98LfU#
//
//	  private boolean isElementPresent(By by) {
//	    try {
//	      driver.findElement(by);
//	      return true;
//	    } catch (NoSuchElementException e) {
//	      return false;
//	    }
//	  }
//
//	  private boolean isAlertPresent() {
//	    try {
//	      driver.switchTo().alert();
//	      return true;
//	    } catch (NoAlertPresentException e) {
//	      return false;
//	    }
//	  }
//
//	  private String closeAlertAndGetItsText() {
//	    try {
//	      Alert alert = driver.switchTo().alert();
//	      String alertText = alert.getText();
//	      if (acceptNextAlert) {
//	        alert.accept();
//	      } else {
//	        alert.dismiss();
//	      }
//	      return alertText;
//	    } finally {
//	      acceptNextAlert = true;
//	    }
//	  }
	}
