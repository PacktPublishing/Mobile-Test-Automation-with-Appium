package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by nishant on 19/06/17.
 */
public class CarResultsPage extends BasePage {
    AppiumDriver appiumDriver;

    @FindBy(id = "category")
    private WebElement categoryChooser;

    @FindBy(id = "inspected_checkbox")
    private WebElement inspectedToggle;

    @FindBy(xpath = "//android.widget.TextView[@text='SORT']")
    private WebElement sortLink;

    @FindBy(xpath = "//android.widget.TextView[@text='FILTER']")
    private WebElement filterLink;

    @FindBy(id = "cars_ad_list_title_tv")
    private List<WebElement> searchResultText;

    public CarResultsPage(AppiumDriver appiumDriver) throws Exception {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
    }

    public String getFirstSearchResult() {
        return searchResultText.get(0).getText();
    }

}