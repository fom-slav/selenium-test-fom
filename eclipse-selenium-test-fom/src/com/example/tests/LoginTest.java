package com.example.tests;

import java.util.concurrent.TimeUnit;
import org.junit.*;

import static org.junit.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.pages.InternalPage;
import com.example.pages.LoginPage;


public class LoginTest {
	
  private WebDriver driver;
  private WebDriverWait wait;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();
  private LoginPage loginPage;
  private InternalPage somePage;

  @Before
  public void setUp() throws Exception {
    driver = ru.stqa.selenium.factory.WebDriverFactory.getDriver(DesiredCapabilities.firefox());
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wait= new WebDriverWait(driver, 10);
    baseUrl = "http://localhost/";
  }
  
  @Test
  public void testLoginLogout() throws Exception {
	  goToMainPage();
	  loginAs("admin", "admin");
	  logout();
	  assertTrue("Нет формы логина!", loginPage.isOnThisPage());
  }
  
private void goToMainPage() {
	    driver.get(baseUrl + "/php4dvd/");
	    loginPage = PageFactory.initElements(driver, LoginPage.class);
	    somePage = PageFactory.initElements(driver, InternalPage.class);
	}

  private void loginAs(String username, String password) {
	  loginPage.userNameField.clear();
	  loginPage.userNameField.sendKeys(username);
	  loginPage.passwordField.clear();
	  loginPage.passwordField.sendKeys(password);
	  loginPage.loginButton.click();
	}
  
  private void logout() {
	  somePage.menuLogoutLink.click();
	  wait.until(alertIsPresent()).accept();
	} 

  @After
  public void tearDown() throws Exception {
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}