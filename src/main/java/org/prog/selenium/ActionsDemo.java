package org.prog.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ActionsDemo {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://allo.ua/");
            WebElement searchInput = driver.findElement(By.id("search-form__input"));
            searchInput.sendKeys("iphone");
            searchInput.sendKeys(Keys.ENTER);

            List<WebElement> searchResults =
                    new WebDriverWait(driver, Duration.ofSeconds(60))
                            .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("product-sku__value"), 10));

            Actions actions = new Actions(driver);
            WebElement firstElement = searchResults.get(0);

            String codeOfItemOfFirstElement = firstElement.getText();

            try {

                int value = searchResults.indexOf(searchResults.get(0)) + 1;

                if (value < 0) {
                    throw new RuntimeException("Less than 0!");
                }

                if (value > searchResults.size()) {
                    throw new RuntimeException("First item more than entire list");
                }

                if (0 <= value && value <= searchResults.size()) {
                    actions.moveToElement(firstElement).perform();
                    System.out.println("Код первой позиции: "+codeOfItemOfFirstElement);
                }

            } catch (NumberFormatException e) {
                System.out.println("Не удалось преобразовать текст в число");
            }

            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("alert('tests!!')");


            System.out.println("look!");
        } finally {
            driver.quit();
        }
    }
}
