package com.epam.ta.test;

import com.epam.ta.page.AccountPage;
import com.epam.ta.page.HomePage;
import com.epam.ta.page.TradingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserAccessTests extends CommonConditions {
	private final int BALANCE_VALUE = 1000;
	private final String ACCOUNT_NAME = "NewAccName";

	private final String URL_PART = "/webtrading/";

	@Test(description = "set demo account balance", priority = 1)
	public void setBalance(){
		AccountPage accountPage = new HomePage(driver)
				.openPage()
				.goToSignInPage()
				.signIn(TEST_USER)
				.setBalance(BALANCE_VALUE);

		Assert.assertEquals(accountPage.getBalance(), BALANCE_VALUE);
	}

	@Test(description = "rename account", priority = 2)
	public void renameAccount(){
		AccountPage accountPage = new HomePage(driver)
				.openPage()
				.goToSignInPage()
				.signIn(TEST_USER)
				.renameAccount(ACCOUNT_NAME);

		Assert.assertEquals(accountPage.getAccountName(), ACCOUNT_NAME);
	}

	@Test(description = "open web terminal", priority = 3)
	public void startTraiding(){
		TradingPage tradingPage = new HomePage(driver)
				.openPage()
				.goToSignInPage()
				.signIn(TEST_USER)
				.startTraiding();

		Assert.assertTrue(driver.getCurrentUrl().contains(URL_PART));
	}
}
