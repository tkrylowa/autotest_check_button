import core.BaseTest;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;


public class TestAstekbetLiveVideo extends BaseTest {

    @Test
    public void liveVideoInMainPageTest() {
        openUrl();
        safeAlertDismiss();
        selectAllAndOnlyLive(true);
        ArrayList<WebElement> content = getListOfNamesOfChampionship();
        checkIcon(content);
        ArrayList<WebElement> currentContent = getListOfNamesOfChampionship();
        while (isNewRowAppear(currentContent, content)) {
            LOGGER.info("New row appears!");
            checkIcon(currentContent);
            currentContent = getListOfNamesOfChampionship();
        }
    }

    @Test
    public void liveVideoInLivePageTest() {
        openUrl();
        safeAlertDismiss();
        clickLiveTopMenu();
        selectAllAndOnlyLive(false);
        ArrayList<WebElement> content = getListOfNamesOfChampionship();
        checkIcon(content);
        ArrayList<WebElement> currentContent = getListOfNamesOfChampionship();
        while (isNewRowAppear(currentContent, content)) {
            LOGGER.info("New row appears!");
            checkIcon(currentContent);
            currentContent = getListOfNamesOfChampionship();
        }
    }
}
