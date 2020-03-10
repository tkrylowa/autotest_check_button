import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import steps.BaseTest;

import java.util.ArrayList;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "steps",
        tags = "@LiveVideoLivePage")
public class TestAstekbetLiveVideoLivePage extends BaseTest {

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
