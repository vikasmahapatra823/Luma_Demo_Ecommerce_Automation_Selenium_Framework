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

import static Constants.ApplicationConstant.currentFeatureProps;

public class Hooks extends SeleniumCommands {

    static SeleniumCommands seleniumCommands = new SeleniumCommands();
    GenericJavaUtilities genericJavaUtilities = new GenericJavaUtilities();

    @Before
    public void beforeScenario(Scenario scenario) {
        ApplicationConstant.currentScenario = scenario;
        String currentFilePath = scenario.getUri().toString();
        System.out.println("File Path: >> "+currentFilePath);

        ApplicationConstant.currentFeature = currentFilePath.substring(currentFilePath.lastIndexOf("/") + 1).split("\\.")[0];
        genericJavaUtilities.DynamicPropertiesManager(ApplicationConstant.currentFeature);

    }

//    @AfterStep
//    public void afterStep(Scenario scenario) throws IOException {
//        if(scenario.isFailed() || !scenario.isFailed()){
//         takeScreenshot();
//        }
//    }

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
