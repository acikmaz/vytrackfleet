package com.automation.pages.fleet;


import com.automation.utilities.AbstractPageBase;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.sql.rowset.BaseRowSet;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class VehiclesFuelLogsPage extends AbstractPageBase {

    @FindBy(xpath = "//input[@id='prependedInput']")
    private WebElement fleetModule;

    @FindBy(xpath = "//span[text()='Vehicle fuel Logs']")
    private WebElement fuelLog;

    @FindBy(xpath = "//div[@class='flash-messages-holder']//div[2]//div")
    private WebElement alertMessageForUnauthorized;

    // US 10

    @FindBy(xpath = "//tbody[@class='ui-sortable']/tr")
    private List<WebElement> vehicleFuelLogGridInfoNames;

    @FindBy(css = "[class='grid-header']>tr>th[class^='grid']")
    private List<WebElement> vehicleFuelLogTableHeader;

    @FindBy(xpath = "//div[@class='dropdown-menu']//span[@class='close']")
    private WebElement closeBtn;

    @FindBy(css = ".pull-right.title-buttons-container > a")
    private WebElement createVehicleFuelLogsButton;

    @FindBy(xpath = "//div[@class='pull-left btn-group icons-holder']")
    private WebElement cancelBtn;

    // US10 AC3

    @FindBy(xpath = "//div[@class='dropdown open']//a[@title='Edit']")
    private WebElement editBtn;

    @FindBy(xpath = "//tr[@class='grid-row row-click-action']")
    private List<WebElement> gridRows;

    @FindBy(xpath ="//a[@class='btn ok btn-danger']")
    private WebElement dangerBtn;

    //US10 AC4
    @FindBy(xpath = "//a[@title='Reset']")
    private WebElement resetBtn;


    //
    @FindBy(css = "[class='column-manager dropdown']")
    private WebElement gridSettingsBtn;


    @FindBy(linkText = "Select All")
    private WebElement selectAllBtn;

    //US 9
    public String alertUnauthorizedUser() {
        return wait.until(ExpectedConditions.visibilityOf(alertMessageForUnauthorized)).getText();
    }


    public void clickOnGridSettings() {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.elementToBeClickable(gridSettingsBtn)).click();
    }

    public void clickSelectAll() {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.elementToBeClickable(selectAllBtn)).click();
    }

    public void clickCreateVehicleFuelLogBttn() { //US 10
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.elementToBeClickable(createVehicleFuelLogsButton)).click();
    }

    public void clickCancelBtn() { //US10
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
    }

    // US10 AC 1
    public Set<String> getAllVehicleFuelLogInfoFromGrid() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody[@class='ui-sortable']/tr")));
        Set<String> allVehicleNamesFromSettingGrid = new HashSet<>();
        for (WebElement each : vehicleFuelLogGridInfoNames) {
            allVehicleNamesFromSettingGrid.add(each.getText().toUpperCase());
        }
        return allVehicleNamesFromSettingGrid;
    }

    public void clickToClose() {
        wait.until(ExpectedConditions.elementToBeClickable(closeBtn)).click();
    }


    public Set<String> getAllVehicleFuelLogTableHeader() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='grid-header']>tr>th[class^='grid']")));
        Set<String> allVehicleNamesFromHeader = new HashSet<>();
        for (WebElement each : vehicleFuelLogTableHeader) {
            allVehicleNamesFromHeader.add(each.getText().toUpperCase());
        }
        return allVehicleNamesFromHeader;
    }
    /*
    US10 AC3
     */

    public void clickEdit() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='dropdown open']//a[@title='Edit']")));
        editBtn.click();
    }

    public void editFuelLog() {
        Actions action = new Actions(driver);
        action.moveToElement(gridRows.get(0).findElement(By.xpath("//div[@class='dropdown']"))).build().perform();
        List<WebElement> editGroup = gridRows.get(0).findElements(By.xpath("//li[@class='launcher-item']"));
        editGroup.get(1).click();

        BrowserUtils.wait(3);
    }

    public String getCurrentURL() {
        BrowserUtils.waitForPageToLoad(3);
        String URL=Driver.getDriver().getCurrentUrl();
        return URL;
    }

    public void deleteFuelLog() {
        BrowserUtils.wait(5);
        Actions action = new Actions(driver);
        action.moveToElement(gridRows.get(0).findElement(By.xpath("//div[@class='dropdown']"))).build().perform();
        List<WebElement> editGroup = gridRows.get(0).findElements(By.xpath("//li[@class='launcher-item']"));
        editGroup.get(2).click();
        BrowserUtils.wait(3);
    }
    public String getDeleteButton(){
        String dangerBtnText=dangerBtn.getText();
        return dangerBtnText;
    }

    /*
    US10 AC 4
     */
    public void resetVFL() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Reset']")));
        resetBtn.click();
    }


}


