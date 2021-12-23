package com.epam.ta.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TradingPage extends AbstractPage{

    private final String PAGE_URL = "https://my.exness.com/webtrading/";

    private final By locatorAddTab = By.className("add-tab-button");
    private final By locatorSearchInput = By.xpath("//input[@type='search']");
    private final By locatorSearchResult = By.xpath("//div[contains(@class,'watchlistItem')]");
    //private final By locatorInputLots = By.xpath("//*[contains(@class,'TradingPage__Order')]//input[contains(@value,'0')]");
    private final By locatorInputLots = By.xpath("//*[contains(@class,'TradingPage__Order')]//div[text()='lots']/preceding-sibling::input");
    private final By locatorBuyButton = By.xpath("//*[contains(@class,'OrderButton__Panel')]");
    private final By locatorNavMenu = By.xpath("//*[contains(@class,'navButton')]");
    private final By locatorControlButtons = By.xpath("//div[contains(@class,'close-button')]");
    private final By locatorTakeProfitInput = By.xpath("//div[contains(@class,'OrderModifyPopup')]//input");
    private final By locatorApplyTakeProfitButton = By.xpath("//div[contains(@class,'OrderModifyPopup')]//button");
    private final By locatorOrdersList = By.xpath("//*[contains(@class,'ScrollContainer')]/div");
    private final By locatorMoreInfo = By.xpath("//div[@class='buttons-container']/div/div[1]");
    private final By locatorTakeProfitValue = By.xpath("//*[text()='Take Profit']/following-sibling::div");
    private final By locatorAllTabs = By.xpath("//div[contains(@class,'asset-close')]");
    private final By locatorWatchlistInput = By.xpath("//div[contains(@class,'NavigationMenuPanel')]//input");
    private final By locatorStar = By.xpath("//div[contains(@class,'favoritesStar')]");
    private final By locatorCompaniesDropdown = By.xpath("//div[contains(@class,'NavigationMenuPanel')]//button");
    private final By locatorAllCompanies = By.xpath("//div[text()='All']");
    private final By locatorFaveCompanies = By.xpath("//div[text()='Favorites']");
    private final By locatorCompany = By.xpath("//div[contains(text(),'AAPL')]");
    private final By locatorFavesList = By.xpath("//div[contains(@class,'WatchList')]");

    public TradingPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public TradingPage addNewTab(String tabName){
        WebElement addTab = wait.waitUntilpresenceOfElementLocated(locatorAddTab);
        addTab.click();
        WebElement searchInput = wait.waitUntilpresenceOfElementLocated(locatorSearchInput);
        searchInput.sendKeys(tabName);
        WebElement searchResult = wait.waitUntilpresenceOfElementLocated(locatorSearchResult);
        searchResult.click();
        logger.info("TradingPage: addNewTab");
        return this;
    }

    public TradingPage buyLots(int amount){
        WebElement addLots = wait.waitUntilpresenceOfElementLocated(locatorInputLots);
        addLots.clear();
        addLots.sendKeys(Integer.toString(amount));
        WebElement buyButton = driver.findElement(locatorBuyButton);
        buyButton.click();
        driver.navigate().refresh();
        logger.info("TradingPage: buyLots");
        return this;
    }

    public TradingPage editLastLot(String takeProfitValue){
        List<WebElement> navMenu = wait.waitUntilpresenceOfAllElementsLocatedBy(locatorNavMenu);
        WebElement portfolioButton = navMenu.get(1);
        portfolioButton.click();
        WebElement editFirstOrderButton = wait.waitUntilpresenceOfElementLocated(locatorControlButtons);
        editFirstOrderButton.click();
        WebElement takeProfitInput = driver.findElement(locatorTakeProfitInput);
        takeProfitInput.clear();
        takeProfitInput.sendKeys(takeProfitValue);
        WebElement applyTakeProfitButton = driver.findElement(locatorApplyTakeProfitButton);
        applyTakeProfitButton.click();
        portfolioButton.click();
        logger.info("TradingPage: editLastLot");
        return this;
    }

    public TradingPage deleteAllLots(){
        List<WebElement> navMenu = wait.waitUntilpresenceOfAllElementsLocatedBy(locatorNavMenu);
        WebElement portfolioButton = navMenu.get(1);
        portfolioButton.click();
        int count = getOrdersCount();
        for (int i=0;i<count;i++){
            List<WebElement> controlButtons = wait.waitUntilpresenceOfAllElementsLocatedBy(locatorControlButtons);
            controlButtons.get(1).click();
        }
        portfolioButton.click();
        logger.info("TradingPage: deleteAllLots");
        return this;
    }

    public TradingPage addCompanyToFaves(String company){
        List<WebElement> navMenu = wait.waitUntilpresenceOfAllElementsLocatedBy(locatorNavMenu);
        WebElement watchListButton = navMenu.get(0);
        watchListButton.click();
        WebElement dropDown = wait.waitUntilpresenceOfElementLocated(locatorCompaniesDropdown);
        dropDown.click();
        WebElement allCompanies = wait.waitUntilpresenceOfElementLocated(locatorAllCompanies);
        allCompanies.click();
        WebElement input = driver.findElement(locatorWatchlistInput);
        input.clear();
        input.sendKeys(company);
        WebElement star = wait.waitUntilpresenceOfElementLocated(locatorStar);
        star.click();
        watchListButton.click();
        logger.info("TradingPage: addCompanyToFaves");
        return this;
    }

    public boolean isCompanyInFaves(){
        List<WebElement> navMenu = wait.waitUntilpresenceOfAllElementsLocatedBy(locatorNavMenu);
        WebElement watchListButton = navMenu.get(0);
        watchListButton.click();
        WebElement dropDown = wait.waitUntilpresenceOfElementLocated(locatorCompaniesDropdown);
        dropDown.click();
        WebElement faveCompanies = wait.waitUntilpresenceOfElementLocated(locatorFaveCompanies);
        faveCompanies.click();
        wait.waitUntilpresenceOfElementLocated(locatorFavesList);
        int size = driver.findElements(locatorCompany).size();
        logger.info("TradingPage: isCompanyInFaves");
        return (size != 0);
    }

    public int getOrdersCount(){
        List<WebElement> navMenu = wait.waitUntilpresenceOfAllElementsLocatedBy(locatorNavMenu);
        WebElement portfolioButton = navMenu.get(1);
        portfolioButton.click();
        List<WebElement> ordersList = driver.findElements(locatorOrdersList);
        portfolioButton.click();
        logger.info("TradingPage: getOrdersCount");
        return ordersList.size();
    }

    public int getAllTabsCount(){
        List<WebElement> allTabs = wait.waitUntilpresenceOfAllElementsLocatedBy(locatorAllTabs);
        logger.info("TradingPage: getAllTabsCount");
        return allTabs.size();
    }

    public String getTakeProfitValueOfLastOrder(){
        List<WebElement> navMenu = wait.waitUntilpresenceOfAllElementsLocatedBy(locatorNavMenu);
        WebElement portfolioButton = navMenu.get(1);
        portfolioButton.click();
        driver.findElement(locatorMoreInfo).click();
        String takeProfitValue = driver.findElement(locatorTakeProfitValue).getText().replace(",","");
        driver.findElement(locatorMoreInfo).click();
        portfolioButton.click();
        logger.info("TradingPage: getTakeProfitValueOfLast");
        return takeProfitValue;
    }

    @Override
    protected AbstractPage openPage() {
        driver.navigate().to(PAGE_URL);
        logger.info("TradingPage: openPage");
        return this;
    }
}
