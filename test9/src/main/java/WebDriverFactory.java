import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    public static WebDriver createWebDriver(DriverType driverType){
        WebDriver driver;
        switch (driverType){
            case CHROME:
                driver = createChromeDriver();
                 break;
            case IE:
                driver = createIEDriver();
                break;
            default:
                throw new RuntimeException("Not supported webdriver");
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }

    private static WebDriver createIEDriver(){
        System.setProperty("webdriver.ie.driver", "src/main/resources/IEDriverServer.exe");
        return new InternetExplorerDriver();
    }

    private static WebDriver createChromeDriver(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        return new ChromeDriver();
    }
}
