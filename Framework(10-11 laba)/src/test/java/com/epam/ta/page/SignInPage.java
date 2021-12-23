package com.epam.ta.page;

import com.epam.ta.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class SignInPage extends AbstractPage{

    private final String PAGE_URL = "https://my.exness.com";

    private final By locatorLoginInput = By.id("login");

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//exwc-button/button[text()='Continue']")
    private WebElement continueButton;

    public SignInPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public SignInPage openPage(){
        driver.navigate().to(PAGE_URL);
        logger.info("SignInPage: openPage");
        return this;
    }

    public AccountPage signIn(User user){
        WebElement loginInput = wait.waitUntilpresenceOfElementLocated(locatorLoginInput);
        loginInput.sendKeys(user.getEmail());
        passwordInput.sendKeys(user.getPassword());
        continueButton.click();
        logger.info("SignInPage: signIn");
        return new AccountPage(driver);
    }
}
