package com.epam.ta.test;

import com.epam.ta.driver.DriverSingleton;
import com.epam.ta.model.User;
import com.epam.ta.page.AccountPage;
import com.epam.ta.page.HomePage;
import com.epam.ta.service.UserCreator;
import com.epam.ta.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners({TestListener.class})
public class CommonConditions {
    protected WebDriver driver;
    protected final User TEST_USER = UserCreator.withCredentialsFromProperty();

    @BeforeClass()
    public void setUp(){
        driver = DriverSingleton.getDriver();
    }

    @AfterClass(alwaysRun = true)
    public void stopBrowser()
    {
        DriverSingleton.closeDriver();
    }
}
