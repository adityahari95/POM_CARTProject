package main.java.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import test.java.BaseTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SuiteListener implements ITestListener, IAnnotationTransformer {

    @Override
    public void onTestFailure(ITestResult iTestResult){
        String fileName = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + iTestResult.getMethod().getMethodName();
        File f = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);

        try{
            FileHandler.copy(f,new File(fileName + ".png"));
        }
        catch (IOException e){
            e.printStackTrace();

        }
    }
    @Override
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method){
        iTestAnnotation.setRetryAnalyzer(RetryAnalyser.class);

    }




}
