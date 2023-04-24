package noahseleniumprac;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Practice1 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://www.amazon.com");

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("java books");
        driver.findElement(By.id("nav-search-submit-button")).click();


        Thread.sleep(5000);
        driver.quit();
    }

}
