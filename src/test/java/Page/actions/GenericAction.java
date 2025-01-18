package Page.actions;

import Constants.ApplicationConstant;
import JavaCommands.SeleniumCommands;
import Page.objects.GenericActionObject;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class GenericAction extends SeleniumCommands {

    GenericActionObject genericActionObject = new GenericActionObject();

    public void navigateToUrl(String portal) {
        if (portal.equalsIgnoreCase("SWAGS LABS")) {
            browserNavigation("NAVIGATE", ApplicationConstant.PORTAL_URL);
        } else if (portal.equalsIgnoreCase("LUMA")) {
            browserNavigation("NAVIGATE", ApplicationConstant.PORTAL_URL);
        } else {
            System.out.println("URL is Not Found");
        }
    }

    public void loginCredentials(String Users) {
        String username = "", password = "";
        if (Users.equalsIgnoreCase("Standard User")) {
            username = ApplicationConstant.USERNAME;
            password = ApplicationConstant.PASSWORD;
        } else if ("Customer User".equalsIgnoreCase(Users)) {
            username = ApplicationConstant.USERNAME;
            password = ApplicationConstant.PASSWORD;
        } else {
            System.out.println("Login Credentials are Incorrect");
        }

        SendInput(GenericActionObject.USERNAME_LOCATOR, username);
        SendInput(GenericActionObject.PASSWORD_LOCATOR, password);
        clickElement(GenericActionObject.LOGIN_BTN);

    }

    public void clickLinkText(String text) throws Exception {
        explicitWait(5, GenericActionObject.MENU_HOVER_LINK(text));
        clickElement(GenericActionObject.MENU_HOVER_LINK(text));
    }

    public void customerMenuItems(String linkText) {
        clickElement(GenericActionObject.CUSTOMER_MENU);
        clickElement(By.xpath(GenericActionObject.CUSTOMER_MENU_LINK(linkText)));
    }

    public void mouseHoverClickAction(String actionName, String targetElement) throws Exception {
        explicitWait(5, GenericActionObject.MENU_HOVER_LINK(targetElement));
        mouseAction(GenericActionObject.MENU_HOVER_LINK(targetElement), actionName);
    }

    public void verifyPageContent(String text) {
        highlightText(genericActionObject.paraContent(text));

    }

    public void verifyPageHeader(String header) throws Exception {
        highlightText(genericActionObject.headerContent(header));
    }

    public void clickButton(String btn) {
        clickElement(genericActionObject.actionButton(btn));
    }

    public void addProduct(DataTable dt) throws Exception {
        List<Map<String, String>> items = dt.asMaps(String.class, String.class);

        for (Map<String, String> item : items) {
            String productName = item.get("Product Name");
            String color = item.get("Color");
            String size = item.get("Size");
            String expectedText = item.get("Text Message");
            try {
                clickElement(GenericActionObject.productOptions(productName, color));
                clickElement(GenericActionObject.productOptions(productName, size));
                clickElement(GenericActionObject.productItem(productName));
                explicitWait(5, GenericActionObject.messageAddProduct);
                scrollToElement(GenericActionObject.messageAddProduct);
                verifyPresence(GenericActionObject.messageAddProduct);
                Thread.sleep(3000);
                highlightText(GenericActionObject.messageAddProduct);
                String actualText = getText(GenericActionObject.messageAddProduct);
                System.out.println("The actual Text >>>>> " + actualText);
                Assert.assertEquals(actualText, expectedText, "The Expected Text Message does not match the actual message:");
            } catch (Exception e) {
                System.err.println("Error while processing product: " + productName);
                e.printStackTrace();
                throw e;
            }
        }
    }


    public void verifyProductDetails(DataTable dt) throws Exception {
        List<Map<String, String>> items = dt.asMaps(String.class, String.class);

        String productName = "";
        String details = "";

        for (int i = 0; i < items.size(); i++) {
            productName = items.get(i).get("Product Name");
            details = items.get(i).get("Details");
            if (details.contains("Price")) {
                verifyPresence(GenericActionObject.productPrice(productName));
                String value = getText(GenericActionObject.productPrice(productName));
                ApplicationConstant.globalDataMap.put(details, value);
                highlightText(GenericActionObject.productPrice(productName));
            } else if (details.contains("Size:")) {
                String[] values = details.split(":")[1].split(";");
                for (String value : values) {
                    verifyPresence(GenericActionObject.productOptions(productName, value));
                    highlightText(GenericActionObject.productOptions(productName, value));
                }
            } else if (details.contains("Color:")) {
                String[] values = details.split(":")[1].split(";");
                for (String value : values) {
                    verifyPresence(GenericActionObject.productOptions(productName, value));

                }
            }
        }
    }

    public void sortingBy(String options, String sort) {
        if ("ASC".equalsIgnoreCase(sort)) {
            clickElement(GenericActionObject.sorting());
            clickElement(GenericActionObject.sortingValue(options));
            clickElement(By.xpath("//a[@data-role='direction-switcher']"));
        } else if ("DESC".equalsIgnoreCase(sort)) {
            clickElement(GenericActionObject.sorting());
            clickElement(GenericActionObject.sortingValue(options));
            clickElement(By.xpath("//a[@data-role='direction-switcher']"));
        } else {
            clickElement(GenericActionObject.sorting());
            clickElement(GenericActionObject.sortingValue(options));
        }

    }


}

