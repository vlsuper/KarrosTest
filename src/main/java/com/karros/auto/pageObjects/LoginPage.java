package com.karros.auto.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    By txtEmail = By.xpath("//input[@name='email']");
    By txtPassword = By.xpath("//input[@name='password']");
    By btnlogin = By.xpath("//a[@class='col-login__btn']");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public LoginPage enterEmail(String email){
        enterText(txtEmail,email);
        return this;
    }

    public LoginPage enterPassword(String password){
        enterText(txtPassword,password);

        return this;
    }

    public LoginPage clickLogin(){
        clickElement(btnlogin);
        return this;
    }

    public StudentRequestStatusPage doLogin(String username, String password){
        this.enterEmail(username)
                .enterPassword(password)
                .clickLogin();

        return new StudentRequestStatusPage(this.driver);
    }
}
