package com.epam.ta.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CurrencyConverterPage extends AccountPage{

    private final String PAGE_URL = "https://www.exness.com/en/converter";

    private final By locatorAmountInput = By.xpath("//*[text()='Amount']/preceding-sibling::input");
    private final By locatorAllAmountInputs = By.xpath("//*[@class='inp-group__case']//input");

    public CurrencyConverterPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public CurrencyConverterPage setAmount(String amount){
        WebElement amountInput = wait.waitUntilpresenceOfElementLocated(locatorAmountInput);
        amountInput.clear();
        amountInput.sendKeys(amount);
        logger.info("CurrencyConverterPage: setAmount");
        return this;
    }

    public boolean areAllInputsSetToNull(){
        List<WebElement> allAmountInputs = wait.waitUntilpresenceOfAllElementsLocatedBy(locatorAllAmountInputs);
        for(int i=0; i<allAmountInputs.size();i++){
            if(!allAmountInputs.get(i).getAttribute("value").equals("0")){
                return false;
            }
        }
        logger.info("CurrencyConverterPage: areAllInputsSetToNull");
        return true;
    }

    @Override
    public CurrencyConverterPage openPage(){
        driver.navigate().to(PAGE_URL);
        logger.info("CurrencyConverterPage: openPage");
        return this;
    }
}
