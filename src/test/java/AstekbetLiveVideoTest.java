import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/resources/features",
        glue = {"stepDefinition.steps", "stepDefinition.hooks"},
        plugin = {"pretty", "html:reports/test-report"})
public class AstekbetLiveVideoTest {
}
