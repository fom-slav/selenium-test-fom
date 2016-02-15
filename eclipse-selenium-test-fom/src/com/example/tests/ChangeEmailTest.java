package com.example.tests;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;


public class ChangeEmailTest {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = ru.stqa.selenium.factory.WebDriverFactory.getDriver(DesiredCapabilities.firefox());
    baseUrl = "http://localhost/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  

  @Test
  public void testEmailChange() {
	goToMainPage();
    loginAs("admin", "admin");
    gotoUserPrifile();
    changeEmailTo("admin@admin.ru");
    assertTrue(isOnUserManagmentPage());
    logout();
  }

  
  private boolean isOnUserManagmentPage() {
	return driver.findElement(By.className("content"))
			.findElement(By.tagName("h2")).getText().equals("User management");
  }

  private void goToMainPage() {
		// open main page
	    driver.get(baseUrl + "/php4dvd/");
  }
  
  private void loginAs(String username, String password) {
		// login
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys(username);
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys(password);
	    driver.findElement(By.name("submit")).click();
	}

  private void gotoUserPrifile() {
	    driver.findElement(By.linkText("My profile")).click();
	}
  
  private void changeEmailTo(String string) {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("admin@admin.ru");
		driver.findElement(By.name("submit")).click();
	}
  
  private void logout() {
		// logout
	    driver.findElement(By.linkText("Log out")).click();
	    driver.switchTo().alert().accept();
	}

  @After
  public void tearDown() throws Exception {
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}