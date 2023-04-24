package noahseleniumprac;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestCases extends Base {
    @Test(priority = 0)

    public void validateUserCanSearch() {


        searchById("gh-ac", "java books");
        clickById("gh-btn");
        waitFor(5);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


    }

    @Test(priority = 1)

    public void validateUserCanSignIn() {

        clickLinkText("Sign in");
        //searchById("userid","javabooks@gmail.com");
        waitFor(5);

    }

    @Test(enabled = false)
    public void validateUserCanPurchase() {
    }

    @Test
    public void printDetails() {

        WebElement searchButton = driver.findElement(By.id("gh-btn"));
        System.out.println(searchButton.getText());
        System.out.println(searchButton.getAttribute("value"));
        WebElement searchBar = driver.findElement(By.id("gh-ac"));
        System.out.println(searchBar.getText());
        System.out.println(searchBar.getAttribute("aria-label"));
    }

    @Test
    public void dropdown() {
        WebElement element = driver.findElement(By.id("gh-cat"));
        Select select = new Select(element);
        //perform actions of a dropdown
        //select.selectByIndex(3);
        //select.selectByValue("14339");
        select.selectByVisibleText("Crafts");
    }
    //WebElement element = driver.findElement(By.id("")):
    //Select select = new Select(element);
    //SelectByVisibleText("");//slect.SelectByValueByIndexByText("");

    @Test
    public void dropdownElements() {
        //tag[@text='']
        //select[@id='gh-cat']
        List<WebElement> elements = driver.findElements(By.xpath("//select[@id='gh-cat']/option"));
//        List<WebElement> elements = driver.findElements(By.id("gh-cat"));
        System.out.println(elements.size());

        for (WebElement e : elements) {
            e.click();
            System.out.println(e.getText());
        }

        //List<WebElement> elements = driver.FindElements(By.xpath"tagname[@type='']""tagname[@type='']"
        //for(WebElement e : elements){
        //e.click();
        //sout.(e.getText());
    }

    @Test
    public void assertEx() {
        WebElement searchButton = driver.findElement(By.id("gh-btn"));
        System.out.println(searchButton.isDisplayed());
        Assert.assertTrue(searchButton.isDisplayed(), "search button was not displayed");


        String expectedURL = "https://www.ebay.com/deals";
        clickLinkText("Daily Deals");
        String actualURL = driver.getCurrentUrl();
        System.out.println(actualURL);

        Assert.assertEquals(actualURL, expectedURL, "url didn't match");
//Assert,assertEquals(x,x,message);
        //Assert.assertEquals(x,x,message);
        //Assert.assertTrue(xxx.isDisplayed().message);

        WebElement spotLightDeal = driver.findElement(By.xpath(" //span[text()='Spotlight Deal']"));
        Assert.assertTrue(spotLightDeal.isDisplayed(), "spotlight deal wasn't displayed");
//assert to see if test case failed.

    }

    @Test
    public void scrollToView() {

        WebElement news = driver.findElement(By.linkText("News"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", news);
    }
    // "arguments[0].scrollIntoView(true)","arguments[0].scrollIntoView(true)

    @Test
    public void mouseHoverTest() {

        WebElement electronics = driver.findElement(By.linkText("Electronics"));

        Actions actions = new Actions(driver);

        actions.moveToElement(electronics).perform();

        waitFor(5);

        clickLinkText("Video Games");
        waitFor(5);

    }

    @Test
    public void handleMultipleTabs() {

        driver.findElement(By.linkText("Daily Deals")).sendKeys(Keys.CONTROL, Keys.ENTER);
        openInNewTabByLinkText("Brand Outlet");
        driver.getWindowHandles();

        Set<String> windows = driver.getWindowHandles();
        HashMap<String, String> tabInfo = new HashMap<>();
        HashMap<String, String> zanWay = new HashMap<>();
        System.out.println(windows);
        for (String tabs : windows) {
            driver.switchTo().window(tabs);
            String tabTitles = driver.getTitle();
            System.out.println(tabTitles);
            tabInfo.put(driver.getTitle(), driver.getWindowHandle());
            zanWay.put(tabTitles, tabs);

        }
        System.out.println(zanWay);
        System.out.println(tabInfo);
        //class 16 1:11:50 just testing it out
        String windowInfo = tabInfo.get("Daily Deals on eBay | Best deals and Free Shipping");
        //driver.switchTo().window(tabInfo.get("Daily Deals on eBay | Best deals and Free Shipping"));
        switchToTabByTitle(windowInfo);
        //driver.switchTo().window("https://www.ebay.com/b/Brand-Outlet/bn_7115532402");
        //driver.switchTo().window("Daily Deals on eBay | Best deals and Free Shipping");
        //driver.switchTo().window"CDwindow-A474C0A39F2EB08F09F4441B94EB3B3C);
        //String test = "CDwindow-A474C0A39F2EB08F09F4441B94EB3B3C";
        //driver.switchTo().window(test);
        //driver.getWindowHandle();
        //driver.switchTo().window("");

        //String a = driver.getWindowHandle();
       // System.out.println(a);
        //driver.switchTo().window(a);
    }
        //HashMap<String, String> tabInfo = new HashMap<>();
        //        tabInfo.put(driver.getWindowHandle()); cant but u can use driver.getTitle()
       //        System.out.println();

        //     String expectedTitle = "Deals";
        // for (String tabs : windows) {
        //     driver.switchTo().window(tabs);
        //     String actualTitle = driver.getTitle();
        //     System.out.println(titles);
        //if (actualTitle.contains(expectedTitle)) {
        //System.out.println("switched to " + expectedTitle);

        //Assert.assertEquals(actualTitle, expectedTitle, "did not switch");






}










