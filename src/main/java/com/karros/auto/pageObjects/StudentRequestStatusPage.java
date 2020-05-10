package com.karros.auto.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StudentRequestStatusPage extends BasePage {

    private By btnFilter = By.cssSelector(".module_grid__btn_filter");
    private By lstRequestStatus= By.xpath("//select[@id='formControlsSelect']");
    private By btnApplyFilter = By.cssSelector(".modal-footer > button.btn-filter.btn.btn-default");

    public StudentRequestStatusPage(WebDriver driver){
        super(driver);
    }

    public StudentRequestStatusPage clickFilter(){
        clickElement(btnFilter);
        return this;
    }

    public StudentRequestStatusPage selectValue(String value){
        clickElement(lstRequestStatus);

        By txtOption = By.xpath("//option[contains(text(),'"+value+"')]");
        clickElement(txtOption);

        return this;
    }

    public StudentRequestStatusPage clickApplyFilters(){
        clickElement(btnApplyFilter);

        return this;
    }

    public StudentRequestStatusPage getFilterTag(String filterTagName){
        By tagFilter = By.xpath("//a[(@class='query__filter__item') and (contains(text(),'"+filterTagName+"'))]");

        System.out.println(getText(tagFilter));
        return this;
    }

    public String getRequestStatusTable(){
        By requestStatusValues = By.xpath("");

        System.out.println(getText(requestStatusValues));

        return "";
    }
}
