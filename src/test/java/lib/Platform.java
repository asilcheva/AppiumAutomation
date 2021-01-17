package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {
    private static final String  PLATFORM_ANDROID = "android";
    private static final String  PLATFORM_IOS = "ios";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
    public boolean isAndroid() {
        return this.isPlatform(PLATFORM_ANDROID);
    }
    public boolean isIOS() {
        return this.isPlatform(PLATFORM_IOS);
    }
    public AppiumDriver getDriver() throws Exception{
        URL url = new URL(APPIUM_URL);
        if (this.isAndroid()){
            return new AndroidDriver(url, this.getAndroidDesiredCapabilities());
        }
        else if (this.isIOS()){
            return new IOSDriver(url, this.getIOSDesiredCapabilities());
        }
        else throw new Exception("Can't detect type of driver. Platform value "+this.getPlatform());
    }
    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "TestDevice");
        desiredCapabilities.setCapability("platformVersion", "11.0");
        desiredCapabilities.setCapability("automationName", "Appium");
        desiredCapabilities.setCapability("appPackage", "org.wikipedia");
        desiredCapabilities.setCapability("appActivity", ".main.MainActivity");
        desiredCapabilities.setCapability("app", "C:\\Anutka\\ProjectsAutotest\\javaAppiumAutomation\\wikipedia.apk");
        return desiredCapabilities;
    }
    private DesiredCapabilities getIOSDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("deviceName", "iPhone 11 Pro Max");
        desiredCapabilities.setCapability("platformVersion", "14.3");
        desiredCapabilities.setCapability("app", "/Users/Desktop/anna/Wikipedia.app");
        return desiredCapabilities;
    }
    private String getPlatform() {
        return System.getenv("PLATFORM");
    }
    private boolean isPlatform(String myPlatform){
        String platform = this.getPlatform();
        return platform.equals(myPlatform);

    }
}
