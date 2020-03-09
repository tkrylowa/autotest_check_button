import core.BaseTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.MainPage;

import java.util.ArrayList;


public class TestAstekbetLiveVideo extends BaseTest {
    private WebDriver webDriver;
    private final String URL = "https://astekbet55.com";
    private static final Logger LOGGER = LoggerFactory.getLogger(TestAstekbetLiveVideo.class);

    @Test
    public void liveVideoInMainPageTest() {
        webDriver = BaseTest.getDriver();
        webDriver.get(URL);
        MainPage mainPage = new MainPage();
        mainPage.safeAlertDismiss(webDriver);
        mainPage.selectAllAndOnlyLive(webDriver, true);
        ArrayList<WebElement> content = mainPage.getListOfNamesOfChampionship(webDriver);
        mainPage.checkIcon(webDriver, content);
        ArrayList<WebElement> currentContent = mainPage.getListOfNamesOfChampionship(webDriver);
        while (mainPage.isNewRowAppear(currentContent, content)) {
            LOGGER.info("New row appears!");
            mainPage.checkIcon(webDriver, currentContent);
            currentContent = mainPage.getListOfNamesOfChampionship(webDriver);
        }
    }

    @Test
    public void liveVideoInLovePageTest() {
        webDriver = getDriver();
        webDriver.get(URL);
        MainPage mainPage = new MainPage();
        mainPage.safeAlertDismiss(webDriver);
        mainPage.clickLiveTopMenu(webDriver);
        mainPage.selectAllAndOnlyLive(webDriver, false);
        ArrayList<WebElement> content = mainPage.getListOfNamesOfChampionship(webDriver);
        mainPage.checkIcon(webDriver, content);
        ArrayList<WebElement> currentContent = mainPage.getListOfNamesOfChampionship(webDriver);
        while (mainPage.isNewRowAppear(currentContent, content)) {
            LOGGER.info("New row appears!");
            mainPage.checkIcon(webDriver, currentContent);
            currentContent = mainPage.getListOfNamesOfChampionship(webDriver);
        }
    }
}
