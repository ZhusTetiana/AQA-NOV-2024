package org.prog.selenium.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
public class AlloUaPage {

    private final WebDriver driver;

    public AlloUaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loadPageAndAcceptCookiesIfPresent() {
        loadPage();
        if (isCookiePresent()) {
            acceptCookies();
        }
    }
    public boolean isCookiePresent() {
        List<WebElement> cookiesLink =
                driver.findElements(By.xpath("//a[contains(@href, 'technologies/cookies')]"));
        if (!cookiesLink.isEmpty()) {
            return cookiesLink.get(0).isDisplayed();
        }
        return false;
    }

    public List<WebElement> searchResult() {
        return new WebDriverWait(driver, Duration.ofSeconds(60))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("product-sku__value")));
    }

    public void acceptCookies() {
        driver.findElements(By.tagName("button")).get(4).click();
    }

    public void loadPage() {
        driver.get("https://allo.ua/");
    }


    public void setSearchInputText(String value) {
        driver.findElement(By.id("search-form__input")).sendKeys(value);
    }

    public void executeSearch() {
        driver.findElement(By.id("search-form__input")).sendKeys(Keys.ENTER);
    }



    public List<WebElement> getSearchHeaders() {
        return new WebDriverWait(driver, Duration.ofSeconds(5L))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("h3"), 1));
    }
}
