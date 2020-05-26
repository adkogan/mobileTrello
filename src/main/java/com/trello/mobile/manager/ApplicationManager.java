package com.trello.mobile.manager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    //private final boolean headless;
    String browser;
    AppiumDriver driver;
    DesiredCapabilities capabilities;
 //   BoardHelper board;
 //   TeamHelper team;
    SessionHelper session;
 //   ProfileHelper profile;
    Properties properties;
    WelcomePage welcome;

//    public ApplicationManager(
//            String browser,
//            String headless
//    ) {
//        this.browser = browser;
//        this.headless = "true".equals(headless);
//    }

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    public void init() throws InterruptedException, IOException {
        logger.info("Initialisation started");

        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Nexus_5X_API_26");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");

        capabilities.setCapability("appPackage", "com.trello");
        capabilities.setCapability("appActivity", ".home.HomeActivity");

        capabilities.setCapability("app", "/home/adkogan/git/mobileTrello/apk/Trello_new.apk");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Ready!");
       // driver.manage().window().maximize();

        properties = new Properties();

        String target = System.getProperty("target", "local");
       // properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        welcome = new WelcomePage(driver);
        session = new SessionHelper(driver);
      //  session.login(properties.getProperty("web.user"), properties.getProperty("web.pwd"));

// Thread.sleep(2000);

//        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-test-id=header-member-menu-button]")));
//        board = new BoardHelper(wd);
//        team = new TeamHelper(wd);
//        profile = new ProfileHelper(wd);

    }


//    public boolean isOnBoardsPage() {
//        String url = driver.getCurrentUrl();
//        return url.contains("boards");
//    }

    public void stop() {

        driver.quit();
        logger.info("Test finished");
    }

//    public BoardHelper getBoard() {
//        return board;
//    }

    public SessionHelper getSession() {
        return session;
    }

    public WelcomePage getWelcome(){
        return welcome;
    }

//    public TeamHelper getTeam() {
//
//        return team;
//    }


//    public ProfileHelper getProfile() {
//        return profile;
//    }
}
