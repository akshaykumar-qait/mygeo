package NATgeo.mygeo;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.omg.CORBA.UserException;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Test1 {

	WebDriver driver;
	
	Test1help temp_Obj;
	WebElementUse useElements;
	Wait_for_element waitElements;
	DataReader data;
	

	@BeforeTest
	public void Initializer() throws IOException {

		temp_Obj = new Test1help();
		driver = new InitWebdriver().Browserdecider();
		useElements = new WebElementUse();
		waitElements = new Wait_for_element();
		data = new DataReader();
	
	}

	@Test
	public void TestA_user_invalid_login_check() throws IOException, InterruptedException {

		driver = temp_Obj.open_the_login_page(driver,data.readit("edubaseurl","urls"));

		driver = temp_Obj.login(driver, data.readit("eduid1", "ids"), data.readit("wrongpass", "password"));

		assertThat(driver.getCurrentUrl())
				.isEqualTo(data.readit("urlforinvalid2", "urls"));

		new WebElementUse().webElement_click_by_id(driver, data.readit("back_id", "locators"));

	}

	@Test(dependsOnMethods ="TestA_user_invalid_login_check")
	public void TestB_user_login_check() throws IOException, InterruptedException {

		waitElements.waits_by_id(driver, data.readit("edulogo_id", "locators"));
		useElements.webElement_click_by_id(driver, data.readit("edulogo_id", "locators"));

		driver = temp_Obj.login(driver, data.readit("eduid1", "ids"), data.readit("correctpassid1", "password"));

		assertThat(driver.getCurrentUrl().substring(0, 48))
				.isEqualTo(data.readit("urlforvalid_edu", "urls"));

	}
	
	
	@Test(dependsOnMethods ="TestB_user_login_check")
	public void TestC_user_logout_check() throws IOException, InterruptedException {

		
		waitElements.waits_by_css(driver,data.readit("logout_css","locators") );
		useElements.webElement_click_by_css(driver, data.readit("logout_css","locators") );

		
		assertThat(driver.getCurrentUrl())
				.isEqualTo(data.readit("edubaseurl","urls"));

	}
	
	
	@AfterTest
	public void Closer() throws IOException {

		driver.close();

	}
	
	
}
