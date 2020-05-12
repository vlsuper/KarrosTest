package com.karros.auto;

import com.karros.auto.pageObjects.LoginPage;
import com.karros.auto.pageObjects.StudentRequestStatusPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class StudentRequestStatusPageTest {

    WebDriver driver;
    LoginPage loginPage;
    StudentRequestStatusPage studentPage;

    @BeforeTest
    public void initDriver() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--no-sandbox");
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), chromeOptions);
        Thread.currentThread().setName("Chrome Browser");
        loginPage = new LoginPage(driver);
    }

    @Test(description = "Verify filter Student Access Request with INACTIVE")
    public void studentAccessRequestWithInactive(){
        System.out.println("Verify filter Student Access Request with INACTIVE");
        loginPage.openURL("http://ktvn-test.s3-website.us-east-1.amazonaws.com/");
        studentPage = loginPage.doLogin("admin@test.com","test123");

        studentPage.clickFilter();
        studentPage.selectValue("Inactive");
        studentPage.clickApplyFilters();

        // Verify tag is displayed properly
        Assert.assertEquals(studentPage.getFilterTag("Request Status"),"Request Status: Inactive",
                "Request Status - Inactive is correct");
        // Verify table is loaded and displayed value matching with the filter
        Assert.assertTrue(studentPage.verifyRequestStatusFilterResult("Inactive"),
                "The result is matching with filter value");
    }

    @Test(description = "Verify sorting of First Name column")
    public void verifySortingFirstNameColumn(){
        System.out.println("Verify sorting of First Name column");

        studentPage.openURL("http://ktvn-test.s3-website.us-east-1.amazonaws.com/");

        studentPage.clickFirstNameHeader();
        Assert.assertTrue(studentPage.verifyOrderOfFirstName(false),
                "First Name is sorted in descending order correctly");
        studentPage.clickFirstNameHeader();
        Assert.assertTrue(studentPage.verifyOrderOfFirstName(true),
                "First Name is sorted in ascending order correctly");
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }

}
