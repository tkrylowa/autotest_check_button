package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriverWait smallWait;
    protected WebDriverWait longWait;
    protected WebDriverWait wait5sec;
    protected WebDriverWait wait3sec;

    void clickButton(By by, WebDriver webDriver) {
        webDriver.findElement(by).click();
    }
}
