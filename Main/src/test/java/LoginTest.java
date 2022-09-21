import com.core.base.CoreBase;
import com.core.listeners.RetryFailedTest;
import com.core.utilities.DataProviderUtil;
import com.umggaming.constants.ErrorMessages;
import com.umggaming.constants.SubUrls;
import com.umggaming.pages.LoginModal;
import com.umggaming.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class LoginTest {
    LoginModal loginModal = new LoginModal();
    HomePage homePage = new HomePage();

    @Test(dataProvider = "getData", dataProviderClass = DataProviderUtil.class, retryAnalyzer = RetryFailedTest.class)
    public void validLoginTest(Map<String, String> map) {
        boolean flag = loginModal.clickLoginBtn().clickRememberMe()
                .enterUnPwd(map.get("Username"), map.get("Password"))
                .clickLoginAccnt()
                .isLoggedIn();
        Assert.assertTrue(flag);
        homePage.logout();
    }

    @Test(dataProvider = "getData", dataProviderClass = DataProviderUtil.class, retryAnalyzer = RetryFailedTest.class)
    public void invalidLoginTest(Map<String, String> map) {
        boolean flag = loginModal.clickLoginBtn()
                .enterUnPwd(map.get("Username"), map.get("Password"))
                .clickLoginAccnt()
                .isLoggedIn();
        Assert.assertEquals(loginModal.getConfirmationMsg(), ErrorMessages.LOGINERRORMSG);
        Assert.assertFalse(flag);
    }

    @Test(dataProvider = "getData", dataProviderClass = DataProviderUtil.class, retryAnalyzer = RetryFailedTest.class)
    public void privacyPolicyLinkTest(Map<String, String> map) {
        loginModal.clickLoginBtn().clickPrivacyOfPolicyLink();
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.PRIVACYPOLICYURL);
    }

    @Test(dataProvider = "getData", dataProviderClass = DataProviderUtil.class, retryAnalyzer = RetryFailedTest.class)
    public void termsOfUseLinkTest(Map<String, String> map) {
        loginModal.clickLoginBtn().clickTermsOfUseLink();
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.TERMSOFUSEURL);
    }
}
