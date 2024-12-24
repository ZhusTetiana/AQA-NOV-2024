package org.prog.testng;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.prog.selenium.pages.AlloUaPage;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;

//TODO: Move Allo.ua code to TestNG
//TODO: replace [// if X > goods count -> throw exception] with Assert
public class AlloUaTestNG {

    private WebDriver driver;
    private AlloUaPage alloPage;
    @BeforeSuite
    public void setUp() {
        driver = new ChromeDriver();
        alloPage = new AlloUaPage(driver);

    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(dataProvider = "phoneNames")
    public void testGoogleSearchClassic(String phoneNames) {
        alloPage.loadPageAndAcceptCookiesIfPresent();
        alloPage.setSearchInputText(phoneNames);
        alloPage.executeSearch();
        List<WebElement> searchResults = alloPage.searchResult();
        WebElement firstElement = searchResults.get(0);

        String codeOfItemOfFirstElement = firstElement.getText();

        try {
            int value = alloPage.searchResult().indexOf(searchResults.get(0)) + 1;
            if (value < 0) {
                throw new RuntimeException("Less than 0!");
            }

            Assert.assertFalse(value > searchResults.size(),
                        "First item more than entire list");

            if (0 <= value && value <= searchResults.size()) {

                System.out.println("Код первой позиции: "+codeOfItemOfFirstElement);
            }
        } catch (NumberFormatException e) {
            System.out.println("Не удалось преобразовать текст в число");
        }
    }

    @DataProvider
    public Object[][] phoneNames() {
        return new Object[][]{
                {"iphone"},

        };
    }

}
