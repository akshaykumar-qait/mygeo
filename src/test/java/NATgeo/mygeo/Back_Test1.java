package NATgeo.mygeo;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Back_Test1 {

	public void TestA_user_login_check(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, 20);

		driver.get("http://s-www.myngconnect.com/login/chooseMainUI.spr");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("imgEducators")));

		driver.findElement(By.id("imgEducators")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.txtField")));
		driver.findElement(By.cssSelector("input.txtField")).sendKeys("LegacyInsideStage1499326371262@qaittest.com");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordField")));
		driver.findElement(By.id("passwordField")).sendKeys("password");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("imgLogin")));

		driver.findElement(By.id("imgLogin")).click();
		
		assertThat(driver.getCurrentUrl().substring(0, 48))
		.isEqualTo("https://s-www.myngconnect.com/html/ecosystem.spr");
		
		driver.close();


	}
	
	
	
	public void TestB_user_invalid_login_check(WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);


		driver.get("http://s-www.myngconnect.com/login/chooseMainUI.spr");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("imgEducators")));
		driver.findElement(By.id("imgEducators")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.txtField")));
		driver.findElement(By.cssSelector("input.txtField")).sendKeys("LegacyInsideStage1499326371262@qaittest.com");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordField")));
		
		driver.findElement(By.id("passwordField")).sendKeys("password2");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("imgLogin")));
		driver.findElement(By.id("imgLogin")).click();
		
		assertThat(driver.getCurrentUrl())
		.isEqualTo("https://s-www.myngconnect.com/login/teacher/login.spr?login_error=2");

		driver.close();
		
	}

}
