import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * created by MehmetBesli063 on 05.2019
 */
public class EbayProjectTestCase {

    EbayProject project;

    @BeforeTest
    private void openChromeBrowser(){
        project=new EbayProject();
        project.openChromeBrowser();
    }

    @AfterTest
    private void closeBrowser(){
        project.closeBrowser();
    }

    @Test
    private void navigateToWebSite(){
        project.navigateToWebsite();
        Assert.assertTrue(project.getDriver().getCurrentUrl().contains("https://www.ebay.com/"),"You are in ebay website" );
    }

    @Test
    private void selectCategoryClickSearch(){
        project.navigateToWebsite();
        project.selectCategoryClickSearch();
        Assert.assertTrue(project.getDriver().getCurrentUrl().contains("267"),"chose book category and clicked search button" );
    }

    @Test
    private void checkForError(){
        project.navigateToWebsite();
        project.selectCategoryClickSearch();
        String mesaj = project.checkForError();
        Assert.assertTrue(mesaj.contains("Top level category browsing is not allowed"),"You got error message" );
    }

    @Test
    private void searchProductName(){
        project.navigateToWebsite();
        project.selectCategoryClickSearch();
        project.checkForError();
        project.searchProductName();
        Assert.assertTrue(project.getDriver().getCurrentUrl().contains("The+Lord+of+the+Rings"), "Searched The Lord of the Rings");
    }

    @Test
    private void clickFirstProduct(){
        project.navigateToWebsite();
        project.selectCategoryClickSearch();
        project.checkForError();
        project.searchProductName();
        project.clickFirstProduct();
        Assert.assertTrue(project.getClickedProductOrNot().contains("Fiction"),"Clicked first product" );
    }

    @Test
    private void collectPriceOfBook(){
        project.navigateToWebsite();
        project.selectCategoryClickSearch();
        project.checkForError();
        project.searchProductName();
        project.clickFirstProduct();
        project.collectPriceOfBook();
        Assert.assertTrue(project.getClickedProductOrNot().contains("Literature"), "Correct price is chosen");
    }

    @Test
    private void addProductToWatchlist(){
        project.navigateToWebsite();
        project.selectCategoryClickSearch();
        project.checkForError();
        project.searchProductName();
        project.clickFirstProduct();
        project.collectPriceOfBook();
        project.addProductToWatchlist();
        Assert.assertTrue(project.getDriver().getCurrentUrl().contains("SignIn"),"You did not add product you watch list" );
    }

    @Test
    private void loginWithUsernameAndPassword(){
        project.navigateToWebsite();
        project.selectCategoryClickSearch();
        project.checkForError();
        project.searchProductName();
        project.clickFirstProduct();
        project.collectPriceOfBook();
        project.addProductToWatchlist();
        project.loginWithUsernameAndPassword();
        Assert.assertTrue(project.getDriver().getCurrentUrl().contains("PageName"), "You loged with username and password");
    }
}