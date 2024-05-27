import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class WikipediaPageObject {
    private WebDriver driver;
    private WebDriverWait wait;

    public WikipediaPageObject(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public void goToMainPage() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
    }

    public void clickRandomArticleLink() {
        WebElement menuCheckbox = driver.findElement(By.cssSelector("#vector-main-menu-dropdown-checkbox"));
        menuCheckbox.click();
        WebElement randomLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Random article")));
        randomLink.click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public List<WebElement> getReferenceItems() {
        return driver.findElements(By.xpath("//ol[@class='references']/li"));
    }

    public void navigateBack() {
        driver.navigate().back();
    }
}
