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

public class NoLoginTest
 {

    private List<DesiredCapabilities> drivers;
    private WebDriver driver;
    @Before
    public void setUp() throws Exception {
        drivers = new ArrayList<DesiredCapabilities>();
        // Choose the browser, version, and platform to test
        DesiredCapabilities capsIE11Win81 = DesiredCapabilities.internetExplorer();
        capsIE11Win81.setCapability("platform", "Windows 8.1");
        capsIE11Win81.setCapability("version", "11.0");

        this.drivers.add(capsIE11Win81);

        DesiredCapabilities capsIE11Win7 = DesiredCapabilities.internetExplorer();
        capsIE11Win7.setCapability("platform", "Windows 7");
        capsIE11Win7.setCapability("version", "11.0");

        this.drivers.add(capsIE11Win7);
    }

    @Test
    public void webDriver() throws Exception {

        for(int i=0;i<this.drivers.size();i++){
            driver = new RemoteWebDriver(
                new URL("http://miterfrants:08d2200d-eabe-4d7e-817b-ecb7fb03af57@ondemand.saucelabs.com:80/wd/hub"),
                this.drivers.get(i));
            String url = "http://hahow.csie.org/";
            driver.get(url);
            System.out.println("URL:" + url);
            assertEquals("分享，學習 - Hahow 好學校", driver.getTitle());    
            driver.quit();
            System.out.println("job finished");
        }

    }

    @After
    public void tearDown() throws Exception {
        
    }

}