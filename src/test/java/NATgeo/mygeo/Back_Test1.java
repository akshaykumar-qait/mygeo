package NATgeo.mygeo;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Back_Test1 {

	public void TestA_user_login_check(WebDriver driver) {

		driver = this.open_the_login_page(driver);

		driver = this.login(driver, "LegacyInsideStage1499326371262@qaittest.com", "password");

		assertThat(driver.getCurrentUrl().substring(0, 48))
				.isEqualTo("https://s-www.myngconnect.com/html/ecosystem.spr");

		driver.close();

	}

	public void TestB_user_invalid_login_check(WebDriver driver) {

		driver = this.open_the_login_page(driver);

		driver = this.login(driver, "LegacyInsideStage1499326371262@qaittest.com", "password2");

		assertThat(driver.getCurrentUrl())
				.isEqualTo("https://s-www.myngconnect.com/login/teacher/login.spr?login_error=2");

		driver.close();

	}

	// usefull
	public WebDriver open_the_login_page(WebDriver driver) {
		new WebElementUse().webElement_open_url(driver, "http://s-www.myngconnect.com/login/chooseMainUI.spr");

		new Wait_for_element().waits_by_id(driver, "imgEducators");

		new WebElementUse().webElement_click_by_id(driver, "imgEducators");

		new Wait_for_element().waits_by_css(driver, "input.txtField");

		return driver;
	}

	// usefull
	public WebDriver login(WebDriver driver, String id, String password) {

		new WebElementUse().webElement_fill_by_css(driver, "input.txtField", id);

		new Wait_for_element().waits_by_id(driver, "passwordField");

		new WebElementUse().webElement_fill_by_id(driver, "passwordField", password);

		new Wait_for_element().waits_by_id(driver, "imgLogin");

		new WebElementUse().webElement_click_by_id(driver, "imgLogin");

		return driver;

	}

}
