package web.stepdefinition;

import genericwrappers.GenericWrapper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import web.utils.BaseClass;
import webutils.LoadProperties;

import static genericwrappers.GenericWrapper.driver;

public class Hooks{

    GenericWrapper wrapper = new GenericWrapper(driver);

    @Before
    public void setUp() {
        wrapper.initializeDriver(LoadProperties.prop.getProperty("chromeBrowser"));
        wrapper.maximizeWindow();
        wrapper.setImplicitWait();
        wrapper.openUrl(LoadProperties.prop.getProperty("IRMS.live"));
        System.out.println("Test execution started");
    }

    @After
    public void tearDown() {
        GenericWrapper.quitDriver();
        System.out.println("Browser closed");
    }
}