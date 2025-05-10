package stepDefinition;

import Constants.ApplicationConstant;
import Page.actions.GenericAction;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.IOException;
import java.util.List;

public class App_E2E_Step_Definitions extends GenericAction {

    GenericAction genericAction = new GenericAction();

    @Given("Navigate to the {string} Ecommerce Website")
    public void navigate_to_the_ecommerce_webiste(String url) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        genericAction.navigateToUrl(url);
        Thread.sleep(2000);
    }


    @And("Login to the Ecommerce Website as {string}")
    public void loginToTheEcommerceWebsiteAs(String applicationUser) throws Exception {
        genericAction.loginCredentials(applicationUser);
    }

    @And("Click on the link {string} via Customer Menu bar")
    public void clickTheLink(String str) throws Exception {
        genericAction.customerMenuItems(str);
    }

    @And("Verify the Page Header is {string}")
    public void verifyThePageHeaderIs(String strText) throws Exception {
        genericAction.verifyPageHeader(strText);

    }

    @Then("Verify the product item is {string}")
    public void verifyTheProductItemIs(String item) {
        genericAction.verifyPageContent(item);

    }

    @And("Verify the product item description is {string}")
    public void verifyTheProductItemDescriptionIs(String description) {
        genericAction.verifyPageContent(description);

    }

    @And("Click on the {string} button")
    public void clickOnTheButton(String btn) throws Exception {
        genericAction.clickButton(btn);
    }

    @And("Wait for {string} seconds")
    public void waitForSeconds(String seconds) throws InterruptedException {
        int milliSeconds = Integer.parseInt(seconds) * 1000;
        Thread.sleep(milliSeconds);
    }

    @And("Get the Inventory item price {string}")
    public void getTheInventoryItemPrice(String price) {
    }


    @And("Fill the below data")
    public void fillTheBelowData(DataTable dt) {

    }

    @And("Add the Following Product into the Cart:")
    public void addTheFollowingProductIntoTheCart(List<String> productNames) {

    }

    @And("Verify the below texts")
    public void verifyTheText(DataTable txt) {

    }

    @And("Click on the link {string}")
    public void clickOnTheLink(String linkText) throws Exception {
        genericAction.clickLinkText(linkText);
    }

    @And("Perform the mouse {string} action on the {string}")
    public void performTheMouseActionOnThe(String actionName, String targetName) throws Exception {
        genericAction.mouseHoverClickAction(actionName, targetName);
    }

    @Then("Add the list of product items & verify the success message upon adding the product items into cart:")
    public void addTheListOfProductItems(DataTable dt) throws Exception {
        genericAction.addProduct(dt);

    }

    @And("Sort By {string} in {string}")
    public void sortByIn(String option, String order) throws Exception {
        sortingBy(option, order);
    }

    @And("Verify the presence & get the product details:")
    public void verifyThePresenceGetTheProductDetails(DataTable dt) throws Exception {
        genericAction.verifyProductDetails(dt);

    }

    @Then("Click cart icon of {string} link")
    public void clickCartIconOfLink(String cart) throws Exception {
        genericAction.myCart(cart);
    }

    @And("Save the following details in feature properties:")
    public void saveTheFollowingDetailsInFeatureProperties(DataTable dt) throws IOException {
        saveIntoFeatureProps(dt);

    }

    @And("Verify the sub total and product price of items on cart:")
    public void verifyTheSubTotalAndProductPriceOfItemsOnCart(DataTable dt) throws Exception {
        verifyMiniCartItems(dt);
    }

    @Then("Update the product items on the shopping cart list:")
    public void updateTheProductItemsOnTheShoppingCartList(DataTable dt) throws Exception {
        updateQTY(dt);

    }

    @And("Click on the hyperlink of {string}")
    public void clickOnTheHyperlinkOf(String link) throws Exception {
        clickOnHyperlink(link);
    }

    @And("Verify the page navigation url ends with {string}")
    public void verifyThePageNavigationUrlEndsWith(String urlPath) {
        urlValidation(urlPath);
    }
}
