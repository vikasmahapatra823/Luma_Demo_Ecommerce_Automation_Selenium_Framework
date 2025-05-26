package Runner;

import Constants.ApplicationConstant;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


@CucumberOptions(features = {"classpath:FeatureFiles/App_E2E_Flow.feature"},
        glue = {"classpath:stepDefinition"},
        tags = "@E2E-Test",
        plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true
)

public class App_E2E_Runner extends AbstractTestNGCucumberTests {

    public static WebDriver driver;

    @BeforeClass
    @Parameters("Chrome-Headless")
    public static void Setup(String param) {
        launchBrowser(param);
        ApplicationConstant.driverMap.put(Thread.currentThread().threadId(), driver);
        driver.manage().window().maximize();

    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    public static void launchBrowser(String browserName) {

        switch (browserName) {
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Chrome-Headless":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                options.addArguments("--disable-notifications");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
                break;
            case "ChromeDebugger":
                ChromeOptions opt = new ChromeOptions();
                opt.setExperimentalOption("debuggerAddress", "localhost:");
                driver = new ChromeDriver(opt);
                break;
            case "FireFox":
                driver = new FirefoxDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
            default:
                System.out.println("Browser not found");

        }
    }
}
