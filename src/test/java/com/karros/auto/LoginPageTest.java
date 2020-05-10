package com.karros.auto;

import com.karros.auto.pageObjects.LoginPage;
import com.karros.auto.pageObjects.StudentRequestStatusPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginPageTest {

    WebDriver driver;

    @BeforeTest
    public void initDriver() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--no-sandbox");
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), chromeOptions);
        Thread.currentThread().setName("Chrome Browser");
    }

    @Test(description = "Verify filter Student Access Request with INACTIVE")
    public void studentAccessRequestWithInactive(){
        LoginPage loginPage = new LoginPage(driver);
        StudentRequestStatusPage studentPage;

        loginPage.openURL("http://ktvn-test.s3-website.us-east-1.amazonaws.com/");
        studentPage = loginPage.doLogin("admin@test.com","test123");

        studentPage.clickFilter();
        studentPage.selectValue("Inactive");
        studentPage.clickApplyFilters();

        // Verify tag is displayed properly
        studentPage.getFilterTag("Request Status");
        // Verify table is loaded and displayed value matching with the filter
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }

}
