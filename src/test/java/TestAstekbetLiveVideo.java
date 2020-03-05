import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

public class TestAstekbetLiveVideo extends BaseTest {
    private WebDriver webDriver;

    @Test
    public void testLiveVideoInMainPage() {
        webDriver = getDriver();
        webDriver.get("https://astekbet55.com");
        MainPage mainPage = new MainPage();
        mainPage.safeAlertDismiss(webDriver);
        mainPage.selectAllAndOnlyLive(webDriver, true);
        mainPage.checkIcon(mainPage.getNameOfChampionship(webDriver));
    }

    @Test
    public void testLiveVideoInLovePage() {
        webDriver = getDriver();
        webDriver.get("https://astekbet55.com");
        MainPage mainPage = new MainPage();
        mainPage.safeAlertDismiss(webDriver);
        mainPage.clickLiveTopMenu(webDriver);
        mainPage.selectAllAndOnlyLive(webDriver, false);
        mainPage.checkIcon(mainPage.getNameOfChampionship(webDriver));
    }
}
