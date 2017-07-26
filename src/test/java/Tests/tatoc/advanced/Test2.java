package Tests.tatoc.advanced;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Tests.Login.Login_help;
import utility.Datadecider;
import utility.InitWebdriver;
import utility.PathSetter;
import utility.Wait_for_element;
import utility.WebElementUse;

public class Test2 {

	WebDriver driver;
	Login_help temp_Obj;
	WebElementUse useElements;
	Wait_for_element waitElements;
	Datadecider data;
	String url;

	@BeforeTest
	public void Initializer() throws IOException {

		temp_Obj = new Login_help();
		driver = new InitWebdriver().Browserdecider();
		useElements = new WebElementUse();
		waitElements = new Wait_for_element();
		data = new Datadecider();
		new PathSetter().setPath("resource/TotacTest/Advanced/");
		url = data.readit("base_url", "urls");

	}

	@Test
	public void TestA_click_on_Menu2() throws IOException, InterruptedException {

		// 10 time tested the same code because the position of the red box
		// changes randomly
		useElements.webElement_open_url(driver, url + data.readit("hover_menu", "urls"));
		waitElements.waits_by_css(driver, ".menutop.m2");
		useElements.webElement_click_by_css(driver, ".menutop.m2");
		
		waitElements.waits_by_xpath(driver, "//span[@class='menuitem'][text()='Go Next']");
		useElements.webElement_click_by_xpath(driver, "//span[@class='menuitem'][text()='Go Next']");

	}

	// @Test
	public void TestA_check_the_database() throws IOException, InterruptedException {

		useElements.webElement_open_url(driver, url + data.readit("query_gate", "urls"));
		String datais = useElements.getText_by_id(driver, "symboldisplay");
		datais = "'" + datais + "'";

		System.out.println(datais);
		MySqlT obj = new MySqlT();
		String record[], record2[];

		record = obj.getResult("symbol", datais, "identity", 2).split(",");
		System.out.println(record[0]);

		record2 = obj.getResult("id", record[0], "credentials", 3).split(",");
		System.out.println(record2[1] + "  " + record2[2]);

		useElements.webElement_fill_by_id(driver, "name", record2[1]);
		useElements.webElement_fill_by_id(driver, "passkey", record2[2]);

		useElements.webElement_click_by_id(driver, "submit");
		waitElements.waits_by_linktext(driver, "Proceed");
		assertThat(driver.getCurrentUrl()).isEqualTo(url + data.readit("vedio_player", "urls"));

	}

}
