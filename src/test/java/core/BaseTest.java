package core;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(TestRunner.class)
public abstract class BaseTest {

    protected static WebDriver getDriver() {
        return TestRunListener.getDriver();
    }
}
