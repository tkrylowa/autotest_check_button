package StepDefinition;

import core.TestRunListener;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.BaseTest;

import java.io.File;
import java.io.IOException;

public class Hooks {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRunListener.class);
    private final static String PATH = new File("").getAbsolutePath();
    private static ChromeDriverService service;
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    private static void createAndStartService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(
                        new File(PATH + "/src/main/resources/chromeDriver/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    @Before
    public void beforeTestStart() throws IOException {
        createAndStartService();
        createDriver();
    }

    @After
    public void testEnd(Scenario scenario) {
        LOGGER.info("Test finished: " + scenario);
        LOGGER.info("--------------------------------------");
        if (scenario.isFailed()) {
            captureScreenshot("faildedTest"
                    + scenario.getName()
                    .replace("(", "_")
                    .replace(")", "_"), BaseTest.getDriver());
        }
        stopServerQuitDriver();
    }

    private void createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new RemoteWebDriver(service.getUrl(), options);
    }

    private void stopServerQuitDriver() {
        driver.quit();
        service.stop();
    }

    private void captureScreenshot(String name, WebDriver webDriver) {
        File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = PATH + "\\target\\screenshots\\" + name + screenshot.getName();
        try {
            FileUtils.copyFile(screenshot, new File(screenshotPath));
        } catch (IOException e) {
            LOGGER.info("Screenshot isn't save");
        }
    }
}
