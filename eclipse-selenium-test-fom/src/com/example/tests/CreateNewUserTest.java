package com.example.tests;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;


public class CreateNewUserTest {
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
  public void createNewUser() {
	goToMainPage();
	loginAs("admin", "admin");
	gotoUserManagmentConsole();
	createUser("user1", "user1@test.com", "password");
	logout();
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
  
  private void gotoUserManagmentConsole() {
		driver.findElement(By.linkText("User management")).click();
	}
    
  private void createUser(String username, String email, String password) {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("password2")).clear();
		driver.findElement(By.id("password2")).sendKeys(password);
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