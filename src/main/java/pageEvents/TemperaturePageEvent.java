package main.java.pageEvents;

import main.java.pageObjects.TemperaturePageElement;
import main.java.utils.ElementFetch;
import org.testng.Assert;
import test.java.BaseTest;

public class TemperaturePageEvent {

    public void getTemperatureDetails(){
        ElementFetch elementFetch = new ElementFetch();
        BaseTest.logger.info("Retrieve temperature");

        String temperature = elementFetch.getWebElement("XPATH", TemperaturePageElement.temperatureText).getText();

        Assert.assertTrue(elementFetch.getListWebElements("XPATH",TemperaturePageElement.temperatureText).size()>0,
                "Temperature Page didnt Open");


    }

}
