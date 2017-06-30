package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.CarResultsPage;
import pages.HomePage;
import pages.LandingPage;

import java.util.List;


public class HomePageSteps extends BaseSteps {

    @When("^I launch Quikr app$")
    public void iLaunchQuikrApp() throws Throwable {
        new LandingPage(appiumDriver).loginIsDisplayed();
    }

    @And("^I choose to log in using Google$")
    public void iChooseToLogInUsingGoogle() throws Throwable {
        new LandingPage(appiumDriver).signInByGoogle();
    }

    @Then("^I see account picker screen with my email address \"([^\"]*)\"$")
    public void iSeeAccountPickerScreenWithMyEmailAddress(String expected) throws Throwable {
        /* TODO - Assignment - Move the below assertion to a page class and call waitForElementToBeVisible for that element */
        Thread.sleep(5000);
        Assert.assertEquals("Email Id matches", expected, appiumDriver.findElement(By.id("com.google.android.gms:id/account_name")).getText());
    }

    @And("^I choose \"([^\"]*)\" as my city$")
    public void iChooseAsMyCity(String city) throws Throwable {
        new LandingPage(appiumDriver).skipToHomePage();

        /* TODO - Assignment - Move the try catch logic to base page */

        try {
            if (appiumDriver.findElement(By.xpath("//android.widget.Button[@text='Later']")).isDisplayed())
                appiumDriver.findElement(By.xpath("//android.widget.Button[@text='Later']")).click();
        } catch (Exception e) {
            //do nothing
        }

        new HomePage(appiumDriver).selectCity(city);
    }


    @And("^I search for \"([^\"]*)\" under Used Cars$")
    public void iSearchForUnderUsedCars(String carName) throws Throwable {
        /* TODO - Assignment - Move the below statements to a page class */

        appiumDriver.findElement(By.xpath("//android.widget.TextView[@text='Cars']")).click();
        appiumDriver.findElement(By.id("cnb_hp_choose_et")).click();
        appiumDriver.findElement(By.id("cnb_search_text_et")).sendKeys(carName);

        List<WebElement> results = appiumDriver.findElements(By.id("text1"));
        for (WebElement result : results) {
            if (result.getText().contains(carName)) {
                result.click();
                break;
            }
        }
        appiumDriver.findElement(By.id("cnb_search_button")).click();
    }

    @Then("^I should see the first car search result with \"([^\"]*)\"$")
    public void iShouldSeeTheFirstCarSearchResultWith(String searchInput) throws Throwable {
        String searchResult = new CarResultsPage(appiumDriver).getFirstSearchResult();
        Assert.assertTrue(searchResult.startsWith(searchInput));
    }

}
