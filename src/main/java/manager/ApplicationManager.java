package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

//    WebDriver wd;
    EventFiringWebDriver wd;
    HelperUser helperUser;
    HelperCar helperCar;

    public void init() {
//        wd = new ChromeDriver();
        wd = new EventFiringWebDriver(new ChromeDriver());
        logger.info("All tests run in Chrome browser");
        helperUser = new HelperUser(wd);
        helperCar = new HelperCar(wd);
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.navigate().to("https://ilcarro.web.app/");
        logger.info("logger started --> " + wd.getCurrentUrl());
        wd.register(new ListenerWebDriver(logger));
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperCar getHelperCar() {
        return helperCar;
    }

    public void stop() {
        wd.quit();
    }
}
