package com.karros.auto.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class StudentRequestStatusPage extends BasePage {

    private By btnFilter = By.cssSelector(".module_grid__btn_filter");
    private By lstRequestStatus= By.xpath("//select[@id='formControlsSelect']");
    private By btnApplyFilter = By.cssSelector(".modal-footer > button.btn-filter.btn.btn-default");

    public StudentRequestStatusPage(WebDriver driver){
        super(driver);
    }

    public StudentRequestStatusPage clickFilter(){
        clickElement(btnFilter);
        System.out.println("Click Filters");

        return this;
    }

    public StudentRequestStatusPage selectValue(String value){
        clickElement(lstRequestStatus);

        By txtOption = By.xpath("//option[contains(text(),'"+value+"')]");
        clickElement(txtOption);
        System.out.println("Select a status - " + value);

        return this;
    }

    public StudentRequestStatusPage clickApplyFilters(){
        clickElement(btnApplyFilter);
        System.out.println("Click Apply Filters");

        return this;
    }

    public String getFilterTag(String filterTagName){
        By tagFilter = By.xpath("//a[(@class='query__filter__item') and (contains(text(),'"+filterTagName+"'))]");
        String txtTagFilter = getText(tagFilter);
        System.out.println("Get filter tag value - " + txtTagFilter);

        return txtTagFilter;
    }

    public List<String> getListRequestStatusValues(){
        By requestStatusValuesElement = By.xpath("//td[contains(@class,'td-request-approved')]");
        List<String> requestStatusValues = new ArrayList<String>();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(requestStatusValuesElement));
            List<WebElement> listFirstNameElement = driver.findElements(requestStatusValuesElement);

            for(WebElement e: listFirstNameElement){
                requestStatusValues.add(e.getText());
            }
        } catch (Exception e){
            try {
                throw e;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return requestStatusValues;
    }

    public boolean verifyRequestStatusFilterResult(String status){
        List<String> listRequestStatusValues = getListRequestStatusValues();

        if (listRequestStatusValues != null){
            for(int i=0;i<listRequestStatusValues.size();i++){
                if (listRequestStatusValues.get(i).equals(status)) {
                    continue;
                } else {
                    System.out.println("'"+listRequestStatusValues.get(i) + "' does not match with '"+status+"'");
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    public StudentRequestStatusPage clickFirstNameHeader(){
        By firstNameHeaderElement = By.xpath("//th[contains(@data-field,'firstName')]");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(btnApplyFilter));
        clickElement(firstNameHeaderElement);
        System.out.println("Click First Name header");

        return this;
    }

    public List<String> getListFirstNameValues(){
        By firstNameValues = By.cssSelector(" td.table-body-cols:nth-child(6)");
        List<String> listFirstName = new ArrayList<String>();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameValues));
            List<WebElement> listFirstNameElement = driver.findElements(firstNameValues);

            for(WebElement e: listFirstNameElement){
                listFirstName.add(e.getText());
            }
        } catch (Exception e){
            try {
                throw e;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return listFirstName;
    }

    public boolean verifyOrderOfFirstName(boolean isAscending){
        List<String> listFirstNameValues = getListFirstNameValues();

        if (listFirstNameValues != null){
            if (isAscending == true){
                // ascending order
                System.out.println("Verify oder of First Name is in ascending order");
                for(int i=1; i < listFirstNameValues.size();i++){
                    if ((listFirstNameValues.get(i).compareTo(listFirstNameValues.get(i-1)) > 0) ||
                            listFirstNameValues.get(i).equalsIgnoreCase(listFirstNameValues.get(i-1))) {
                        continue;
                    } else {
                        return false;
                    }
                }
            } else {
                // descending order
                System.out.println("Verify oder of First Name is in descending order");
                for(int i=1; i < listFirstNameValues.size();i++){
                    if ((listFirstNameValues.get(i).compareTo(listFirstNameValues.get(i-1)) <= 0) ||
                            listFirstNameValues.get(i).equalsIgnoreCase(listFirstNameValues.get(i-1))) {
                        continue;
                    } else {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }

        return true;
    }

}
