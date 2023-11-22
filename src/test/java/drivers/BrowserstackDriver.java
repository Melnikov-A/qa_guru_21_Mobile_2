package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.System.getProperties;

public class BrowserstackDriver implements WebDriverProvider {
    protected static BrowserstackConfig browserstackConfig = ConfigFactory.create(BrowserstackConfig.class, getProperties());

    @Nonnull
    @Override

    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        mutableCapabilities.setCapability("browserstack.user",browserstackConfig.getUsername());
        mutableCapabilities.setCapability("browserstack.key", browserstackConfig.getPassword());

        mutableCapabilities.setCapability("app", browserstackConfig.getApp());
        mutableCapabilities.setCapability("deviceName", browserstackConfig.getDevice());
        mutableCapabilities.setCapability("osVersion", browserstackConfig.getVersion());

        mutableCapabilities.setCapability("projectName", browserstackConfig.getProject());
        mutableCapabilities.setCapability("buildName", browserstackConfig.getBuild());
        mutableCapabilities.setCapability("testName", browserstackConfig.getName());

        try {
            return new RemoteWebDriver(
                    new URL(browserstackConfig.getRemoteUrl()), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}

