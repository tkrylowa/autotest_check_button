package stepDefinition.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.util.Asserts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.MainPage;
import stepDefinition.hooks.Hooks;

import java.util.ArrayList;

public class Steps {
    private static final Logger LOGGER = LoggerFactory.getLogger(Steps.class);
    private static WebDriver webDriver;
    private MainPage mainPage = new MainPage();

    public static WebDriver getDriver() {
        return webDriver;
    }

    @Given("^open astekbet site$")
    public void openUrl() {
        webDriver = Hooks.getDriver();
        webDriver.get("https://astekbet55.com");
    }

    @And("^dismiss alert$")
    public void safeAlertDismiss() {
        mainPage.safeAlertDismiss(webDriver);
    }

    @And("^Click button 'live' in top menu$")
    public void clickLiveTopMenu() {
        mainPage.clickLiveTopMenu(webDriver);
    }

    @And("^select necessary filters in live page: all sports, live-game$")
    public void selectAllAndOnlyLiveLivePage() {
        mainPage.selectAllAndOnlyLive(webDriver, false);
    }

    @And("^select necessary filters in main page: all sports, live-game$")
    public void selectAllAndOnlyLiveMainPage() {
        mainPage.selectAllAndOnlyLive(webDriver, true);
    }

    @When("^List of the championship is not null$")
    public void getListOfNamesOfChampionship() {
        Asserts.check(mainPage.getListOfNamesOfChampionship(webDriver).size() > 0, "Not have any games");
    }

    @Then("^Verify that icon for each game appears$")
    public void checkIcon() {
        ArrayList<WebElement> content = mainPage.getListOfNamesOfChampionship(webDriver);
        mainPage.checkIcon(webDriver, content);
        ArrayList<WebElement> currentContent = mainPage.getListOfNamesOfChampionship(webDriver);
        while (isNewRowAppear(currentContent, content)) {
            LOGGER.info("New row appears!");
            mainPage.checkIcon(webDriver, currentContent);
            currentContent = mainPage.getListOfNamesOfChampionship(webDriver);
        }
    }

    private boolean isNewRowAppear(ArrayList<WebElement> currentView, ArrayList<WebElement> oldView) {
        return mainPage.isNewRowAppear(currentView, oldView);
    }
}
