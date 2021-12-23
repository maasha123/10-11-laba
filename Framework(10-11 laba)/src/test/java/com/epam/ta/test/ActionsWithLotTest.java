package com.epam.ta.test;

import com.epam.ta.model.User;
import com.epam.ta.page.HomePage;
import com.epam.ta.page.TradingPage;
import com.epam.ta.service.UserCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ActionsWithLotTest extends CommonConditions{
    private final String TAB_NAME = "BTC/USD";
    private final String COMPANY_NAME = "APPL";
    private final String TAKE_PROFIT_VALUE = "1000000.88";
    private final int LOTS_AMOUNT = 1;

    TradingPage tradingPage;

    @Test(description = "add tab", priority = 1)
    public void addTab(){
        tradingPage = new HomePage(driver)
                .openPage()
                .goToSignInPage()
                .signIn(TEST_USER)
                .startTraiding();

        int initialCount = tradingPage.getAllTabsCount();
        tradingPage = tradingPage.addNewTab(TAB_NAME);
        int finalCount = tradingPage.getAllTabsCount();
        Assert.assertEquals(finalCount-initialCount,1);
    }

    @Test(description = "buy lot", priority = 2)
    public void buyLot(){
        int initialCount = tradingPage.getOrdersCount();
        tradingPage = tradingPage
                 .buyLots(LOTS_AMOUNT);
        int finalCount = tradingPage.getOrdersCount();
        Assert.assertEquals(finalCount-initialCount,LOTS_AMOUNT);
    }

    @Test(description = "edit last lot", priority = 3)
    public void editLastLot(){
        tradingPage = tradingPage.editLastLot(TAKE_PROFIT_VALUE);
        String value = tradingPage.getTakeProfitValueOfLastOrder();
        Assert.assertEquals(value, TAKE_PROFIT_VALUE);
    }

    @Test(description = "delete all lots", priority = 4)
    public void deleteAllLots(){
        tradingPage=tradingPage.deleteAllLots();
        int finalCount = tradingPage.getOrdersCount();
        Assert.assertEquals(finalCount, 0);
    }

    @Test(description = "add company to faves", priority = 5)
    public void addCompanyToFaves(){
        tradingPage = tradingPage.addCompanyToFaves(COMPANY_NAME);
        Assert.assertEquals(tradingPage.isCompanyInFaves(), true);
    }
}
