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
        mainPage.selectAllAndOnlyLive(webDriver);
//        mainPage.getNameOfChampionship(webDriver);
        mainPage.checkIcon(webDriver, mainPage.getNameOfChampionship(webDriver));
    }

    @Test
    public void testLiveVideoInLovePage() {
        getDriver().get("https://astekbet55.com");
        new MainPage().safeAlertDismiss(getDriver());
        new MainPage().clickLiveTopMenu(getDriver());
    }
}
