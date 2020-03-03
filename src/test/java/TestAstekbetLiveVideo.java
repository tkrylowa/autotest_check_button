import org.junit.Test;
import pages.MainPage;

public class TestAstekbetLiveVideo extends BaseTest {

    @Test
    public void testLiveVideo() {
        getDriver().get("https://astekbet55.com");
        new MainPage().clickLiveTopMenu(getDriver());
    }
}
