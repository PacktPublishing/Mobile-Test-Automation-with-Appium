package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class StartingSteps extends BaseSteps {

    private AppiumDriverLocalService appiumService;

    @Before
    public void startAppiumServer() throws IOException {

        int port = 4723;
        String nodeJS_Path = "C:/Program Files/NodeJS/node.exe";
        String appiumJS_Path = "C:/Program Files/Appium/node_modules/appium/bin/appium.js";

        String osName = System.getProperty("os.name");

        if (osName.contains("Mac")) {
            appiumService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingDriverExecutable(new File(("/usr/local/bin/node")))
                    .withAppiumJS(new File(("/usr/local/bin/appium")))
                    .withIPAddress("0.0.0.0")
                    .usingPort(port)
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withLogFile(new File("build/appium.log")));
        } else if (osName.contains("Windows")) {
            appiumService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingDriverExecutable(new File(nodeJS_Path))
                    .withAppiumJS(new File(appiumJS_Path))
                    .withIPAddress("0.0.0.0")
                    .usingPort(port)
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withLogFile(new File("build/appium.log")));
        }
        appiumService.start();

        /* We have put a check based on the tag passed to determine whether to run Android or iOS
           This can be handled by having gradle decide which is the target device to be tested against.
          */

        DesiredCapabilities capabilities = new DesiredCapabilities();

        String target = System.getProperty("targetDevice");
        System.out.println("Input Target Device is: " + target);
        if (target.equalsIgnoreCase("android")) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("platformVersion", "5.1");
            capabilities.setCapability("deviceName", "Nexus6");
            capabilities.setCapability("noReset", false);
            capabilities.setCapability("fullReset", true);
            capabilities.setCapability("app", "app/quikr.apk");
        } else if (target.equalsIgnoreCase("ios")) {
            capabilities.setCapability("appium-version", "1.0");
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("platformVersion", "8.4");
            capabilities.setCapability("deviceName", "iPhone 6");
            capabilities.setCapability("app", "app/TestApp.app");
        } else if (target.equalsIgnoreCase("mweb")) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "Nexus");
            capabilities.setCapability("browserName", "Browser");
        }

        appiumDriver = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                final byte[] screenshot = appiumDriver
                        .getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            }
            appiumDriver.quit();
            appiumService.stop();
        } catch (Exception e) {
            System.out.println("Exception while running Tear down :" + e.getMessage());
        }
    }

    public static void changeDriverContextToWeb(AppiumDriver driver) {
        Set<String> allContext = driver.getContextHandles();
        for (String context : allContext) {
            if (context.contains("WEBVIEW"))
                driver.context(context);
        }
    }
}
