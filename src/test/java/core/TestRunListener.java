package core;

import org.apache.commons.io.FileUtils;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
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

public class TestRunListener extends RunListener {
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

    @Override
    public void testRunStarted(Description description) {
        LOGGER.info("Before tests run: " + description);
    }

    @Override
    public void testRunFinished(Result result) {
        LOGGER.info("Result of the test run:");
        LOGGER.info("Run time: " + result.getRunTime() + " ms");
        LOGGER.info("Run count: " + result.getRunCount());
        LOGGER.info("Failure count: " + result.getFailureCount());
        LOGGER.info("Ignored count: " + result.getIgnoreCount());
    }

    @Override
    public void testStarted(Description description) throws IOException {
        LOGGER.info("Test starts: " + description);
        createAndStartService();
        createDriver();
    }

    @Override
    public void testFinished(Description description) {
        LOGGER.info("Test finished: " + description);
        LOGGER.info("--------------------------------------");
        stopServerQuitDriver();
    }

    @Override
    public void testFailure(Failure failure) {
        LOGGER.info("Test failed with: " + failure.getException());
        captureScreenshot("faildedTest"
                + failure.getTestHeader()
                .replace("(", "_")
                .replace(")", "_"), BaseTest.getDriver());
        stopServerQuitDriver();
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        LOGGER.info("Test assumes: " + failure.getException());
        stopServerQuitDriver();
    }

    @Override
    public void testIgnored(Description description) {
        LOGGER.info("Test ignored: " + description);
        LOGGER.info("--------------------------------------");
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

    private void createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new RemoteWebDriver(service.getUrl(), options);
    }

    private void stopServerQuitDriver() {
        driver.quit();
        service.stop();
    }
}
