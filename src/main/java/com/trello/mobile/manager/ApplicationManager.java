package com.trello.mobile.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final boolean headless;
    String browser;
    WebDriver wd;
 //   BoardHelper board;
 //   TeamHelper team;
    SessionHelper session;
 //   ProfileHelper profile;
    Properties properties;

    public ApplicationManager(
            String browser,
            String headless
    ) {
        this.browser = browser;
        this.headless = "true".equals(headless);
    }

    public void init() throws InterruptedException, IOException {

        if (browser.equals(BrowserType.CHROME)) {
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--no-sandbox"); //Bypass OS security model
                options.addArguments("--start-maximized");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--headless");
            }
            wd = new ChromeDriver(options);
        }
        if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        }//if(browser.equals(BrowserType.EDGE)){
        //wd = new EdgeDriver();
        //  }



        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.manage().window().maximize();
        properties = new Properties();
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));


        wd.navigate().to(properties.getProperty("web.baseURL"));




        session = new SessionHelper(wd);
        session.login(properties.getProperty("web.user"), properties.getProperty("web.pwd"));

// Thread.sleep(2000);

        new WebDriverWait(wd, 20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-test-id=header-member-menu-button]")));
//        board = new BoardHelper(wd);
//        team = new TeamHelper(wd);
//        profile = new ProfileHelper(wd);



    }


    public boolean isOnBoardsPage() {
        String url = wd.getCurrentUrl();
        return url.contains("boards");
    }

    public void stop() {
        wd.quit();
    }

//    public BoardHelper getBoard() {
//        return board;
//    }

    public SessionHelper getSession() {
        return session;
    }

//    public TeamHelper getTeam() {
//
//        return team;
//    }


//    public ProfileHelper getProfile() {
//        return profile;
//    }
}
