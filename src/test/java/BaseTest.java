import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public abstract class BaseTest {
    private final static String PATH = new File("").getAbsolutePath();
    private static ChromeDriverService service;
    private WebDriver driver;

    @BeforeClass
    public static void createAndStartService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(
                        new File(PATH + "/src/main/resources/chromeDriver/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    @AfterClass
    public static void stopService() {
        service.stop();
    }

    WebDriver getDriver() {
        return driver;
    }

    @Before
    public void createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new RemoteWebDriver(service.getUrl(), options);
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
