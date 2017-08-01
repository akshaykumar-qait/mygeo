package utility;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class WebElementUse {

	public void webElement_open_url(WebDriver driver, String url) {
		driver.get(url);
	}

	public void webElement_click(WebDriver driver, String id) throws IOException {

		this.clickByjavascript(driver, id);

		// driver.findElement(new WebElementUse().Getbys(id)).click();
	}

	public void webElement_fill(WebDriver driver, String id, String keys) throws IOException {

		driver.findElement(new WebElementUse().Getbys(id)).sendKeys(keys);

	}

	public void webElement_hover_move(WebDriver driver, String css) throws IOException {
		// TODO Auto-generated method stub

		Actions action = new Actions(driver);

		action.moveToElement(driver.findElement(new WebElementUse().Getbys(css))).build().perform();

	}

	public String getText(WebDriver driver, String id) throws IOException {
		return driver.findElement(new WebElementUse().Getbys(id)).getText();
	}

	public void clickByjavascript(WebDriver driver, String id) {

		String value2[] = id.split("_");
		String locator = "";

		for (int index = 0; index < value2.length - 1; index++) {

			if (locator != "") {
				locator = locator + "_" + value2[index];
			} else

			{

				locator = locator + value2[index];
			}
		}

		try {

			if (id.endsWith("id"))
				((JavascriptExecutor) driver).executeAsyncScript("document.getElementById('" + locator + "').click()");

			else if (id.endsWith("css")) {

				System.out.println(
						"document.getElementsByClassName('" + locator.replace('.', ' ').trim() + "')[0].click()");

				((JavascriptExecutor) driver).executeAsyncScript(
						"document.getElementsByClassName('" + locator.replace('.', ' ').trim() + "')[0].click()");

			}

			else  if(id.endsWith("name")){
 
				System.out.println(
						"document.getElementsByName('" + locator.replace('.', ' ').trim() + "')[0].click()");

				((JavascriptExecutor) driver).executeAsyncScript(
						"document.getElementsByName("+locator+")[0].click()");

			}

		}

		catch (Exception ex) {

		}

	}

	By Getbys(String value) throws IOException {

		System.out.println("value is " + value);

		value.toLowerCase();
		String value2[] = value.split("_");
		String locator = "";

		for (int index = 0; index < value2.length - 1; index++) {

			if (locator != "")
				locator = locator + "_" + value2[index];

			else
				locator = locator + value2[index];
		}

		if (value2[value2.length - 1].equals("id")) {
			return By.id(locator);
		} else if (value2[value2.length - 1].equals("css")) {
			return By.cssSelector(locator);

		} else if (value2[value2.length - 1].equals("name")) {
			return By.name(locator);

		} else if (value2[value2.length - 1].equals("xpath")) {
			return By.xpath(locator);

		} else if (value2[value2.length - 1].equals("linktext")) {
			return By.linkText(locator);

		}

		return null;

	}

}
