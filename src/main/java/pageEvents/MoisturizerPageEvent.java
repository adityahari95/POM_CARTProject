package main.java.pageEvents;

import main.java.pageObjects.MoisturizerPageElement;
import main.java.pageObjects.TemperaturePageElement;
import main.java.utils.ElementFetch;


public class MoisturizerPageEvent {

    public void buyMoisturizer() throws InterruptedException {
        ElementFetch elementFetch = new ElementFetch();

        int temperature = Integer.parseInt
                (elementFetch.getWebElement
                        ("XPATH", TemperaturePageElement.temperatureText).getText().substring(0, 2).replaceAll(" ", ""));


        if (temperature < 19) {
            //Navigating to Moisturizer Page
            elementFetch.getWebElement
                    ("XPATH", MoisturizerPageElement.buyUrl).click();


            //Adding First Product
            elementFetch.getWebElement
                    ("XPATH", MoisturizerPageElement.addingFirstMoisturizerProduct).click();


            //Adding Second Product
            elementFetch.getWebElement
                    ("XPATH", MoisturizerPageElement.addingSecondMoisturizerProduct).click();

            System.out.println("Apply Moisturizer as weather is " + temperature + "C and it's quite Cold");

            //Navigate to Checkout
            CheckoutPageEvent checkoutPageEvent = new CheckoutPageEvent();
            checkoutPageEvent.Checkout();


        }
    }
}
