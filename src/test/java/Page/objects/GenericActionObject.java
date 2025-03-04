package Page.objects;

import org.openqa.selenium.By;

public class GenericActionObject {


    public static By USERNAME_LOCATOR = By.cssSelector("#email");
    public static By PASSWORD_LOCATOR = By.cssSelector("#password");
    public static By LOGIN_BTN = By.xpath("//button[contains(@class, 'action login')]");

    public static By CUSTOMER_MENU = By.xpath("//div[@class='panel header']//button[@type='button']");
    public static String CUSTOMER_MENU_LINK (String linktText){
        return "//div[@class='customer-menu' and @aria-hidden='false']//a[normalize-space(text())='"+linktText+"']";

    }
    public static By MENU_HOVER_LINK (String linkBar){
        return By.xpath("(//span[@aria-haspopup='true']//following::span[normalize-space(text())='"+linkBar+"'])[last()]");

    }

    public static By messageAddProduct = By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");


    public static By productPrice(String product){
       return By.xpath("(//a[normalize-space(text())='"+product+"']/../..//span[@class='price'])[last()]");
    }
    public static By cartIcon = By.xpath("//*[@class='shopping_cart_link']");

    public By headerContent(String strText){
        return By.xpath("//*[@class='title'][text()='"+strText+"']");
    }

    public By paraContent(String paraText){
        return By.xpath("//*[text()='"+paraText+"']");
    }

    public By actionButton(String btn){
        return By.xpath("//button[text()='"+btn+"'] | //a[text()='"+btn+"'] | //input[@value='"+btn+"'] || //input[@type='"+btn+"']");
    }

    public static By productItem(String itemName){

        return By.xpath("//a[normalize-space(text())='"+itemName+"']/../..//button[@title='Add to Cart']");

    }

    public static By sortingValue(String sortingOption){
        return By.xpath("//option[normalize-space(text())='"+sortingOption+"']");
    }

    public static By sorting(){
        return By.xpath("//select[@class='sorter-options']");
    }

    public static By productOptions(String product, String option){
        return By.xpath("//a[normalize-space(text())='"+product+"']/../..//div[@data-option-label='"+option+"']");
    }

    public static By myCart(String cart){
        return By.xpath("(//a[@class='action showcart']/span[normalize-space(text()='"+cart+"')])[last()]");
    }



}
