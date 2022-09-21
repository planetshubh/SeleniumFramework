import com.core.base.CoreBase;
import com.core.listeners.Listener;
import com.core.listeners.RetryFailedTest;
import com.core.pages.CorePage;
import com.core.utilities.CoreUtils;
import com.core.utilities.DataProviderUtil;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Map;

/**
 * This class contains the unit test cases, to test out the initialization, utilities, etc. of the Core project.
 * I will add more test in future as needed.
 *
 * @author Shubhendra Singh
 */

@Listeners(Listener.class)
public class CoreTest extends CoreBase {
    CorePage corePage;
    CoreUtils coreUtils;

    protected CoreTest() {
        super();
    }

   /*
   @BeforeSuite
    public void beforeSuite(){
        ExtentReport.initReports();
    }

    @AfterSuite
    public void AfterSuite(){
        ExtentReport.flushReports();
    }
    */

    @BeforeClass
    public void setup() {
        initialization();
        corePage = new CorePage(getDriver());
        coreUtils = new CoreUtils(getDriver());
    }

    @Test(priority = 1)
    public void validatePageTitle() {
        Assert.assertEquals(corePage.loginPageTitle(), "UMG Gaming - Tournaments, Ladders, Live/On-Demand Esports Competitions, and More!");
        //coreUtils.captureScreenShot(getDriver()); --> haven't removed this code line as it might be needed for testing framework.
    }

    @Test(priority = 2, dataProvider = "getData", dataProviderClass = DataProviderUtil.class,retryAnalyzer = RetryFailedTest.class)
    public void loginTest(Map<String, String> map) {
        boolean flag = corePage.login(map.get("Username"), coreUtils.decodedString(map.get("Password")));
        Assert.assertTrue(flag);
    }

    @Test(priority = 3)
    public void dashNavTest() {
        corePage.dashNav();
    }

    @AfterClass
    public void quit() {
        getDriver().quit();
        unload();
    }
}

