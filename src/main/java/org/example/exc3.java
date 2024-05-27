package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class exc3 {
    public static void main(String[] args) {
        // Указываем путь к драйверу
        System.setProperty("webdriver.chrome.driver", "C:chromedriver/chromedriver.exe");

        // Настройки драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Максимизируем окно браузера

        // Создаем экземпляр WebDriver
        WebDriver driver = new ChromeDriver(options);

        // Переходим на главную страницу Wikipedia
        driver.get("https://en.wikipedia.org/wiki/Main_Page");

        // Поиск элемента по CSS-селектору
        WebElement navElement = driver.findElement(By.cssSelector("#vector-main-menu-dropdown-checkbox"));
        navElement.click();

        // Находим и кликаем по кнопке "Current events"
        WebElement currentEventsLinkInnavElem = driver.findElement(By.linkText("Current events"));
        currentEventsLinkInnavElem.click();

    }
}


