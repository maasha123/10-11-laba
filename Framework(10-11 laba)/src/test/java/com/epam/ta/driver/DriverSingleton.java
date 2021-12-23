package com.epam.ta.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverSingleton {

    private static WebDriver driver;

    private DriverSingleton(){}

    public static WebDriver getDriver(){
        if (null == driver){
            switch (System.getProperty("browser")){
                case "firefox": {
                    WebDriverManager.firefoxdriver().version("0.30.0").setup();
                    FirefoxOptions options = new FirefoxOptions();
                    options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                    driver = new FirefoxDriver(options);
                    break;
                }
                default: {
                    WebDriverManager.chromedriver().version("96.0.4664.45").setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                    driver = new ChromeDriver(options);
                    break;
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}
