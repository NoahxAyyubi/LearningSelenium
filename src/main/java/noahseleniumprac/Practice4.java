package noahseleniumprac;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Practice4 {

    public ChromeDriver driver;

    @BeforeMethod
    public void openBrowser(){

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://www.amazon.com");
        //"https://www.amazon.com"
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    public void waitFor(int seconds) {
      try{
        Thread.sleep(1000 * seconds);
    }catch(InterruptedException  ignored){}}



    public  void searchById(String id, String key){
        driver.findElement(By.id(id)).sendKeys(key);
    }

    public void clickById(String id2){
        driver.findElement(By.id(id2)).click();
    }

    @Test (priority = 0)

    public void validateUserCanSearch(){


        searchById("twotabsearchtextbox","java books");
        clickById("nav-search-submit-button");
        waitFor(5);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);


    }
    @Test (priority = 1)

    public void validateUserCanSignIn(){

        clickById("nav-link-accountList-nav-line-1");
        searchById("ap_email","javabooks@gmail.com");
        waitFor(5);

    }

@Test (enabled = false)
    public void validateUserCanPurchase(){}
}
