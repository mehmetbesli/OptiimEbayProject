import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * created by MehmetBesli063 on 05.2019
 */
public class EbayProject {

    private Logger logger = Logger.getLogger("EbayProject");

    private WebDriver driver;
    private String errorMessage = "Top level category browsing is not allowed. Please provide keywords or more filters for the applied top level category.";
    private String productPrice;
    private String clickedProductOrNot;

    public void  ebayTestCase(){
        openChromeBrowser();
        navigateToWebsite();
        selectCategoryClickSearch();
        checkForError();
        searchProductName();
        clickFirstProduct();
        collectPriceOfBook();
        addProductToWatchlist();
        loginWithUsernameAndPassword();
        closeBrowser();
    }

    //closing browser
    public void closeBrowser() {
        try {
            driver.close();
            logger.info("Browser closed");
        } catch (Exception e) {
            logger.info("Exception occured");
        }
    }

    //writing 'username' and 'password' then click
    public void loginWithUsernameAndPassword() {
        driver.findElement(By.id("userid")).sendKeys("mehmetbesli063@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("mhmtbsleba63.");
        driver.findElement(By.id("sgnBt")).click();
    }

    //adding product to 'watch list'
    public void addProductToWatchlist() {
        driver.findElement(By.id("watchLink")).click();
    }

    //getting price of selected product
    public void collectPriceOfBook() {

        productPrice = driver.findElement(By.xpath("//div[@id='vi-mskumap-none']/span")).getText();
        logger.info(productPrice);
    }

    //click first result
    public void clickFirstProduct() {
        driver.findElement(By.xpath("//*[@id='srp-river-results-listing1']//a[@class='s-item__link']")).click();
        clickedProductOrNot=driver.findElement(By.xpath("//a[@href='https://www.ebay.com/b/Fiction-Literature-/171228']")).getText();
        logger.info(clickedProductOrNot);
    }

    //searching 'The Lord of the Rings' and clicked search button
    public void searchProductName() {
        driver.findElement(By.xpath("//input[@class='gh-tb ui-autocomplete-input']")).sendKeys("The Lord of the Rings");
        driver.findElement(By.xpath("//input[@class='btn btn-prim gh-spr']")).click();
    }

    //getting error message
    public String checkForError() {
        String messageText = driver.findElement(By.xpath("//p[@class='page-notice__cell']")).getText();
        logger.info(messageText);
        Assert.assertEquals(messageText, errorMessage, "Message are not same");
        return messageText;
    }

    //choose category and click search button
    public void selectCategoryClickSearch() {

        driver.findElement(By.id("gh-cat-box")).click();
        
        Select dropdown = new Select(driver.findElement(By.id("gh-cat")));
        dropdown.selectByValue("267");

        driver.findElement(By.xpath("//td[@class='gh-td gh-sch-btn']")).click();

    }

    // go to Ebay website
    public void navigateToWebsite() {
        driver.get("https://www.ebay.com/");
        if (driver.getCurrentUrl().contains("https://www.ebay.com/")) {
            logger.info("Ebay page opened");
        } else {
            logger.info("Ebay did not open");
        }
    }

    //Opening chrome browser
    public void openChromeBrowser() {
        String path = System.getProperty("user.dir");
        System.out.println("Proje path : " + path);
        System.setProperty("webdriver.chrome.driver", path + "\\lib\\chromedriver.exe");

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getClickedProductOrNot() {
        return clickedProductOrNot;
    }

    public void setClickedProductOrNot(String clickedProductOrNot) {
        this.clickedProductOrNot = clickedProductOrNot;
    }

}