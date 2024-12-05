package org.prog.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumDemo {

    public static void main(String[] args) {
        WebDriver driver = null;
        try {

            driver = new ChromeDriver();
            driver.get("https://allo.ua/");

            WebElement searchInput = driver.findElement(By.name("search"));
            searchInput.sendKeys("Apple iPhone 16 Pro 128GB Desert Titanium (MYNF3)");
            searchInput.sendKeys(Keys.ENTER);

            List<WebElement> searchHeaders = new WebDriverWait(driver, Duration.ofSeconds(5L))
                    .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("h3"), 1));

            int count = 0;
            for (WebElement webElement : searchHeaders) {
                if (webElement.getText().contains("Apple iPhone 16 Pro")) {
                    count++;
                }
            }

            if (count >= 0) {
                System.out.println("Apple iPhone 16 Found!");
            } else {
                System.out.println("Apple iPhone 16 not found!");
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
