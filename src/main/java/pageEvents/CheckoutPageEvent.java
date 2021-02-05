package main.java.pageEvents;

import main.java.pageObjects.CheckoutPageElement;
import main.java.utils.ElementFetch;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class CheckoutPageEvent {

    public void Checkout() {
        ElementFetch elementFetch = new ElementFetch();

        //Verifying the Cart
        Assert.assertEquals(elementFetch.getWebElement("XPATH",
                CheckoutPageElement.Cart).getText(), "Cart - 2 item(s)");

        //Moving to Cart
        elementFetch.getWebElement("XPATH", CheckoutPageElement.Cart).click();

        //Verifying the Payment Page
        Assert.assertEquals(
                elementFetch.getWebElement("XPATH",
                        CheckoutPageElement.proceedToPayment).getText(),"Pay with Card");

    }

}

