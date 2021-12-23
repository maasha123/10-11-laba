package com.epam.ta.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage{

    private final String PAGE_URL = "https://exness.com";

    private final By locatorSignInButton = By.xpath("//div[@class='sidebar-tabs__buttons']/a");

    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public HomePage openPage(){
        driver.navigate().to(PAGE_URL);
        logger.info("HomePage: openPage");
        return this;
    }

    public SignInPage goToSignInPage(){
        WebElement signInButton = wait.waitUntilpresenceOfElementLocated(locatorSignInButton);
        String URLToClick = signInButton.getAttribute("href");
        driver.navigate().to(URLToClick);
        logger.info("HomePage: goToSignInPage");
        return new SignInPage(driver);
    }
}
