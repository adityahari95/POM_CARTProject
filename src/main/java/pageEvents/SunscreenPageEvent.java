package main.java.pageEvents;

import main.java.pageObjects.SunscreenPageElements;
import main.java.pageObjects.TemperaturePageElement;
import main.java.utils.ElementFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;


public class SunscreenPageEvent {

    public void buySunscreen() throws InterruptedException {

        ElementFetch elementFetch = new ElementFetch();
        int temperature = Integer.parseInt
                (elementFetch.getWebElement
                        ("XPATH", TemperaturePageElement.temperatureText).
                        getText().substring(0, 2).replaceAll(" ", ""));

        if (temperature > 34) {

            //Navigating to Sunscreen Page
            elementFetch.getWebElement
                    ("XPATH", SunscreenPageElements.buyUrl).click();

            //Adding First Product
            elementFetch.getWebElement
                    ("XPATH", SunscreenPageElements.addingFirstSunscreenProduct).click();


            //Adding Second Product
            elementFetch.getWebElement
                    ("XPATH", SunscreenPageElements.addingSecondSunscreenProduct).click();

            System.out.println("Apply Sunscreen as weather is " + temperature + "'C and it's quite Sunny");

            //Navigate to Checkout
            CheckoutPageEvent checkoutPageEvent = new CheckoutPageEvent();
            checkoutPageEvent.Checkout();


        }
    }
}
