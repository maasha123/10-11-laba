package com.epam.ta.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.lang.String;

public class AccountPage extends AbstractPage{

    private final String PAGE_URL = "https://my.exness.com";

    @FindBy(id = "amount")
    private WebElement inputAreaBalance;

    @FindBy(id = "alias")
    private WebElement inputAreaName;

    @FindBy(xpath = "//form/button")
    private WebElement saveChanges;

    @FindBy(xpath = "//button[text()='Rename Account']")
    private WebElement renameAccout;

    private final By locatorDemoTab=By.xpath("//div[contains(@class,'Tabs_container')]/div[contains(text(),'Demo')]");
    private final By locatorSetBalance = By.xpath("//button[contains(text(),'Set Balance')]");
    private final By locatorBalance = By.xpath("//div/span[contains(@class,'Money_balanceLarge')]");
    private final By locatorTrade = By.xpath("//div[contains(@class,'AccountCards')]/button[text()='Trade']");
    private final By locatorExnessTerminal = By.xpath("//a[contains(@class,'ApplicationsLinks')]");
    private final By locatorTraidingPageBody = By.xpath("//*[@id='tradingPage']");
    private final By locatorSettings = By.xpath("//div[contains(@class,'AccountCogMenu')]");
    private final By locatorRename = By.xpath("//li/span[text()='Rename account']");
    private final By locatorAccName = By.xpath("//div[contains(@class,'AccountCards_topText')]/div");
    private final By locatorHelp = By.xpath("//*[contains(@class,'HeaderDropDown_component')]");
    private final By locatorCurrencyConverter = By.xpath("//a[text()='Currency Converter']");
    private final By locatorTradersCalculator = By.xpath("//a[text()=\"Trader's Calculator\"]");

    public AccountPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public AccountPage openDemoTab(){
        WebElement demoTab = wait.waitUntilpresenceOfElementLocated(locatorDemoTab);
        demoTab.click();
        logger.info("AccountPage: openDemoTab");
        return this;
    }

    public int getBalance(){
        openDemoTab();
        WebElement balance = wait.waitUntilpresenceOfElementLocated(locatorBalance);
        String balanceValue = balance.getAttribute("innerHTML").replaceAll(",","");
        logger.info("AccountPage: getBalance");
        return Integer.parseInt(balanceValue);
    }

    public AccountPage setBalance(int balance){
        openDemoTab();
        WebElement setBalanceButton = wait.waitUntilpresenceOfElementLocated(locatorSetBalance);
        setBalanceButton.click();
        inputAreaBalance.clear();
        inputAreaBalance.sendKeys(Integer.toString(balance));
        saveChanges.click();
        driver.navigate().refresh();
        logger.info("AccountPage: setBalance");
        return this;
    }

    public String getAccountName(){
        openDemoTab();
        List<WebElement> name = wait.waitUntilpresenceOfAllElementsLocatedBy(locatorAccName);
        String fullName = name.get(1).getText().toString();
        logger.info("AccountPage: getAccountName");
        return fullName.substring(0,fullName.indexOf(' '));
    }

    public AccountPage renameAccount(String name){
        openDemoTab();
        List<WebElement> settings = wait.waitUntilpresenceOfAllElementsLocatedBy(locatorSettings);
        settings.get(1).click();
        driver.findElements(locatorRename).get(1).click();
        inputAreaName.clear();
        inputAreaName.sendKeys(name);
        renameAccout.click();
        driver.navigate().refresh();
        logger.info("AccountPage: renameAccount");
        return this;
    }

    public TradingPage startTraiding(){
        openDemoTab();
        WebElement trade = wait.waitUntilpresenceOfElementLocated(locatorTrade);
        trade.click();
        WebElement tradeButton = wait.waitUntilpresenceOfElementLocated(locatorExnessTerminal);
        String URLToClick = tradeButton.getAttribute("href");
        driver.navigate().to(URLToClick);
        wait.waitUntilpresenceOfElementLocated(locatorTraidingPageBody);
        logger.info("AccountPage: startTraiding");
        return new TradingPage(driver);
    }

    public AccountPage openHelpDropDown(){
        WebElement help = wait.waitUntilpresenceOfElementLocated(locatorHelp);
        help.click();
        logger.info("AccountPage: openHelpDropDown");
        return this;
    }

    public CurrencyConverterPage openCurrencyConverter(){
        WebElement currencyConverter = wait.waitUntilpresenceOfElementLocated(locatorCurrencyConverter);
        String URLToClick = currencyConverter.getAttribute("href");
        driver.navigate().to(URLToClick);
        logger.info("AccountPage: openCurrencyConverter");
        return new CurrencyConverterPage(driver);
    }

    public TradersCalculatorPage openTradersCalculator(){
        WebElement tradersCalculator = wait.waitUntilpresenceOfElementLocated(locatorTradersCalculator);
        String URLToClick = tradersCalculator.getAttribute("href");
        driver.navigate().to(URLToClick);
        logger.info("AccountPage: openTradersCalculator");
        return new TradersCalculatorPage(driver);
    }

    @Override
    public AccountPage openPage(){
        driver.navigate().to(PAGE_URL);
        logger.info("AccountPage: openPage");
        return this;
    }
}
