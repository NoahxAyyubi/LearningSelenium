package noahseleniumprac;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Practice2 {

    public static ChromeDriver driver;
    public static void openBrowser(String url){

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(url);
        //"https://www.amazon.com"
    }

    public static void waitFor(int seconds) {
      try{
        Thread.sleep(1000 * seconds);
    }catch(InterruptedException  ignored){}}

    public static void closeBrowser(){
        driver.quit();
    }

    public static void searchById(String id, String key){
        driver.findElement(By.id(id)).sendKeys(key);
    }

    public static void clickById(String id2){
        driver.findElement(By.id(id2)).click();
    }

    public static void main(String[] args){

        openBrowser("https://www.amazon.com");
        searchById("twotabsearchtextbox","java books");
        clickById("nav-search-submit-button");
        waitFor(5);
        closeBrowser();

    }
}
