package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class MainPage extends BasePage {
    private By liniaBtn = By.id("line_href");
    private By liveBtn = By.id("live_href");
    private By promoBtn = By.id("1xbonus");
    private By slotsBtn = By.xpath("//a[@href='slots/' and @class='main-item']");
    private By liveCasinoBtn = By.xpath("//a[@href='casino/' and @class='main-item main-item_casino ']");
    private By gamesBtn = By.id("1xgames");
    private By vertualSportBtn = By.id("live_href");
    private By moreBtn = By.id("live_href");
    private By liveIconBtn = By.xpath("//div[contains(@class,'b-filters__item b-filters__item_alone')]");
    private By notificationPopUp = By.id("pushfree");
    private By notificationPopUpBlock = By.xpath("//div[@id='pushfree']//a");
    private By nameOfChampionship = By.xpath(".//div[@class='c-events__name']//a[@class='c-events__liga']");
    private By contentUl = By.xpath("//div[@class='c-events__item c-events__item_head greenBack']");
    private By liveIcon = By.xpath(".//div[@class='c-events__ico c-events__ico_video']");

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

    private void clickLiveIcon(WebDriver webDriver, boolean isNeedActive) {
        boolean isActive = checkIsActiveFilter(liveIconBtn, webDriver);
        if ((isNeedActive && !isActive) || (!isNeedActive && isActive))
            clickButton(liveIconBtn, webDriver);
    }

    public void safeAlertDismiss(WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, 1);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(notificationPopUp));
            if (webDriver.findElement(notificationPopUp).isDisplayed()) {
                webDriver.findElement(notificationPopUpBlock).click();
            }
        } catch (NoSuchElementException ignored) {
        } catch (TimeoutException ignored) {
        }
    }

    private void clickSportFilter(WebDriver webDriver, String filtersName, boolean isNeedActive) {
        //add click '>' while element is no present by name
        String SPORT_FILTER_PATH = "//div[contains(@class,'b-filters__item')]";
        By byFilter = By.xpath(
                String.format("%s//div[text()='%s']", SPORT_FILTER_PATH, filtersName));
        boolean isActive = checkIsActiveFilterByParent(byFilter, webDriver);
        if ((isNeedActive && !isActive) || (!isNeedActive && isActive))
            webDriver.findElement(byFilter).click();
    }

    private String getNameOfChampionship(WebElement webElement) {
        return webElement.findElement(nameOfChampionship).getAttribute("title");
    }

    public void selectAllAndOnlyLive(WebDriver webDriver) {
        safeAlertDismiss(webDriver);
        clickSportFilter(webDriver, "All", true);
        clickLiveIcon(webDriver, true);
        clickButton(By.className("sb-t-cell"), webDriver);
    }

    public ArrayList<WebElement> getNameOfChampionship(WebDriver webDriver) {
//        ArrayList<WebElement> content = new ArrayList<WebElement>(webDriver.findElements(contentUl));
//        for (WebElement element : content) {
//            System.out.println("Name of content row: " + getNameOfChampionship(element));
//        }
        return new ArrayList<WebElement>(webDriver.findElements(contentUl));
    }

    public void checkIcon(WebDriver webDriver, ArrayList<WebElement> content) {
        for (WebElement element : content) {
            if (isElementPresent(liveIcon, webDriver)) {
                System.out.println("Name of content row: " + getNameOfChampionship(element));
                System.out.println("Icon is presented");
            } else {
                System.out.println("Name of content row: " + getNameOfChampionship(element));
                System.out.println("Icon is no presented");
            }
        }
    }
}
