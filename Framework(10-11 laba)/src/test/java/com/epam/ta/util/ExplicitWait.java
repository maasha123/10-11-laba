package com.epam.ta.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ExplicitWait {

    private WebDriver driver;
    private final int WAIT_TIMEOUT_SECONDS;

    public ExplicitWait(WebDriver driver, int timeoutSeconds){
        this.driver = driver;
        this.WAIT_TIMEOUT_SECONDS = timeoutSeconds;
    }

    public WebElement waitUntilpresenceOfElementLocated(By locator){
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public List<WebElement> waitUntilpresenceOfAllElementsLocatedBy(By locator){
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

}
