package noahseleniumprac;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class Base {

    private String browserName = "chrome";

    public WebDriver driver;

   // public FirefoxDriver driver;
    @BeforeMethod
    public void openBrowser() {
        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.ebay.com");
    }
    //"https://www.amazon.com"


    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    public void waitFor(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException ignored) {
        }
    }

  //Thread.sleep();try{ thtread.sleep)();}catch(){}
    //Thread.sleep();try {thread.sleep);}catch(){}
   //try{ thread.sleep()}catch(){}


    public void searchById(String id, String key) {
        driver.findElement(By.id(id)).sendKeys(key);
    }
    //driver.findElement(By.id("")).sendKeys();
    //driver.findElement(By.id("")).SendKeys();
    public void clickById(String id2) {
        driver.findElement(By.id(id2)).click();
    }
    //driver.findElement(By.id(id2)).click();
    //driver.findElement(By.id(id2)).click();
    //driver.findElement(By.id(id2)).click();


    public void clickLinkText(String link) {
        driver.findElement(By.linkText(link)).click();
    }

    public HashMap<String, String> getMapOfWindows() {
        Set<String> windowHandles = driver.getWindowHandles();
        HashMap<String, String> windowMap = new HashMap<>();
        for (String x : windowHandles) {
            driver.switchTo().window(x);
            String actualTitle = driver.getTitle();
            windowMap.put(actualTitle, x);
        }
        return windowMap;
    }
    //driver.getWindowHandles();
    //driver.switchTo().window(x);
    //driver.getWindowHandles();
    //driver.switchTo.window();


    public void openInNewTabByLinkText(String linkText) {
        driver.findElement(By.linkText(linkText)).sendKeys(Keys.CONTROL, Keys.ENTER);


    }

    public void mouseHoverTo(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
    //Actions actions = new Actions(driver);
    //actions.moveToElement(element).perform();
    //action.moveToElement(element).perform();
    //actions.moveToElement(element.perform();
    //actions.moveToELement(element).perform();

    //public void mouseHoverTo (WebElement element) {
    //Action actions = new Actions(driver)
    //actions.moveToElement(element).perform();

    public void switchToTabByTitle(String value) {
        driver.switchTo().window(value);
    }
    //driver.switchTo().window(value);
    //driver.switchTo().window(value);
}
