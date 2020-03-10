import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = {"stepDefinition.steps", "stepDefinition.hooks"},
        plugin = {"html:target/cucumber-report/smoketest", "json:target/cucumber.json"},
        dryRun = true)
public class AstekbetLiveVideoTest {
}
