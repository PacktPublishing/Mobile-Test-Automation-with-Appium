package pages;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage {
    AppiumDriver appiumDriver;

    @FindBy(id = "skip")
    private WebElement skipLink;

    @FindBy(id = "login_register_view")
    private WebElement mobileOrEmailField;

    @FindBy(id = "continue_login")
    private WebElement continueButton;

    @FindBy(id = "fb")
    private WebElement fbButton;

    @FindBy(id = "sign_in_button")
    private WebElement googleButton;

    public LandingPage(AppiumDriver driver) throws Exception {
        super(driver);
        this.appiumDriver = driver;
        PageFactory.initElements(appiumDriver, this);

    }

    public void skipToHomePage() {
        waitForElementToBeVisible(skipLink);
        skipLink.click();
    }

    public void registerByMobileOrEmail(String mobileorEmail) {
        mobileOrEmailField.sendKeys(mobileorEmail);
        continueButton.click();
    }

    public void signInByFacebook() {
        waitForElementToBeVisible(fbButton);
        fbButton.click();
    }

    public void signInByGoogle() {
        waitForElementToBeVisible(googleButton);
        googleButton.click();
    }

    public void loginIsDisplayed() {
        Assert.assertTrue(mobileOrEmailField.isDisplayed());
    }
}
