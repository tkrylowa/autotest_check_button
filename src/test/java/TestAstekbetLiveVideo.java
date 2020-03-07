import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.MainPage;

import java.util.ArrayList;

public class TestAstekbetLiveVideo extends BaseTest {
    private WebDriver webDriver;
    private final String URL = "https://astekbet55.com";

    @Test
    public void testLiveVideoInMainPage() {
        webDriver = getDriver();
        webDriver.get(URL);
        MainPage mainPage = new MainPage();
        mainPage.safeAlertDismiss(webDriver);
        mainPage.selectAllAndOnlyLive(webDriver, true);
        ArrayList<WebElement> content = mainPage.getListOfNamesOfChampionship(webDriver);
        mainPage.checkIcon(webDriver, content);
        ArrayList<WebElement> currentContent = mainPage.getListOfNamesOfChampionship(webDriver);
        mainPage.checkIconForNewContent(currentContent, content, webDriver);
    }

    @Test
    public void testLiveVideoInLovePage() {
        webDriver = getDriver();
        webDriver.get(URL);
        MainPage mainPage = new MainPage();
        mainPage.safeAlertDismiss(webDriver);
        mainPage.clickLiveTopMenu(webDriver);
        mainPage.selectAllAndOnlyLive(webDriver, false);
        ArrayList<WebElement> content = mainPage.getListOfNamesOfChampionship(webDriver);
        mainPage.checkIcon(webDriver, content);
        ArrayList<WebElement> currentContent = mainPage.getListOfNamesOfChampionship(webDriver);
        mainPage.checkIconForNewContent(currentContent, content, webDriver);
    }
}
