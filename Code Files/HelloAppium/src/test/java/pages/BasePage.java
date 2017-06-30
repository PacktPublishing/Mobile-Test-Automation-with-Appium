package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public BasePage(AppiumDriver driver) throws Exception {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 30);
    }

    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}