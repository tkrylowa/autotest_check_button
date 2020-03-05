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

    boolean isElementPresent(By by, WebDriver webDriver) {
        try {
            return webDriver.findElement(by).isEnabled();
        } catch (ElementNotInteractableException e) {
            return false;
        }
    }

    boolean isElementPresentChild(By by, WebElement webElement) {
        try {
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
}
