package Page.actions;

import Constants.ApplicationConstant;
import JavaCommands.SeleniumCommands;
import JavaUtils.GenericJavaUtilities;
import Page.objects.GenericActionObject;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class GenericAction extends SeleniumCommands {

    GenericJavaUtilities genericJavaUtilities = new GenericJavaUtilities();
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

    public void loginCredentials(String Users) throws Exception {
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

    public void clickOnHyperlink(String link) throws Exception {
        scrollToElement(GenericActionObject.hyperLink(link));
        clickElement(GenericActionObject.hyperLink(link));
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

    public void verifyPageHeader(String header) {

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

            String keyName = productName.replaceAll("\\s", "");
            genericJavaUtilities.setProperty(keyName + "_color", color);
            genericJavaUtilities.setProperty(keyName + "_size", size);

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
        double subTotal = 0.00;
        for (int i = 0; i < items.size(); i++) {
            productName = items.get(i).get("Product Name");
            details = items.get(i).get("Details");
            if (details.contains("Price")) {
                verifyPresence(GenericActionObject.productPrice(productName));
                String value = getText(GenericActionObject.productPrice(productName));
                ApplicationConstant.globalDataMap.put(details, value);
                highlightText(GenericActionObject.productPrice(productName));
                subTotal += Double.parseDouble(value.split("€")[1].trim());
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
        String sub_total = String.join("", "€", String.format("%.2f", subTotal));
        genericJavaUtilities.setProperty("Sub_Total", sub_total);
    }

    public void sortingBy(String options, String sort)  {
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

    public void myCart(String cart) throws Exception {
        scrollToElement(GenericActionObject.myCart(cart));
        clickElement(GenericActionObject.myCart(cart));
        explicitWait(3000, GenericActionObject.cartSubtotal);
    }

    public void verifyMiniCartItems(DataTable dt) throws Exception {
        List<Map<String, String>> data = dt.asMaps(String.class, String.class);
        for (Map<String, String> datum : data) {
            String key = "";
            String elementName = "";
            key = datum.get("Key");
            elementName = datum.get("Element");

            if (elementName.equalsIgnoreCase("Sub Total")) {
                String expected_subTotal = genericJavaUtilities.getProperty(key);
                textVisibility(10, GenericActionObject.cartSubtotal, expected_subTotal);
                String actual_subTotal = getText(GenericActionObject.cartSubtotal);

                Assert.assertEquals(actual_subTotal, expected_subTotal, "The actual amount is not matching with expected amount");
                verifyPresence(GenericActionObject.cartSubtotal);
                highlightText(GenericActionObject.cartSubtotal);
            } else {
                String actual_amt = getText(GenericActionObject.productPriceOnCart(elementName));
                String expected_amt = genericJavaUtilities.getProperty(key);
                Assert.assertEquals(actual_amt, expected_amt, "The actual amount is not matching with expected amount");
            }

        }
    }

    public void saveIntoFeatureProps(DataTable dt) {
        List<Map<String, String>> data = dt.asMaps(String.class, String.class);
        for (int i = 0; i < data.size(); i++) {
            String key = "";
            String value = "";
            String val = "";
            key = data.get(i).get("Key");
            value = data.get(i).get("Value");

            if (value.contains("Price")) {
                if (ApplicationConstant.globalDataMap.containsKey(value))
                    val = ApplicationConstant.globalDataMap.get(value).trim();
                System.out.println("The Amount is: " + val);
            }
            genericJavaUtilities.setProperty(key, val);
            String dat = genericJavaUtilities.getProperty(key);
            System.out.println("The stored amount from property file is: " + dat);

        }

        genericJavaUtilities.saveProperties();

    }

    public void updateQTY(DataTable dt)  {

        List<Map<String, String>> data = dt.asMaps(String.class, String.class);
        double getSubTotal;
        String qty = "";
        String productName = "";
        String key = "";
        for (Map<String, String> datum : data) {
            productName = datum.get("Product Name");
            qty = datum.get("QTY");
            key = datum.get("Key");
            keyBoardAction(GenericActionObject.updateProductQty(productName), "CLEAR INPUT");
            SendInput(GenericActionObject.updateProductQty(productName), qty);
            keyBoardAction(GenericActionObject.updateProductQty(productName), "PRESS TAB");
            clickElement(GenericActionObject.updateButton(productName));

            if (key.contains("Updated_")) {
                String getKey = key.split("Updated_")[1].trim();
                double getValue = Double.parseDouble(genericJavaUtilities.getProperty(getKey).split("€")[1].trim());
                double getCost = getValue * Integer.parseInt(qty);

                String value = String.join("", "€", String.format("%.2f", getCost));
                ApplicationConstant.globalDataMap.put(productName, value);
                genericJavaUtilities.setProperty(key, value);

                getSubTotal = Double.parseDouble(genericJavaUtilities.getProperty("Sub_Total").split("€")[1].trim());
                double getUpdatedCost = getSubTotal + (getCost - getValue);
                String getUpdatedValue = String.join("", "€", String.format("%.2f", getUpdatedCost));
                genericJavaUtilities.setProperty("Sub_Total", getUpdatedValue);

            } else {
                System.out.println("The given key is not valid or Qty may be empty");
            }
        }
        }

        public void urlValidation(String url){
            String getUrl =  getCurrentURL();
            System.out.println("The actual url is: "+getUrl);
            if(getUrl.endsWith(url)){
                System.out.println("The user is navigated to the correct page");
            }
            else{
                System.out.println("The user is navigated to the incorrect page");
            }
        }
    }




