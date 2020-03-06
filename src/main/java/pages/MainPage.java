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
    private By contentName = By.xpath("//div[@class='c-events__item c-events__item_head greenBack']");
    private By contentRow = By.xpath(".//div[@class='c-events__item c-events__item_col']");
    private By liveIcon = By.xpath(".//div[@class='c-events__ico c-events__ico_video']");

    public void clickLiveTopMenu(WebDriver webDriver) {
        clickButton(liveBtn, webDriver);
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
        String SPORT_FILTER_PATH = "//div[contains(@class,'b-filters__item')]";
        By byFilter = By.xpath(
                String.format("%s//div[text()='%s']", SPORT_FILTER_PATH, filtersName));
        boolean isActive = checkIsActiveFilterByParent(byFilter, webDriver);
        if ((isNeedActive && !isActive) || (!isNeedActive && isActive))
            webDriver.findElement(byFilter).click();
    }

    private String getListOfNamesOfChampionship(WebElement webElement, WebDriver webDriver) {
        scrollToElement(webDriver, webElement);
        return webElement.findElement(nameOfChampionship).getAttribute("title");
    }

    public void selectAllAndOnlyLive(WebDriver webDriver, boolean isMainPage) {
        safeAlertDismiss(webDriver);
        clickSportFilter(webDriver, "All", true);
        clickLiveIcon(webDriver, false);
        if (isMainPage)
            clickButton(By.className("sb-t-cell"), webDriver);
    }

    public ArrayList<WebElement> getListOfNamesOfChampionship(WebDriver webDriver) {
        return getListOfWebElement(webDriver, contentName);
    }

    private boolean isMoreThanOneRowInContent(WebElement webElement) {
        return getListOfWebElementChild(webElement, contentRow).size() > 1;
    }

    public void checkIcon(ArrayList<WebElement> content, WebDriver webDriver) {
        for (WebElement element : content) {
            System.out.println("Name of content row: " + getListOfNamesOfChampionship(element, webDriver));
            boolean isIconAppear;
            if (isMoreThanOneRowInContent(element.findElement(By.xpath("..//.")))) {
                ArrayList<WebElement> rowOfContent
                        = getListOfWebElementChild(element.findElement(By.xpath("..//.")), contentRow);
                for (WebElement elementRow : rowOfContent) {
                    isIconAppear = isElementPresentChild(liveIcon, elementRow.findElement(By.xpath("..//.")), webDriver);
//                    Assert.assertTrue("Icon is presented", isIconAppear);
                    if (!isIconAppear) {
                        System.out.println("Icon is no presented");
                    } else
                        System.out.println("Icon is presented");
                }
            } else {
                isIconAppear = isElementPresentChild(liveIcon, element.findElement(By.xpath("..//.")), webDriver);
//                Assert.assertTrue("Icon is no presented", isIconAppear);
                if (!isIconAppear) {
                    System.out.println("Icon is no presented");
                } else
                    System.out.println("Icon is presented");
            }
        }
    }
}