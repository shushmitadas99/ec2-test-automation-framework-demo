package com.revature.testcases;

import com.revature.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests {
    public static WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }

    @Test
    public void validLogin() {
        //Step 1: go to login page
        driver.get("http://ec2-44-204-37-74.compute-1.amazonaws.com/index.html");

        //Step 2: enter username and password
        LoginPage loginPage = new LoginPage(driver);

        loginPage.typeUsername("jane_doe");
        loginPage.typePassword("pass123");

//        usernameInput.sendKeys();
//        passwordInput.sendKeys();

        //Step3: click login
        loginPage.clickLoginButton();

        //Check expected vs. actual
        //See what URL you're on
        WebDriverWait wdw = new WebDriverWait(driver, Duration.ofSeconds(10)); //wait for a max of 10 secs
        // check every 500ms to see if the expected condition occurred
        wdw.until(ExpectedConditions.urlContains("success.html"));

        String actual = driver.getCurrentUrl();
        String expected = "http://ec2-44-204-37-74.compute-1.amazonaws.com/success.html";

        Assert.assertEquals(actual, expected);
    }

    //Exercise assigned by Bach: Do the negative testcases
    @Test
    public void validUsernameInvalidPassword(){
        driver.get("http://ec2-44-204-37-74.compute-1.amazonaws.com/index.html");

        //Login steps
        LoginPage loginPage = new LoginPage(driver);

        loginPage.typeUsername("jane_doe");
        loginPage.typePassword("pass1234");
        loginPage.clickLoginButton();


        //Check expected vs. actual
        String actual = loginPage.getErrorMessage();
        String expected = "Username and/or password is incorrect";

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void invalidUsernameInvalidPassword(){
        driver.get("http://ec2-44-204-37-74.compute-1.amazonaws.com/index.html");

        //Login steps
        LoginPage loginPage = new LoginPage(driver);

        loginPage.typeUsername("jayne_doe");
        loginPage.typePassword("pass1123");
        loginPage.clickLoginButton();

        //Check expected vs. actual
        String actual = loginPage.getErrorMessage();
        String expected = "Username and/or password is incorrect";

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void invalidUsernameValidPassword(){
        driver.get("http://ec2-44-204-37-74.compute-1.amazonaws.com/index.html");

        //Login steps
        LoginPage loginPage = new LoginPage(driver);

        loginPage.typeUsername("jayne_doe");
        loginPage.typePassword("pass123");
        loginPage.clickLoginButton();

        //Check expected vs. actual
        String actual = loginPage.getErrorMessage();
        String expected = "Username and/or password is incorrect";

        Assert.assertEquals(actual, expected);
    }
}
