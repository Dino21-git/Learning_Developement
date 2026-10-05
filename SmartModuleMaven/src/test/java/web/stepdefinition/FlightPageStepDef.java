package web.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import web.utils.BaseClass;
import webutils.LoadProperties;

public class FlightPageStepDef extends BaseClass {

    private static final Logger logger = LogManager.getLogger(FlightPageStepDef.class);

    @Given("the user is on the homepage")
    public void the_user_is_on_the_homepage() {
        try {
            String url = wrapper.getCurrentUrl();
            Assert.assertEquals(url, LoadProperties.prop.getProperty("mmt.live"));
        } catch (Exception e) {
            logger.error("Failing to get Current Url {}", String.valueOf(e));

        }
    }
    @When("the user clicks on the flight icon")
    public void the_user_clicks_on_the_flight_icon() {

        getMMTHomePage().clickOnCrossIcon();
        getMMTHomePage().clickOnFlightsIcon();

    }
    @Then("Verifies the flight icon should be visible")
    public void verifies_the_flight_icon_should_be_visible() {

    }
    @Then("the user should be navigated to the flight page")
    public void the_user_should_be_navigated_to_the_flight_page() {

    }
}
