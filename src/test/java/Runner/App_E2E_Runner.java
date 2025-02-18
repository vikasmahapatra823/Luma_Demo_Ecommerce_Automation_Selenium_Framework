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
import java.io.IOException;


@CucumberOptions(features = {"classpath:FeatureFiles/App_E2E_Flow.feature"},
        glue = {"classpath:stepDefinition"},
        tags = "@E2E-Test",
        plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true
)

public class App_E2E_Runner extends AbstractTestNGCucumberTests {

    public static WebDriver driver;

    @BeforeClass
    public static void Setup() throws InterruptedException {
        String browser = "Chrome";
        launchBrowser(browser);
        ApplicationConstant.driverMap.put(Thread.currentThread().threadId(), driver);
        driver.manage().window().maximize();

    }

    @AfterClass
    public static void tearDown() throws IOException {

//        driver.quit();

    }

    public static void launchBrowser(String browserName) {

        switch (browserName) {
            case "Chrome":
                driver = new ChromeDriver();
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
