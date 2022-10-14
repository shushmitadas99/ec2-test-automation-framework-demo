package com.revature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wdw;

    @FindBy(id="username")
    private WebElement usernameInput;

    @FindBy(xpath="//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(css="button[id='login-btn']")
    private WebElement loginButton;

    @FindBy(xpath="//div[@id='error-message']/p")
    private WebElement loginErrorMessageParagraph;

    public LoginPage(WebDriver driver) {  //constructor
        this.driver = driver;
        this.wdw = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void typeUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void typePassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getErrorMessage() {
        // We need to do an explicit wait here so that by the time we try to grab the text from the paragraph element,
        // it already exists
        wdw.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='error-message']/p")));
        return loginErrorMessageParagraph.getText();
    }
}
