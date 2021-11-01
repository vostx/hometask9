import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import static com.sun.deploy.cache.Cache.copyFile;

public class MyTest {

    WebDriverFactory webDriverFactory = new WebDriverFactory();

    @Test
    public void test1() {
        WebDriver driver = webDriverFactory.createWebDriver(DriverType.CHROME);
            String query = "SoftServe";
            driver.manage().window().maximize();
            driver.get("https://google.com");
            String searchFieldXpath = "//input[@title='Поиск']";

            WebElement searchElement = driver.findElement(By.xpath(searchFieldXpath));
            searchElement.sendKeys(query);
            searchElement.submit();

            List<WebElement> results = driver.findElements(By.xpath(".//h3"));

            boolean isQueryPresentInResult = true;
            for (WebElement result : results) {
                if (result.getText().equalsIgnoreCase(query)) {
                    isQueryPresentInResult = true;
                    break;
                }
            }
            Assert.assertTrue(isQueryPresentInResult);
        driver.findElement(By.id("search")).findElements(By.xpath(".//h3")).get(0).click();

        String URL = driver.getCurrentUrl();
        if(URL.contains("softserve"))
        {
            System.out.println("Landed in correct URL " +
                    "" + URL);

        }else
        {
            System.out.println("Landed in wrong URL");
        }

    }

    @Test
    public void test2() {
        WebDriver driver = webDriverFactory.createWebDriver(DriverType.IE);
        String query = "SoftServe";
        driver.manage().window().maximize();
        driver.get("https://google.com");
        String searchFieldXpath = "//input[@title='Поиск']";

        WebElement searchElement = driver.findElement(By.xpath(searchFieldXpath));
        searchElement.sendKeys(query);
        searchElement.submit();

        List<WebElement> results = driver.findElements(By.xpath(".//h3"));

        boolean isQueryPresentInResult = true;
        for (WebElement result : results) {
            if (result.getText().equalsIgnoreCase(query)) {
                isQueryPresentInResult = true;
                break;
            }
        }
        Assert.assertTrue(isQueryPresentInResult);
        driver.findElement(By.id("search")).findElements(By.xpath(".//h3")).get(0).click();

        String URL = driver.getCurrentUrl();
        if(URL.contains("softserve"))
        {
            System.out.println("Landed in correct URL " +
                    "" + URL);

        }else
        {
            System.out.println("Landed in wrong URL");
        }

    }


    @AfterMethod(alwaysRun = true)
    public void takeScreenshot(ITestResult result) {
        if (!result.isSuccess()) {
            try {
                File scrFile = ((TakesScreenshot) webDriverFactory).getScreenshotAs(OutputType.FILE);
                copyFile(scrFile, new File(result.getName() + "[" + LocalDate.now() + "][" + System.currentTimeMillis() + "].png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}