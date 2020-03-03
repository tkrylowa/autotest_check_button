package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    private By liniaBtn = By.id("line_href");
    private By liveBtn = By.id("live_href");
    private By promoBtn = By.id("1xbonus");
    private By slotsBtn = By.xpath("//a[@href='slots/' and @class='main-item']");
    private By liveCasinoBtn = By.xpath("//a[@href='casino/' and @class='main-item main-item_casino ']");
    private By gamesBtn = By.id("1xgames");
    private By vertualSportBtn = By.id("live_href");
    private By moreBtn = By.id("live_href");

    public void clickLiniaTopMenu(WebDriver webDriver) {
        clickButton(liniaBtn, webDriver);
    }

    public void clickLiveTopMenu(WebDriver webDriver) {
        clickButton(liveBtn, webDriver);
    }

    public void clickPromoTopMenu(WebDriver webDriver) {
        clickButton(promoBtn, webDriver);
    }

    public void clickSlotsTopMenu(WebDriver webDriver) {
        clickButton(slotsBtn, webDriver);
    }

    public void clickLiveCasinoTopMenu(WebDriver webDriver) {
        clickButton(liveCasinoBtn, webDriver);
    }

    public void clickGamesTopMenu(WebDriver webDriver) {
        clickButton(gamesBtn, webDriver);
    }

    public void clickVirtualSportsTopMenu(WebDriver webDriver) {
        clickButton(vertualSportBtn, webDriver);
    }

    public void clickMoreTopMenu(WebDriver webDriver) {
        clickButton(moreBtn, webDriver);
    }
}
