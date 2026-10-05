package web.resources.testrunner;

import genericwrappers.GenericWrapper;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/web/resources/features",  // Path to your feature files
        glue = "web.stepdefinition",                 // Package name where step definitions are located
        plugin = {"pretty", "html:target/cucumber-reports.html"}, // Reporting plugins
        monochrome = true,                       // Makes console output readable
        tags = "@tag2"
        )
public class RunnerClass {

}
