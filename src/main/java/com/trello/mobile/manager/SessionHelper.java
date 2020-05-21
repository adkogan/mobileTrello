package com.trello.mobile.manager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {

        super(wd);
    }


    public void login (String email, String pwd)  {
        initLogin();
        fillLoginForm(email, pwd);
        confirmLogin();
    }


    public void initLogin() {
       waitForElementLocatedAndClick(By.cssSelector("a[href*='login']"), 10);
    }

    public void fillLoginForm(String userEmail, String password) {
        type(By.name("user"), userEmail);
        type(By.cssSelector("input#password"), password);
        click(By.cssSelector("#login.button-green"));

    }

    public void confirmLogin() {
        click(By.id("login"));
    }


}
