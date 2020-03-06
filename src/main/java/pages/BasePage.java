package pages;

import org.openqa.selenium.*;

import java.util.ArrayList;

abstract class BasePage {

    void clickButton(By by, WebDriver webDriver) {
        webDriver.findElement(by).click();
    }

    boolean checkIsActiveFilterByParent(By by, WebDriver webDriver) {
        try {
            String classCss = webDriver.findElement(by).findElement(By.xpath("../..")).getAttribute("class");
            return classCss.contains("active");
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    boolean checkIsActiveFilter(By by, WebDriver webDriver) {
        String classCss = webDriver.findElement(by).getAttribute("class");
        return classCss.contains("active");
    }

    boolean isElementPresentChild(By by, WebElement webElement, WebDriver webDriver) {
        try {
            scrollToElement(webDriver, webElement);
            return webElement.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    ArrayList<WebElement> getListOfWebElement(WebDriver webDriver, By by) {
        return new ArrayList<WebElement>(webDriver.findElements(by));
    }

    ArrayList<WebElement> getListOfWebElementChild(WebElement webElement, By by) {
        return new ArrayList<WebElement>(webElement.findElements(by));
    }

    void scrollToElement(WebDriver webDriver, WebElement webElement) {
        try {
            if (webElement.isDisplayed()) {
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();"
                        , webElement);
            } else {
                System.out.println("Element is no presented any more");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is no presented any more");
        }

    }
}
