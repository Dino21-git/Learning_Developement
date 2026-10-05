package web.utils;

import genericwrappers.GenericWrapper;
import org.openqa.selenium.WebDriver;
import web.pageobjects.HomePage;
import web.pageobjects.LoginPage;
import webutils.LoadProperties;

public class BaseClass extends GenericWrapper{

    //MMT Pages
    public GenericWrapper wrapper;
    private HomePage homePage;
    private LoginPage loginPage;

    public BaseClass() {
        super(driver);
        wrapper = new GenericWrapper(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    //Constructor accepting another BaseClass Instance
    public BaseClass(BaseClass base){
        super(driver);
        if (base != null){
            this.wrapper = base.wrapper;
            this.homePage =base.getMMTHomePage();
            this.loginPage = base.getLoginPage();
        }
    }

    public HomePage getMMTHomePage(){
        return homePage;
    }

    public LoginPage getLoginPage(){
        return loginPage;
    }

}
