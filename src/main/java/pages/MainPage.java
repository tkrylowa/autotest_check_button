package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class MainPage extends BasePage {
    private By liveBtn = By.id("live_href");
    private By liveIconBtn = By.xpath("//div[contains(@class,'b-filters__item b-filters__item_alone')]");
    private By notificationPopUp = By.id("pushfree");
    private By notificationPopUpBlock = By.xpath("//div[@id='pushfree']//a");
    private By nameOfChampionship = By.xpath(".//div[@class='c-events__name']//a[@class='c-events__liga']");
    private By contentName = By.xpath("//div[@class='c-events__item c-events__item_head greenBack']");
    private By contentRow = By.xpath(".//div[@class='c-events__item c-events__item_col']");
    private By liveIcon = By.xpath(".//div[@class='c-events__ico c-events__ico_video']");
    private By parentPath = By.xpath("..//.");

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
        } catch (NoSuchElementException | StaleElementReferenceException | TimeoutException ignored) {
            LOGGER.info("Element is no presented");
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
        return getTitle(webElement, nameOfChampionship);
    }

    public void selectAllAndOnlyLive(WebDriver webDriver, boolean isMainPage) {
        safeAlertDismiss(webDriver);
        clickSportFilter(webDriver, "All", true);
        clickLiveIcon(webDriver, true);
        if (isMainPage)
            clickButton(By.className("sb-t-cell"), webDriver);
    }

    public ArrayList<WebElement> getListOfNamesOfChampionship(WebDriver webDriver) {
        return getListOfWebElement(webDriver, contentName);
    }

    private boolean isMoreThanOneRowInContent(WebElement webElement) {
        return getListOfWebElementChild(webElement, contentRow).size() > 1;
    }

    public boolean isNewRowAppear(ArrayList<WebElement> currentView, ArrayList<WebElement> oldView) {
        return differentView(currentView, oldView, nameOfChampionship) != null;
    }

    public void checkIcon(WebDriver webDriver, ArrayList<WebElement> currentContent) {
        for (WebElement element : currentContent) {
            LOGGER.info("Name of content row: " + getListOfNamesOfChampionship(element, webDriver));
            boolean isIconAppear;
            if (isMoreThanOneRowInContent(element.findElement(parentPath))) {
                ArrayList<WebElement> rowOfContent
                        = getListOfWebElementChild(element.findElement(parentPath), contentRow);
                for (WebElement elementRow : rowOfContent) {
                    isIconAppear = isElementPresentChild(liveIcon, elementRow.findElement(parentPath), webDriver);
                    Assert.assertTrue("Icon is presented", isIconAppear);
//                    if (!isIconAppear) {
//                        LOGGER.info("Icon is no presented");
//                    } else
//                        LOGGER.info("Icon is presented");
                }
            } else {
                isIconAppear = isElementPresentChild(liveIcon, element.findElement(By.xpath("..//.")), webDriver);
                Assert.assertTrue("Icon is no presented", isIconAppear);
//                if (!isIconAppear) {
//                    LOGGER.info("Icon is no presented");
//                } else
//                    LOGGER.info("Icon is presented");
            }
        }
    }
}