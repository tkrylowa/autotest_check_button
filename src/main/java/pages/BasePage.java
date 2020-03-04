package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriverWait smallWait;
    protected WebDriverWait longWait;
    protected WebDriverWait wait5sec;
    protected WebDriverWait wait3sec;
    protected final String ICON_LIVE_VIDEO = "c-events__ico c-events__ico_video";

    void clickButton(By by, WebDriver webDriver) {
        webDriver.findElement(by).click();
    }

    boolean checkIsActiveFilterByParent(By by, WebDriver webDriver) {
        String classCss = webDriver.findElement(by).findElement(By.xpath("../..")).getAttribute("class");
        return classCss.contains("active");
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
}
