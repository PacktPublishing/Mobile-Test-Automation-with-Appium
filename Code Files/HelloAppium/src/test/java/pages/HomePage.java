package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by nishant on 02/05/17.
 */
public class HomePage extends BasePage {
    AppiumDriver appiumDriver;

    @FindBy(id = "citySpinner")
    private WebElement cityDropdown;

    @FindBy(id = "search_ET")
    private WebElement citySearchBox;

    @FindBy(id = "city_name")
    private WebElement cityName;

    @FindBy(xpath = "//android.widget.TextView[@text='Cars']")
    private WebElement mobileOrEmailField;

    @FindBy(id = "sign_in_button")
    private WebElement googleButton;

    public HomePage(AppiumDriver appiumDriver) throws Exception {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
    }

    public void selectCity(String city) {
        cityDropdown.click();
        citySearchBox.click();
        citySearchBox.sendKeys(city);
        waitForElementToBeVisible(cityName);
        cityName.click();
    }
}
