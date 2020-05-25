package com.trello.mobile.manager;

import com.google.common.io.Files;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HelperBase {
    AppiumDriver driver;


    public HelperBase(AppiumDriver driver) {

        this.driver = driver;
    }

    public void returnToHomePage() throws InterruptedException {
        click(By.cssSelector("[name ='house']"));
//        click(By.xpath("//*[@name='house']"));
//        Thread.sleep(4000);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        waitForElementLocatedAndClick(locator, 15);
        //click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void confirm() {
        click(By.cssSelector(".js-confirm"));
    }

    public void waitForElementLocatedAndClick(By locator, int timeOut) {
        new WebDriverWait(driver, timeOut)
                .until(ExpectedConditions.presenceOfElementLocated(locator))
                .click();
    }

//    public WebElement waitForElement(By locator, int timeOut) {
//        return new WebDriverWait(wd, timeOut)
//
//                .until(ExpectedConditions.presenceOfElementLocated(locator));
//    }


    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public boolean waitForElementsPresent(By locator, int timeout){
     List <WebElement>elements = new WebDriverWait(driver, timeout)
             .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
     return elements.size()>0;
    }

    public void takeScreenshot(long timeMillis){
    File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File screenshot = new File("screen" + timeMillis + ".png");
        try {
            Files.copy(tmp,screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void refreshPage() {
        driver.navigate().refresh();
    }


}
