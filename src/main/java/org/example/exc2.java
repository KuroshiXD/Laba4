package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class exc2 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");

        // Настройки драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Максимизируем окно браузера

        // Создаем экземпляр WebDriver
        WebDriver driver = new ChromeDriver(options);

        // Переходим на главную страницу Wikipedia
        driver.get("https://en.wikipedia.org/wiki/Main_Page");

        // Закрываем браузер
        //driver.quit();
    }
}
