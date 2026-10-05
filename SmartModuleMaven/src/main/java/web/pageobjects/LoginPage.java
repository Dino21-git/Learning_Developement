package web.pageobjects;

import genericwrappers.GenericWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webutils.LoadProperties;

import java.io.IOException;

public class LoginPage extends GenericWrapper {

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Email']")
    WebElement LOGIN_EMAIL;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement LOGIN_PASSWORD;

    @FindBy(xpath = "//button[text()='Log in']")
    WebElement LOGIN_BTN;

    public void enterLoginCredential(){
        enterText(LOGIN_EMAIL, LoadProperties.prop.getProperty("username"));
        enterText(LOGIN_PASSWORD, LoadProperties.prop.getProperty("password"));
        clickElement(LOGIN_BTN);
    }

}
