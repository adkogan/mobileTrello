package com.trello.mobile.manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class HelperBase {
    WebDriver wd;
    WebDriverWait wait;

    public HelperBase(WebDriver wd) {

        this.wd = wd;
    }

    public void returnToHomePage() throws InterruptedException {
        click(By.cssSelector("[name ='house']"));
//        click(By.xpath("//*[@name='house']"));
//        Thread.sleep(4000);
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void type(By locator, String text) {
        waitForElementLocatedAndClick(locator, 15);
        //click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    public void confirm() {
        click(By.cssSelector(".js-confirm"));
    }

    public void waitForElementLocatedAndClick(By locator, int timeOut) {
        new WebDriverWait(wd, timeOut)
                .until(ExpectedConditions.presenceOfElementLocated(locator))
                .click();
    }

//    public WebElement waitForElement(By locator, int timeOut) {
//        return new WebDriverWait(wd, timeOut)
//
//                .until(ExpectedConditions.presenceOfElementLocated(locator));
//    }


    public boolean isElementPresent(By locator) {
        return wd.findElements(locator).size() > 0;
    }

    public void takeScreenshot(long timeMillis){
    File tmp = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
    File screenshot = new File("screen" + timeMillis + ".png");
        try {
            Files.copy(tmp,screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void refreshPage() {
        wd.navigate().refresh();
    }


}
