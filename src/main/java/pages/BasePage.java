package pages;

import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;

abstract class BasePage {
    static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

    void clickButton(By by, WebDriver webDriver) {
        webDriver.findElement(by).click();
    }

    boolean checkIsActiveFilterByParent(By by, WebDriver webDriver) {
        try {
            String classCss = webDriver.findElement(by).findElement(By.xpath("../..")).getAttribute("class");
            return classCss.contains("active");
        } catch (NoSuchElementException e) {
            LOGGER.info(e.toString());
            return false;
        }
    }

    boolean checkIsActiveFilter(By by, WebDriver webDriver) {
        try {
            String classCss = webDriver.findElement(by).getAttribute("class");
            return classCss.contains("active");
        } catch (NoSuchElementException e) {
            LOGGER.info(e.toString());
            return false;
        }
    }

    boolean isElementPresentChild(By by, WebElement webElement, WebDriver webDriver) {
        try {
            scrollToElement(webDriver, webElement);
            return webElement.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            LOGGER.info(e.toString());
            return false;
        }
    }

    ArrayList<WebElement> getListOfWebElement(WebDriver webDriver, By by) {
        return new ArrayList<>(webDriver.findElements(by));
    }

    ArrayList<WebElement> getListOfWebElementChild(WebElement webElement, By by) {
        return new ArrayList<>(webElement.findElements(by));
    }

    void scrollToElement(WebDriver webDriver, WebElement webElement) {
        try {
            if (webElement.isDisplayed()) {
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();"
                        , webElement);
            } else {
                LOGGER.info("Element is no presented any more");
            }
        } catch (StaleElementReferenceException e) {
            LOGGER.error("Element is no presented any more");
        }
    }

    String getTitle(WebElement webElement, By by) {
        sleepOnSec(1);
        return webElement.findElement(by).getAttribute("title");
    }

    private void sleepOnSec(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            LOGGER.info(e.toString());
        }
    }

    ArrayList<WebElement> differentView(ArrayList<WebElement> currentView, ArrayList<WebElement> oldView, By by) {
        ArrayList<WebElement> different = new ArrayList<>();
        currentView.sort(Comparator.comparing(o -> o.findElement(by).getAttribute("title")));
        oldView.sort(Comparator.comparing(o -> o.findElement(by).getAttribute("title")));
        int i = 0;
        if (!currentView.containsAll(oldView)) {
            for (WebElement element : currentView) {
                if (!getTitle(oldView.get(i), by).equalsIgnoreCase(getTitle(element, by))) {
                    different.add(element);
                }
            }
        }
        if (different.size() != 0)
            return different;
        else
            return null;
    }
}
