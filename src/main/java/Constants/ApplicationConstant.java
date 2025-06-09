package Constants;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class ApplicationConstant {

    public static Scenario currentScenario;

    public static String currentFeatureProps;
    public static String currentFeature;
    public static Map<Long, WebDriver> driverMap = new HashMap<>();

    public static Map<String, String> globalDataMap = new HashMap<>();

    public static String current_time_stamp;

    public static boolean IS_HIGHLIGHT_TEXT = false;
    public static final String PORTAL_URL = "https://demo-m2.bird.eu/";
    public static final String USERNAME = "autobot@mail.com";

    public static boolean IS_JENKINS_EXECUTION = false;
    public static final String PASSWORD = "Autobot@123";


}
