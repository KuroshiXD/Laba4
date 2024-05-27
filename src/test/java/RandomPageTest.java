import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.time.Duration;

public class RandomPageTest {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        // Указываем путь к драйверу
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        // Создаем экземпляр WebDriver
        driver = new ChromeDriver(options);
    }

    @Test
    @Parameters({"amountOfPages"})
    public void testRandomPages(int amountOfPages) {
        // Переходим на главную страницу Wikipedia
        driver.get("https://en.wikipedia.org/wiki/Main_Page");

        // Переход на случайные страницы
        for (int i = 0; i < amountOfPages; i++) {
            WebElement webElement = driver.findElement(By.cssSelector("#vector-main-menu-dropdown-checkbox"));
            webElement.click();

            // Явное ожидание появления ссылки "Random article"
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
            WebElement randomLink =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Random article")));

            // Кликаем на случайную ссылку на главной странице
            randomLink.click();

            // Выводим URL, заголовок и список источников текущей страницы
            String pageUrl = driver.getCurrentUrl();
            String pageTitle = driver.getTitle();
            List<WebElement> referenceItems = driver.findElements(By.xpath("//ol[@class='references']/li"));

            System.out.println("\n");
            System.out.println("URL: " + pageUrl);
            System.out.println("Заголовок: " + pageTitle);
            System.out.println("Количество источников: " + referenceItems.size());
            System.out.println("Источники: ");

            int sourceCount = 1;
            for (WebElement item : referenceItems) {
                // Извлекаем текст источника
                String sourceText = item.getText();
                // Извлекаем ссылки внутри источника
                List<WebElement> links = item.findElements(By.xpath(".//a[contains(@href, 'http')]"));

                // Формируем строку с текстом источника и ссылками
                StringBuilder sourceOutput = new StringBuilder();
                sourceOutput.append(sourceCount).append(". ").append(sourceText);

                for (WebElement link : links) {
                    sourceOutput.append(" [").append(link.getText()).append("](").append(link.getAttribute("href")).append(")");
                }

                System.out.println(sourceOutput.toString());
                sourceCount++;
            }

            // Возвращаемся на главную страницу
            driver.navigate().back();
        }
    }

    @AfterTest
    public void tearDown() {
        // Закрываем браузер
        driver.quit();
    }
}
