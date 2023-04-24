package com.woa.base2;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase2 {

    public static WebDriver driver;
    public static Properties properties;
    private static ExtentReports extent;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final String SAUCE_USERNAME = "";
    private final String SAUCE_ACCESS_KEY = "";

    private final String URL_OF_SAUCE = "https://" + SAUCE_USERNAME + ":" + SAUCE_ACCESS_KEY;
    // store the creds in the properties file and read from there
    private final String BROWSERSTACK_USERNAME = properties.getProperty("bs.username");
    private final String BROWSERSTACK_ACCESS_KEY = properties.getProperty("bs.password");
    private final String URL_OF_BS = "https://" + BROWSERSTACK_USERNAME + ":" + BROWSERSTACK_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void waitTillClickable(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }
//    public void waitTillClickable (WebElement element){
//        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
//
//    public void waitTillClickable(WebElement element){
//        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
//    public waitTillClickable(WebElement element){
    // WebdriverWait webDriverWait = new WebDriverWait(driver,25);
    //webDriverWait.until(ExpectedCondition.elementToBeClickable(element));}

    public static void waitTillVisible(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }
//public static void waitTillVisible(){

    //WebDriverWait webDriverWait = new WebDriverWait((driver, 30);
    //webDriverWait.until(ExpectedCondition.visibilityOf(element));
    //WebDriverWait wait = new WebDriverWait(driver, 20);
    //wait.until(ExpectedConditions.visibilityOf(element));

//}
    public static void waitFor(int seconds) {
        try {
            // static sleep
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //@BeforeMethod (alwaysRun = true) (alwaysRun = true) (alwaysRun = true)  (alwaysRun = true) (alwaysRun = true) (alwaysRun = true)
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browserName", "url", "cloud", "os"})
    public void setupBrowser(String browserName, String url, boolean cloud, String os) throws MalformedURLException {
        System.out.println("Setup browser running");
        if (cloud) {
            driver = getRemoteWebDriver();
        } else {
            driver = getLocalWebDriver(browserName, os);
        }
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(url);

    }

    /*
    public void setUpBrowser(String browserName, String url, boolean cloud, String os) throws MalformedURLException {
    System.out.println("Setup browser running");
    if (cloud) {
    driver = getRemoteWebDriver();

   }else{
   driver = getLocalWebDriver(browserName, os);
   }
   driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
   driver.manage().timeouts().implicitlyWait(47 , TimeUnit.SECONDS);
     if (cloud){
     driver = getRemoteWebDriver():
     }else{
     driver = getLocalWebDriver(browserName, os):
     }
*/
    private WebDriver getLocalWebDriver(String browserName, String os) {
        if (browserName.equalsIgnoreCase("chrome")) {
            if (os.equalsIgnoreCase("mac")) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
            } else {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver2.exe");
            }
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else {
            if (os.equalsIgnoreCase("mac")) {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
            } else {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            }
            driver = new FirefoxDriver();
        }
        return driver;
    }


    private WebDriver getRemoteWebDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "101");

        HashMap<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "10");

        capabilities.setCapability("bstack:options", browserstackOptions);
        System.out.println(URL_OF_BS);
        URL url = new URL(URL_OF_BS);
        WebDriver driver = new RemoteWebDriver(url, capabilities);
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public void quitBrowser() {
        driver.quit();
    }

    public void scrollDownToSpecificElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    //Extent Report Setup
    @BeforeSuite(alwaysRun = true)
    public void extentSetup(ITestContext context) {
        ExtentTestManager.setOutputDirectory(context);
        extent = ExtentTestManager.getInstance();
    }

    //Extent Report Setup for each methods
    @BeforeMethod(alwaysRun = true)
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }

    //Extent Report cleanup for each methods
    @AfterMethod(alwaysRun = true)
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(ExtentTestManager.getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(ExtentTestManager.getTime(result.getEndMillis()));
        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "TEST CASE PASSED : " + result.getName());
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, "TEST CASE FAILED : " + result.getName() + " :: " + ExtentTestManager.getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "TEST CASE SKIPPED : " + result.getName());
        }
        ExtentTestManager.endTest();
        extent.flush();
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.captureScreenshot(driver, result.getName());
        }
    }

    //Extent Report closed
    @AfterSuite(alwaysRun = true)
    public void generateReport() {
        extent.close();
    }

    /*private WebDriver getLocalWebDriver(String browserName, String os) {
        if (browserName.equalsIgnoreCase("chrome"))
        if (browserName.equalIgnoreCase("Chrome")){
        {   If (os.equalsIgnoreCase("mac")){
            if (os.equalsIgnoreCase("mac")){
                System.setProperty("webdriver.chrome.driver", "");
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
            } else {
            } else {
                System.setProperty(webdriver.chome.driver", "");
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            }
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else {
            if (os.equalsIgnoreCase("mac")) {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
            } else {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            }
            driver = new FirefoxDriver();
        }
        return driver;*/

}
