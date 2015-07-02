import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class NoLoginTest implements SauceOnDemandSessionIdProvider
 {

    
    private WebDriver driver;
    private String sessionId = "";
    private DesiredCapabilities caps;
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication();
    public @Rule
    SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);
    public @Rule TestName testName = new TestName();

    @Parameters
    public static Collection<Object[]> data() {
        System.out.println("set @parameters");

        //Win 8.1
        DesiredCapabilities capsIE11Win81 = DesiredCapabilities.internetExplorer();
        capsIE11Win81.setCapability("platform", "Windows 8.1");
        capsIE11Win81.setCapability("version", "11.0");
        capsIE11Win81.setCapability("screenResolution", "1280x1024");

        DesiredCapabilities capsChromeWin81 = DesiredCapabilities.chrome();
        capsChromeWin81.setCapability("platform", "Windows 8.1");
        capsChromeWin81.setCapability("version", "43.0");
        capsChromeWin81.setCapability("screenResolution", "1280x1024");

        DesiredCapabilities capsFirefoxWin81 = DesiredCapabilities.firefox();
        capsFirefoxWin81.setCapability("platform", "Windows 8.1");
        capsFirefoxWin81.setCapability("version", "38.0");
        capsFirefoxWin81.setCapability("screen-resolution", "1280x1024");

        //Win 7

        DesiredCapabilities capsIE11Win7 = DesiredCapabilities.internetExplorer();
        capsIE11Win7.setCapability("platform", "Windows 7");
        capsIE11Win7.setCapability("version", "11.0");
        capsIE11Win7.setCapability("screen-resolution", "1280x1024");

        DesiredCapabilities capsChromeWin7 = DesiredCapabilities.chrome();
        capsChromeWin7.setCapability("platform", "Windows 7");
        capsChromeWin7.setCapability("version", "43.0");
        capsChromeWin7.setCapability("screen-resolution", "1280x1024");

        DesiredCapabilities capsFirefoxWin7 = DesiredCapabilities.firefox();
        capsFirefoxWin7.setCapability("platform", "Windows 7");
        capsFirefoxWin7.setCapability("version", "38.0");
        capsFirefoxWin7.setCapability("screen-resolution", "1280x1024");

        //Mac 10.10
        DesiredCapabilities capsChromeMac1010 = DesiredCapabilities.chrome();
        capsChromeMac1010.setCapability("platform", "OS X 10.10");
        capsChromeMac1010.setCapability("version", "43.0");
        capsChromeMac1010.setCapability("screenResolution", "1024x768");

        DesiredCapabilities capsSafariMac1010 = DesiredCapabilities.safari();
        capsSafariMac1010.setCapability("platform", "OS X 10.10");
        capsSafariMac1010.setCapability("version", "8.0");
        capsSafariMac1010.setCapability("screenResolution", "1024x768");

        DesiredCapabilities capsFirefoxMac1010 = DesiredCapabilities.firefox();
        capsFirefoxMac1010.setCapability("platform", "OS X 10.10");
        capsFirefoxMac1010.setCapability("version", "38.0");
        capsFirefoxMac1010.setCapability("screenResolution", "1024x768");

        //iPhone
        DesiredCapabilities capsIphone82 = DesiredCapabilities.iphone();
        capsIphone82.setCapability("platform", "OS X 10.10");
        capsIphone82.setCapability("version", "8.2");
        capsIphone82.setCapability("deviceName","iPhone Simulator");
        capsIphone82.setCapability("deviceOrientation", "portrait");

        DesiredCapabilities capsIphone81 = DesiredCapabilities.iphone();
        capsIphone81.setCapability("platform", "OS X 10.10");
        capsIphone81.setCapability("version", "8.1");
        capsIphone81.setCapability("deviceName","iPhone Simulator");
        capsIphone81.setCapability("deviceOrientation", "portrait");

        //iPad
        DesiredCapabilities capsiPad = DesiredCapabilities.iphone();
        capsiPad.setCapability("platform", "OS X 10.10");
        capsiPad.setCapability("version", "8.2");
        capsiPad.setCapability("deviceName","iPad Simulator");
        capsiPad.setCapability("deviceOrientation", "portrait");

        //Android
        DesiredCapabilities capsAndroid44 = DesiredCapabilities.android();
        capsAndroid44.setCapability("platform", "Linux");
        capsAndroid44.setCapability("version", "4.4");
        capsAndroid44.setCapability("deviceName","Google Nexus 7 HD Emulator");
        capsAndroid44.setCapability("deviceOrientation", "portrait");

        Object[][] caps = {
            {capsIE11Win81},
            {capsChromeWin81},
            {capsFirefoxWin81},
            {capsIE11Win7},
            {capsChromeWin7},
            {capsFirefoxWin7},
            {capsChromeMac1010},
            {capsSafariMac1010},
            {capsFirefoxMac1010},
            {capsIphone82},
            {capsIphone81},
            {capsiPad},
            {capsAndroid44}
        };

        return Arrays.asList(caps);

    }

    public NoLoginTest(DesiredCapabilities pCaps) {
        System.out.println("new a test instant");
        this.caps = pCaps;
    }

    @Test
    public void webDriver() throws Exception {
        System.out.println("testing start");
        driver = new RemoteWebDriver(
            new URL("http://miterfrants:08d2200d-eabe-4d7e-817b-ecb7fb03af57@ondemand.saucelabs.com:80/wd/hub"),
            this.caps);
        this.sessionId = ((RemoteWebDriver)driver).getSessionId().toString();

        String url = "http://hahow.csie.org/";
        driver.get(url);
        System.out.println("URL:" + url);
        assertEquals("分享，學習 - Hahow 好學校", driver.getTitle());    
        driver.quit();
        System.out.println("job finished");
    }

    @Override
    public String getSessionId() {
            return sessionId;
    }

}