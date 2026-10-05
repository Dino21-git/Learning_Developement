package web.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import web.utils.BaseClass;
import webutils.LoadProperties;

import java.io.IOException;

public class IRMSStepDef extends BaseClass {

    private static final Logger logger = LogManager.getLogger(IRMSStepDef.class);


    @Given("the user is on the loginpage")
    public void the_user_is_on_the_loginpage() {
        try {
            String url = wrapper.getCurrentUrl();
            Assert.assertEquals(url, LoadProperties.prop.getProperty("IRMS.live"));
        } catch (Exception e) {
            logger.error("Failing to get Current Url {}", String.valueOf(e));

        }
    }

    @When("User enter the login credential")
    public void userEnterTheLoginCredential() throws InterruptedException {
        getLoginPage().enterLoginCredential();
    }
}