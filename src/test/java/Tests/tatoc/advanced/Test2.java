package Tests.tatoc.advanced;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.omg.CORBA.Environment;
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
	public void TestA_on_hover_menu() throws IOException, InterruptedException {

		// 10 time tested the same code because the position of the red box
		// changes randomly
		useElements.webElement_open_url(driver, url + data.readit("hover_menu", "urls"));
		waitElements.waits_by_css(driver, ".menutop.m2");
		useElements.webElement_click_by_css(driver, ".menutop.m2");

		waitElements.waits_by_xpath(driver, "//span[@class='menuitem'][text()='Go Next']");
		useElements.webElement_click_by_xpath(driver, "//span[@class='menuitem'][text()='Go Next']");

	}

	@Test(dependsOnMethods = "TestA_on_hover_menu")
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

		//Thread.sleep(3000);
		useElements.webElement_click_by_id(driver, "submit");
		// waitElements.waits_by_linktext(driver, "Proceed");
		assertThat(driver.getCurrentUrl()).isEqualTo(url + data.readit("vedio_player", "urls"));

	}

	@Test(dependsOnMethods = "TestA_check_the_database")
	public void TestA_check_after_vedio_playback() throws Exception {

		useElements.webElement_open_url(driver, url + data.readit("after_vedio", "locators"));
		APIuse api = new APIuse();
		String session[] = useElements.getText_by_id(driver, "session_id").split(":");
		String token = api.gettoken(session[1].trim());
		// System.out.println(token);

		api.register(session[1].trim(), token);
		useElements.webElement_click_by_linkname(driver, "Proceed");
		waitElements.waits_by_linktext(driver, "Download File");
		assertThat(driver.getCurrentUrl()).isEqualTo(url + data.readit("file_handle", "urls"));

	}

	@Test(dependsOnMethods = "TestA_check_after_vedio_playback")
	public void TestA_check_file_handle() throws Exception {

		useElements.webElement_click_by_linkname(driver, "Download File");

	Thread.sleep(4000);

		
		
    	File f = new File("resource/downloads/file_handle_test.dat");

		BufferedReader b = new BufferedReader(new FileReader(f));

		String readLine = "";

		String arrsplit = null;
		while ((readLine = b.readLine()) != null) {

			arrsplit = arrsplit + readLine;

		}
		//f.delete();
	

		String session[] = arrsplit.split(":");

		System.out.println(session[session.length - 1].trim());

	useElements.webElement_fill_by_id(driver, "signature", session[session.length - 1].trim());

		useElements.webElement_click_by_css(driver, ".submit");
		assertThat(driver.getCurrentUrl()).isEqualTo(data.readit("totac_complete", "urls"));

	}

}
