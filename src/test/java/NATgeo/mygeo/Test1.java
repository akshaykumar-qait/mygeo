package NATgeo.mygeo;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.sql.Driver;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test1 {

	WebDriver driver;

	@BeforeTest
	public void Initializer() throws IOException {

		driver = new InitWebdriver().Browserdecider();

	}

	@Test
	public void TestA_user_login_check() throws IOException, InterruptedException {

		new Back_Test1().TestA_user_login_check(driver);
		
	
	}

	@Test
	public void TestB_user_invalid_login_check() throws IOException, InterruptedException {

		
		new Back_Test1().TestB_user_invalid_login_check(driver);
	
	}
}
