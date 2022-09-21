package com.revature.testcases;

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
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        usernameInput.sendKeys("jane_doe");
        passwordInput.sendKeys("pass123");

        //Step3: click login
        WebElement loginButton = driver.findElement(By.id("login-btn"));
        loginButton.click();

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
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        usernameInput.sendKeys("jane_doe");
        passwordInput.sendKeys("pass1123");

        WebElement loginButton = driver.findElement(By.id("login-btn"));
        loginButton.click();

        //Check expected vs. actual
        WebDriverWait wdw = new WebDriverWait(driver, Duration.ofSeconds(10)); //wait for a max of 10 secs
        wdw.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='error-message']/p")));

        //Create errorMessage WebElement
        WebElement errorMessage = driver.findElement(By.xpath("//div[@id='error-message']/p"));

        String actual = errorMessage.getText();
        String expected = "Username and/or password is incorrect";

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void invalidUsernameInvalidPassword(){
        driver.get("http://ec2-44-204-37-74.compute-1.amazonaws.com/index.html");

        //Login steps
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        usernameInput.sendKeys("jayne_doe");
        passwordInput.sendKeys("pass1123");

        WebElement loginButton = driver.findElement(By.id("login-btn"));
        loginButton.click();

        //Check expected vs. actual
        WebDriverWait wdw = new WebDriverWait(driver, Duration.ofSeconds(10)); //wait for a max of 10 secs
        wdw.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='error-message']/p")));

        //Create errorMessage WebElement
        WebElement errorMessage = driver.findElement(By.xpath("//div[@id='error-message']/p"));

        String actual = errorMessage.getText();
        String expected = "Username and/or password is incorrect";

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void invalidUsernameValidPassword(){
        driver.get("http://ec2-44-204-37-74.compute-1.amazonaws.com/index.html");

        //Login steps
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        usernameInput.sendKeys("jayne_doe");
        passwordInput.sendKeys("pass123");

        WebElement loginButton = driver.findElement(By.id("login-btn"));
        loginButton.click();

        //Check expected vs. actual
        WebDriverWait wdw = new WebDriverWait(driver, Duration.ofSeconds(10)); //wait for a max of 10 secs
        wdw.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='error-message']/p")));

        //Create errorMessage WebElement
        WebElement errorMessage = driver.findElement(By.xpath("//div[@id='error-message']/p"));

        String actual = errorMessage.getText();
        String expected = "Username and/or password is incorrect";

        Assert.assertEquals(actual, expected);
    }
}
