import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.List;

public class RandomPageTestWithPageObject {
    private ChromeDriver driver;
    private WikipediaPageObject wikipediaPage;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        wikipediaPage = new WikipediaPageObject(driver);
    }

    @Test
    @Parameters({"amountOfPages"})
    public void testRandomPages(int amountOfPages) {
        wikipediaPage.goToMainPage();

        for (int i = 0; i < amountOfPages; i++) {
            wikipediaPage.clickRandomArticleLink();
            String pageUrl = wikipediaPage.getCurrentUrl();
            String pageTitle = wikipediaPage.getPageTitle();
            List<WebElement> referenceItems = wikipediaPage.getReferenceItems();

            System.out.println("\n");
            System.out.println("URL: " + pageUrl);
            System.out.println("Заголовок: " + pageTitle);
            System.out.println("Количество источников: " + referenceItems.size());
            System.out.println("Источники: ");

            int sourceCount = 1;
            for (WebElement item : referenceItems) {
                String sourceText = item.getText();
                List<WebElement> links = item.findElements(By.xpath(".//a[contains(@href, 'http')]"));

                StringBuilder sourceOutput = new StringBuilder();
                sourceOutput.append(sourceCount).append(". ").append(sourceText);

                for (WebElement link : links) {
                    sourceOutput.append(" [").append(link.getText()).append("](").append(link.getAttribute("href")).append(")");
                }

                System.out.println(sourceOutput.toString());
                sourceCount++;
            }

            wikipediaPage.navigateBack();
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
