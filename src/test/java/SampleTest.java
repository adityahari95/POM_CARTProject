package test.java;

import main.java.pageEvents.CheckoutPageEvent;
import main.java.pageEvents.MoisturizerPageEvent;
import main.java.pageEvents.SunscreenPageEvent;
import main.java.pageEvents.TemperaturePageEvent;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest{


    @Test()
    public void sampleMethodForTemperature(){
        TemperaturePageEvent temperaturePageEvent = new TemperaturePageEvent();
        temperaturePageEvent.getTemperatureDetails();

    }

    @Test()
    public void sampleMethodForMoisturizerAndSunscreen() throws InterruptedException {
        MoisturizerPageEvent moisturizerPageEvent = new MoisturizerPageEvent();
        moisturizerPageEvent.buyMoisturizer();

        SunscreenPageEvent sunscreenPageEvent =new SunscreenPageEvent();
        sunscreenPageEvent.buySunscreen();

    }
   }

