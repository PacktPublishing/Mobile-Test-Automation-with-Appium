package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class iOSPageSteps extends BaseSteps {

    @When("^I launch iOS app$")
    public void iLaunchIOSApp() throws Throwable {
        /*
            Moved the Desired Capability code to Starting steps to have that at one place
            This method can be changed to do some assertion as shown below
        */
        Assert.assertTrue(appiumDriver.findElementByAccessibilityId("TextField1").isDisplayed());
    }

    @And("^I choose to enter \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iChooseToEnterAnd(String num1, String num2) throws Throwable {
        appiumDriver.findElementByAccessibilityId("TextField1").sendKeys(num1);
        appiumDriver.findElementByAccessibilityId("TextField2").sendKeys(num2);
    }

    @When("^I tap on Compute Sum$")
    public void iTapOnComputeSum() throws Throwable {
        appiumDriver.findElementByAccessibilityId("ComputeSumButton").click();
    }

    @Then("^I should see the result \"([^\"]*)\"$")
    public void iShouldSeeTheResult(String result) throws Throwable {
        Assert.assertEquals(result, appiumDriver.findElementByAccessibilityId("Answer").getText());
    }

}
