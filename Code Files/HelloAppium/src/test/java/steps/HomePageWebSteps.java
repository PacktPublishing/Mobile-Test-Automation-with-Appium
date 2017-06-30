package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class HomePageWebSteps extends BaseSteps {

    @When("^I launch Quikr mobile web$")
    public void iLaunchQuikrMobileWeb() throws Throwable {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Nexus");
        desiredCapabilities.setCapability("browserName", "Browser");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        appiumDriver = new AppiumDriver(url, desiredCapabilities);
        appiumDriver.get("http://m.quikr.com");


    }

    @And("^I choose to register$")
    public void iChooseToRegister() throws Throwable {
        appiumDriver.findElement(By.id("hamburger")).click();

        Thread.sleep(5 * 1000);
        appiumDriver.findElement(By.id("hamLoginLink")).click();

    }

    @Then("^I should see an option to register using Facebook$")
    public void iShouldSeeAnOptionToRegisterUsingFacebook() throws Throwable {
        Thread.sleep(5 * 1000);
        appiumDriver.findElement(By.partialLinkText("Register")).click();

        Thread.sleep(2 * 1000);
        Assert.assertTrue(appiumDriver.findElement(By.className("icon-facebook")).isDisplayed());

    }
}
