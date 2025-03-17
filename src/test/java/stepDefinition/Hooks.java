package stepDefinition;

import Constants.ApplicationConstant;
import JavaCommands.SeleniumCommands;
import Constants.PathConstant;
import JavaUtils.GenericJavaUtilities;
import Page.actions.GenericAction;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

import static Constants.ApplicationConstant.currentFeatureProps;

public class Hooks extends SeleniumCommands {

    static SeleniumCommands seleniumCommands = new SeleniumCommands();
    GenericJavaUtilities genericJavaUtilities = new GenericJavaUtilities();

    @Before
    public void beforeScenario(Scenario scenario) throws IOException {
        ApplicationConstant.currentScenario = scenario;
        String currentFilePath = scenario.getUri().toString();
        System.out.println("File Path: >> "+currentFilePath);

        ApplicationConstant.currentFeature = currentFilePath.substring(currentFilePath.lastIndexOf("/") + 1).split("\\.")[0];
        genericJavaUtilities.DynamicPropertiesManager(ApplicationConstant.currentFeature);
        genericJavaUtilities.clearProperties(PathConstant.Current_Execution_Properties_File + ApplicationConstant.currentFeature + ".properties");

    }

    @After
    public void afterScenario(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            takeScreenshot();
        }
        else {
            takeScreenshot();
        }

    }

}
