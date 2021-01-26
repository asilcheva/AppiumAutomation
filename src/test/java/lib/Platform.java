package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {
    private static final String  PLATFORM_ANDROID = "android";
    private static final String  PLATFORM_IOS = "ios";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
    public boolean isAndroid() {
        return this.isPlatform(PLATFORM_ANDROID);
    }
    public boolean isIOS() {
        return this.isPlatform(PLATFORM_IOS);
    }
    public boolean isMW() {
        return this.isPlatform(PLATFORM_MOBILE_WEB);
    }
    private static Platform instance;
    public static Platform getInstance(){
        if (instance == null) {
            instance= new Platform();
        }
        return instance;
    }
    public RemoteWebDriver getDriver() throws Exception{
        URL url = new URL(APPIUM_URL);
        if (this.isAndroid()){
            return new AndroidDriver(url, this.getAndroidDesiredCapabilities());
        }
        else if (this.isIOS()){
            return new IOSDriver(url, this.getIOSDesiredCapabilities());
        }
        else if (this.isMW()) {
            return new ChromeDriver(this.getMWChromeOptions());
        }
        else throw new Exception("Can't detect type of driver. Platform value "+this.getPlatform());
    }
    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "PixelXL");
        desiredCapabilities.setCapability("platformVersion", "11.0");
        desiredCapabilities.setCapability("appPackage", "org.wikipedia");
        desiredCapabilities.setCapability("appActivity", ".main.MainActivity");
        desiredCapabilities.setCapability("app", "/Users/dantilar/Desktop/org.wikipedia.apk");
        return desiredCapabilities;
    }
    private DesiredCapabilities getIOSDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("deviceName", "iPhone 11");
        desiredCapabilities.setCapability("platformVersion", "13.7");
        desiredCapabilities.setCapability("automationName", "XCUITest");
        desiredCapabilities.setCapability("app", "/Users/dantilar/Documents/Wikipedia.app");
        return desiredCapabilities;
    }
    private String getPlatform() {
        return System.getenv("PLATFORM");
    }
    private boolean isPlatform(String myPlatform){
        String platform = this.getPlatform();
        return platform.equals(myPlatform);
    }
    private ChromeOptions getMWChromeOptions(){
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 411);
        deviceMetrics.put("height", 823);
        deviceMetrics.put("pixelRatio", 3.0);
        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=340, 640");
        chromeOptions.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        chromeOptions.setCapability("chromedriverExecutable", "C:\\Anutka\\mine\\chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver", "C:\\Anutka\\mine\\chromedriver.exe");
        return chromeOptions;
    }
}
