package JavaCommands;

import Constants.ApplicationConstant;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;


public class SeleniumCommands {

    public String methodFailureMessage = null;
    public WebDriver driver;

    public static WebDriver getWebdriver() {
        return ApplicationConstant.driverMap.get(Thread.currentThread().threadId());
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void browserNavigation(String action, String URL) {

        try {
            String browserAction = action.toUpperCase();

            switch (browserAction) {

                case "BACK":
                    getWebdriver().navigate().back();
                    break;

                case "FORWARD":
                    getWebdriver().navigate().forward();
                    break;

                case "REFRESH":
                    getWebdriver().navigate().refresh();

                case "NAVIGATE":
                    getWebdriver().navigate().to(URL);

                default:
                    methodFailureMessage = "Selected action is not Present in list";
                    System.out.println(methodFailureMessage);
                    break;
            }
        } catch (Exception e) {
            methodFailureMessage = "Unable to Perform Action " + action + " because of an Exception " + e.getMessage();


        }

    }

    public static String getCurrentURL(){
        return getWebdriver().getCurrentUrl();
    }

    public void mouseAction(By element, String actionName) {
        try {
            WebElement webElement = findElement(element);
            if ("HOVERING".equalsIgnoreCase(actionName)) {
                new Actions(getWebdriver())
                        .moveToElement(webElement)
                        .perform();
            } else if ("RIGHT-CLICK".equalsIgnoreCase(actionName)) {
                new Actions(getWebdriver())
                        .contextClick(webElement)
                        .perform();
            } else if ("DOUBLE CLICK".equalsIgnoreCase(actionName)) {
                new Actions(getWebdriver())
                        .doubleClick(webElement)
                        .perform();
            }
        } catch (Exception e) {
            methodFailureMessage = "Unable to Peform the " + actionName + " Mouse Action " + e.getMessage();
        }

    }

    public void keyBoardAction(By element, String actionName){
        try{
            WebElement webElement = findElement(element);
            if (actionName.equalsIgnoreCase("CLEAR INPUT")) {
                webElement.sendKeys(Keys.CONTROL + "a");
                webElement.sendKeys(Keys.BACK_SPACE);
            }
            else if(actionName.equalsIgnoreCase("PRESS TAB")){
                webElement.sendKeys(Keys.TAB);
            }
            else if(actionName.equalsIgnoreCase("OPEN NEW TAB")){
                webElement.sendKeys(Keys.CONTROL + "t");
            }
        }
        catch (Exception e){
            System.out.println("The exception message is: "+methodFailureMessage);
        }
    }

    public void SendInput(By ele, String input) {

        try {
            WebElement webElement = findElement(ele);
            webElement.sendKeys(input);
        } catch (Exception e) {
            methodFailureMessage = "Unable to send Data because of an Exception" + e.getMessage();

        }
    }

    public void clearText(By ele){
        try {
            WebElement element = findElement(ele);
            element.clear();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void elementVisibility(Integer Seconds, By element) throws Exception {
        try {
            WebElement ele = findElement(element);
            WebDriverWait wait = new WebDriverWait(getWebdriver(), Duration.ofSeconds(Seconds));
            wait.until(ExpectedConditions.visibilityOf(ele));
        }
        catch (Exception e){
            throw new Exception("Element not found :: " + e.getMessage());
        }
    }

    public void textVisibility(Integer seconds, By locator , String txt) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(getWebdriver(), Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, txt));
        }
        catch (Exception e){
            throw new Exception("Element not found :: " + e.getMessage());
        }
    }

    public void explicitWait(Integer Seconds, By element) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(getWebdriver(), Duration.ofSeconds(Seconds));
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            System.out.println("Element : " + element + " is Visible");
        } catch (Exception e) {
            throw new Exception("Element not found :: " + e.getMessage());
        }
    }

    public void implicitWait(Integer Seconds) throws Exception {
        try {
            getWebdriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Seconds));
        } catch (Exception e) {
            throw new Exception("Global Waits is Not Working: " + e.getMessage());
        }
    }

    public void waitUntilReadyToClick(By element)  {

        Wait<WebDriver> wait = new FluentWait<>(getWebdriver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void javaScriptClickElement(By element){
        try{
            WebElement ele = findElement(element);
            waitUntilReadyToClick(element);
            JavascriptExecutor js = (JavascriptExecutor) getWebdriver();
            js.executeScript("arguments[0].click();", ele);
        }
        catch (Exception e){
            System.out.println("Element not clicked - " + element + "Exception: "+methodFailureMessage);
        }
    }


    public void clickElement(By element)  {
        try {
            waitUntilReadyToClick(element);
            WebElement ele = findElement(element);
            ele.click();
        }
        catch (Exception e){
            System.out.println("Element not clicked - " + element + "Exception: "+methodFailureMessage);
        }
    }

    public void scrollToElement(By element)  {
        try {
            WebElement element1 = findElement(element);
            JavascriptExecutor executor = (JavascriptExecutor) getWebdriver();
            executor.executeScript("arguments[0].scrollIntoView(true);", element1);
            Thread.sleep(500);
        }
        catch (Exception e){
            System.out.println("The Exception Message is: "+ e.getMessage());
        }

    }

    public String getText(By element) {
        try {
            WebElement element1 = findElement(element);
            return element1.getText();
        }
        catch (Exception e){
            return "The Execption Message is: "+e.getMessage();
        }

    }

    public WebElement findElement(By locator) throws Exception {
        WebElement element = null;
        try {
            element = getWebdriver().findElement(locator);
        } catch (Exception e) {
            methodFailureMessage = "FindElement method failed because of exception " + e.getMessage();
            throw new Exception(methodFailureMessage);
        }
        return element;
    }

    public List<WebElement> Elements(By locator) throws Exception {
        List<WebElement> elements = null;
        try {
            elements = getWebdriver().findElements(locator);
        } catch (Exception e) {
            methodFailureMessage = "FindElements method failed because of exception " + e.getMessage();
            throw new Exception(methodFailureMessage);
        }
        return elements;
    }

    public void highlightText(By element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getWebdriver();
            js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 1px solid black;');", findElement(element));
        } catch (Exception exception) {
            System.out.println("Element couldn't be highlighted");
        }

    }

    public void verifyPresence(By name) throws Exception {

        try {
            if (!getWebdriver().findElements(name).isEmpty()) {
                System.out.println("Element found " + name);
                ApplicationConstant.IS_HIGHLIGHT_TEXT = true;
            } else {
                System.out.println("Element Not found " + name);
                ApplicationConstant.IS_HIGHLIGHT_TEXT = false;
                String text = name.toString();
                String splitterString = text.split("\"")[1];
                splitterString = splitterString.split("\"")[0];
                ApplicationConstant.currentScenario.log("<font color=red size=\"+3\"><b>" + splitterString + " is not there on the page" + "</b></font>");
                throw new Exception("Element not found");
            }
        } catch (Exception e) {
            throw new Exception("Unable to find element :: " + e.getMessage());
        }

    }
    public static void takeScreenshot() throws IOException {
        File src = ((TakesScreenshot) getWebdriver()).getScreenshotAs(OutputType.FILE);
        byte[] fileContent = FileUtils.readFileToByteArray(src);
        ApplicationConstant.currentScenario.attach(fileContent, "image/png", "");
        }
    }








