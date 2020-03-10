package core;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.MainPage;

import java.util.ArrayList;

@RunWith(TestRunner.class)
public abstract class BaseTest {
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    private static WebDriver webDriver;
    private MainPage mainPage = new MainPage();

    public static WebDriver getDriver() {
        return webDriver;
    }

    protected void openUrl() {
        webDriver = TestRunListener.getDriver();
        webDriver.get("https://astekbet55.com");
    }

    protected void safeAlertDismiss() {
        mainPage.safeAlertDismiss(webDriver);
    }

    protected void clickLiveTopMenu() {
        mainPage.clickLiveTopMenu(webDriver);
    }

    protected void selectAllAndOnlyLive(boolean isMainPage) {
        mainPage.selectAllAndOnlyLive(webDriver, isMainPage);
    }

    protected ArrayList<WebElement> getListOfNamesOfChampionship() {
        return mainPage.getListOfNamesOfChampionship(webDriver);
    }

    protected void checkIcon(ArrayList<WebElement> content) {
        mainPage.checkIcon(webDriver, content);
    }

    protected boolean isNewRowAppear(ArrayList<WebElement> currentView, ArrayList<WebElement> oldView) {
        return mainPage.isNewRowAppear(currentView, oldView);
    }
}
