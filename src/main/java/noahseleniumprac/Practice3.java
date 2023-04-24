package noahseleniumprac;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Practice3 {

    public ChromeDriver driver;
    public void openBrowser(String url){

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(url);
        //"https://www.amazon.com"
    }

    public void waitFor(int seconds) {
      try{
        Thread.sleep(1000 * seconds);
    }catch(InterruptedException  ignored){}}

    public void closeBrowser(){
        driver.quit();
    }

    public  void searchById(String id, String key){
        driver.findElement(By.id(id)).sendKeys(key);
    }

    public void clickById(String id2){
        driver.findElement(By.id(id2)).click();
    }

    @Test

    public void validateUserCanSearch(){

        openBrowser("https://www.amazon.com");
        searchById("twotabsearchtextbox","java books");
        clickById("nav-search-submit-button");
        waitFor(5);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        closeBrowser();

    }
    @Test

    public void validateUserCanSignIn(){
        openBrowser("https://www.amazon.com");
        clickById("nav-link-accountList-nav-line-1");
        searchById("ap_email","javabooks@gmail.com");
        waitFor(5);
        closeBrowser();
    }
    @Test
    public void targetLocator() {
        openBrowser("https://www.target.com");

        // id
        //driver.findElement(By.id("")).click();
        // linkText
        //driver.findElement(By.linkText("Deals")).click();
        //partialLinkText
        //driver.findElement(By.partialLinkText("Groceries")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //xpathtag[@type = '']"//xpathtag[@tyep = '']"
        //using text
        //span[text()='Sign in'] //tag[text() = 'Sign in']//tag[text()='']//tagname[text()='']
        //using text contains
        //span[contains(tag(),'Sign')]//tag[contains(tag(),'Sign')]
        // used //*[text()='']
        waitFor(5);
        closeBrowser();
    }

    @Test
    public void dragAndDrop(){
        openBrowser("https://demo.guru99.com/test/drag_drop.html");
        WebElement from = driver.findElement(By.xpath("//*[test()=' 5000']"));
        WebElement to = driver.findElement(By.id("amt8"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(from,to).perform();

        waitFor(5);
//Actions action = new Actions(driver)
        //WebElement from = driver.findElement(By.xpath("//*[test()='']"));
        //WebElement to = driver.findEement(By.id(""));
        //Actions actions = new Acrions(driver);
        //actions.dragAndDrop(from,to).perform();
    }
    @Test
    public void handleIFrame() {
        openBrowser("https://demoqa.com/frames");

        // WebElement x = driver.findElement(By.id("frame2"));

        driver.switchTo().frame("frame2");
        //driver.switchTo().frame()

        // do whatever you want inside
        WebElement element = driver.findElement(By.id("sampleHeading"));
        System.out.println(element.getText());

        // switch back to default
        driver.switchTo().defaultContent();
        closeBrowser();
//driver.switchTo().defaultContent();
    }

    @Test
    public void handleWebBasedPopup(){
        openBrowser("https://demo.guru99.com/test/delete_customer.php");

        driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys("test");
        driver.findElement(By.xpath("//input[@name='submit']")).click();

        Alert alert = driver.switchTo().alert();
       // Alert alert = driver.switchTo().alert();9
        System.out.println(alert.getText());
        //alert.accept();
        handAlerts(AlertOptions.GETTEXT);

        Alert alert2 = driver.switchTo().alert();
        System.out.println(alert.getText());
        //alert.accept();

    }
    private  enum AlertOptions {

        Accept, DISMISS, GETTEXT
    }

    //private enum AlertOptions(){
    //Accept,Dismiss,getText

    public String handAlerts(AlertOptions alertoptions){
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
//public String handleAlerts(AlertOptions options){
// switch(options){
// case Accept:
// alert.accept();
// break;
// case Dismiss:
// alert.dismiss();
// break;
//}return text;
        switch (alertoptions) {
            case Accept:
                alert.accept();
                break;
            case DISMISS:
                alert.dismiss();
                break;
            case GETTEXT:
                break;
                //technically we don't need this if we set a return at the end
        }
        return text;
        }
    @Test
    public void handleUpload() {
        openBrowser("https://demo.guru99.com/test/upload/");
        WebElement uploadButton = driver.findElement(By.id("uploadfile_0"));
        //webElement uploadButton = driver.FindElement(By.id("x"));
        //String filePath = System.getProperty("user.dir") + "content root";uploadButton.sendKeys(filePath);


        String filePath = System.getProperty("user.dir") + "/src/main/resources/file.png";

        System.out.println(filePath);
        uploadButton.sendKeys(filePath);

        // driver.findElement(By.id("uploadfile_0")).sendKeys("sfev");

        WebElement termsXpath = driver.findElement(By.id("terms"));

        System.out.println(termsXpath.isSelected());

        termsXpath.click();
        System.out.println(termsXpath.isSelected());
        closeBrowser();
    }
    public WebElement getElementByXpath(String locator) {
        return driver.findElement(By.xpath(locator));
    }
    @Test
    public void handleRadioButton() {
        openBrowser("https://www.aa.com");

        WebElement roundTrip = getElementByXpath("//input[@type='radio' and @id ='flightSearchForm.tripType.roundTrip']");
        WebElement oneWay = getElementByXpath("//input[@type='radio' and @id ='flightSearchForm.tripType.oneWay']");

        WebElement cookie = getElementByXpath("//*[@id='cookieBannerClose']");

        if (cookie.isDisplayed()) {
            cookie.click();
            System.out.println("cookie handled");
        }

        System.out.println("round trip is : " + roundTrip.isEnabled());
        System.out.println("oneWay trip is : " + oneWay.isEnabled());

        System.out.println("after selecting one way");
        oneWay.click();
        waitFor(5);

        System.out.println("round trip is : " + roundTrip.isEnabled());
        System.out.println("oneWay trip is : " + oneWay.isEnabled());

        closeBrowser();

    }
    }


