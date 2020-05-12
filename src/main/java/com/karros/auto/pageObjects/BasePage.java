package com.karros.auto.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * constructor for Base Page
     *
     * @param webDriver - init Web Driver
     */
    public BasePage(WebDriver webDriver){
        driver = webDriver;
        wait = new WebDriverWait(driver,120,10);

        PageFactory.initElements(driver, this);
        System.out.println("Initial Page Factory");
    }

    public void openURL(String url){
        driver.navigate().to(url);
        System.out.println("Open URL - " + url);
    }

    /**
     * enter text to a By locator
     * @param by - locator of element to be entered text
     * @param inputString - text to be entered into the element
     */
    protected void enterText(By by, String inputString) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            WebElement element = driver.findElement(by);

            if (element!=null) {
                // enter text
                element.sendKeys(inputString);
            } else {
                // throw exception
                throw new RuntimeException("Element is null");
            }
        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * click element with By locator
     * @param by - locator of element to be clicked
     */
    protected void clickElement(By by){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
            WebElement element = driver.findElement(by);

            if (element != null) {
                element.click();
            } else {
                throw new RuntimeException("Element is null");
            }
        } catch (Exception e){
            try {
                throw e;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    protected String getText(By by){
        String value="";

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));

            WebElement element = driver.findElement(by);

            value = element.getText();

            return value;
        } catch (Exception e) {

        }
        return value;
    }
}
