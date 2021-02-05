package test.java;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import main.java.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver;
    public ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;

    @BeforeTest
    public void BeforeTest(){
        htmlReporter = new ExtentHtmlReporter( System.getProperty("user.dir")+File.separator+ "reports" + File.separator+ "AutomationReport.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("AutomationReport");
        htmlReporter.config().setReportName("AutomationTestResults");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Tester","Aditya Jha");
    }


    @BeforeMethod
    @Parameters(value={"browserName"})
    public void BeforeMethod(String browserName, Method testMethod){
        logger = extent.createTest(testMethod.getName());
        setupDriver(browserName);
        driver.manage().window().maximize();
        driver.get(Constants.url);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void AfterMethod(ITestResult result){
        if(result.getStatus()==ITestResult.SUCCESS){
            String methodName = result.getMethod().getMethodName();
            String logText = "TestCase:" +methodName + "Passed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            logger.log(Status.PASS,m);
        }else if(result.getStatus()==ITestResult.FAILURE){
            String methodName = result.getMethod().getMethodName();
            String logText = "TestCase:" +methodName + "Failed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
            logger.log(Status.FAIL,m);

        }
        else if(result.getStatus()==ITestResult.SKIP){
            String methodName = result.getMethod().getMethodName();
            String logText = "TestCase:" +methodName + "Skipped";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
            logger.log(Status.SKIP,m);
        }
        driver.quit();
    }

    @AfterTest
    public void afterTestMethod(){
        extent.flush();
    }


    public void setupDriver(String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir") + File.separator +"drivers" + File.separator+"chromedriver.exe");
            driver = new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver",
                    System.getProperty("user.dir") + File.separator +"drivers" + File.separator+"geckodriver.exe");
            driver = new FirefoxDriver();
        }else if(browserName.equalsIgnoreCase("edge")){
            System.setProperty("webdriver.edge.driver",
                    System.getProperty("user.dir") + File.separator +"drivers" + File.separator+"msedgedriver.exe");
            driver = new EdgeDriver();
        }else if(browserName.equalsIgnoreCase("ie")){
            System.setProperty("webdriver.ie.driver",
                    System.getProperty("user.dir") + File.separator +"drivers" + File.separator+"ie.exe");
            driver = new InternetExplorerDriver();
        }
        else {
            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir") + File.separator +"drivers" + File.separator+"chromedriver");
            driver = new ChromeDriver();
        }
    }
}