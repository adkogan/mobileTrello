package com.trello.mobile.manager;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(AppiumDriver driver) {

        super(driver);
    }


    public void login (String email, String pwd)  {
        initLogin();
        fillLoginForm(email, pwd);
        //confirmLogin();
    }


    public void initLogin() {
       waitForElementLocatedAndClick(By.id("log_in_button"), 10); //исправить локатор
      // waitForElementLocatedAndClick(By.xpath("//*[@resource-id='com.trello:id/log_in_button']"), 10);
    }

    public void fillLoginForm(String userEmail, String password) {
        type(By.xpath("//*[@resource-id='com.trello:id/user']"), "adkogan@gmail.com");
       // click(By.id("button1"));
        type(By.xpath("//*[@resource-id='com.trello:id/password']"), "5605105zxc");
        click(By.xpath("//*[@resource-id='com.trello:id/authenticate']"));

    }

    public void confirmLogin() {
        click(By.id("login"));
    }


}
