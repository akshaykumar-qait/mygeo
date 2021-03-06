package Tests.JavaSciptTest;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Tests.Login.Login_help;
import utility.Datadecider;
import utility.InitWebdriver;
import utility.Wait_for_element;
import utility.WebElementUse;

@Test
public class ByJavaScript {

	WebDriver driver;
	Login_help temp_Obj;
	WebElementUse useElements;
	Wait_for_element waitElements;
	Datadecider data;

	@BeforeTest
	public void Initializer() throws IOException {

		temp_Obj = new Login_help();
		driver = new InitWebdriver().Browserdecider();
		useElements = new WebElementUse();
		waitElements = new Wait_for_element();
		data = new Datadecider();

	}

	@Test
	public void TestA_user_invalid_login_check() throws IOException, InterruptedException {

		driver = temp_Obj.open_the_login_page(driver, data.readit("edubaseurl", "urls"));

	

		driver = temp_Obj.login(driver, data.readit("eduid1", "ids"), data.readit("wrongpass", "password"));

		assertThat(driver.getCurrentUrl()).isEqualTo(data.readit("urlforinvalid2", "urls"));

		new WebElementUse().webElement_click(driver, data.readit("back_id", "locators"));

	}
	
	@Test(dependsOnMethods ="TestA_user_invalid_login_check")
	public void TestB_user_login_check() throws IOException, InterruptedException {

		waitElements.waits_by(driver, data.readit("edulogo_id", "locators"));
		useElements.webElement_click(driver, data.readit("edulogo_id", "locators"));

		driver = temp_Obj.login(driver, data.readit("eduid1", "ids"), data.readit("correctpassid1", "password"));

		assertThat(driver.getCurrentUrl().substring(0, 48))
				.isEqualTo(data.readit("urlforvalid_edu", "urls"));

	}
	
	
	@Test(dependsOnMethods ="TestB_user_login_check")
	public void TestC_user_logout_check() throws IOException, InterruptedException {
		waitElements.waits_by(driver,data.readit("logout_css","locators") );
		useElements.webElement_click(driver, data.readit("logout_css","locators") );

		
		assertThat(driver.getCurrentUrl())
				.isEqualTo(data.readit("edubaseurl","urls"));

	}
	
	
	@AfterTest
	public void Closer() throws IOException {

	//	driver.close();

	}

}
