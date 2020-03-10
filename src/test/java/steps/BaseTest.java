package steps;

import StepDefinition.Hooks;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.MainPage;

import java.util.ArrayList;

//@RunWith(TestRunner.class)
public abstract class BaseTest {
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    private static WebDriver webDriver;
    private MainPage mainPage = new MainPage();

    public static WebDriver getDriver() {
        return webDriver;
    }

    @Given("^open astekbet55\\.com$")
    protected void openUrl() {
//        webDriver = TestRunListener.getDriver();
        webDriver = Hooks.getDriver();
        webDriver.get("https://astekbet55.com");
    }

    @And("^ dismiss alert")
    protected void safeAlertDismiss() {
        mainPage.safeAlertDismiss(webDriver);
    }

    @And("Click button 'live' in top menu")
    protected void clickLiveTopMenu() {
        mainPage.clickLiveTopMenu(webDriver);
    }

    @And("^ select necessary filters: all sports, live-game $")
    protected void selectAllAndOnlyLive(boolean isMainPage) {
        mainPage.selectAllAndOnlyLive(webDriver, isMainPage);
    }

    @When("Get list of the championship")
    protected ArrayList<WebElement> getListOfNamesOfChampionship() {
        return mainPage.getListOfNamesOfChampionship(webDriver);
    }

    @Then("Verify that icon for each game appears")
    protected void checkIcon(ArrayList<WebElement> content) {
        mainPage.checkIcon(webDriver, content);
    }

    protected boolean isNewRowAppear(ArrayList<WebElement> currentView, ArrayList<WebElement> oldView) {
        return mainPage.isNewRowAppear(currentView, oldView);
    }
}
