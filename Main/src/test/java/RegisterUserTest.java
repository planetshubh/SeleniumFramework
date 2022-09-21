import com.core.base.CoreBase;
import com.core.listeners.RetryFailedTest;
import com.core.utilities.DataProviderUtil;

import com.umggaming.constants.ConstantTexts;
import com.umggaming.constants.ErrorMessages;
import com.umggaming.constants.SelectBy;
import com.umggaming.constants.SubUrls;
import com.umggaming.pages.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class RegisterUserTest {
    HomePage homePage = new HomePage();
    RegisterUser registerUser = new RegisterUser();
    SecurityQuestions securityQuestions = new SecurityQuestions();
    HeaderElements headerElements = new HeaderElements();
    ActivateAccount activateAccount = new ActivateAccount();

    @Test(dataProvider = "getData", dataProviderClass = DataProviderUtil.class, retryAnalyzer = RetryFailedTest.class)
    public void registerBlankDataTest(Map<String, String> map) {
        homePage.clickJoinForFreeBtn().clickRegisterBtn();
        Assert.assertEquals(registerUser.getErrorMsg("email"), ErrorMessages.REG_MANDATORYFIELDERROR);
        Assert.assertEquals(registerUser.getErrorMsg("username"), ErrorMessages.REG_MANDATORYFIELDERROR);
        Assert.assertEquals(registerUser.getErrorMsg("password"), ErrorMessages.REG_MANDATORYFIELDERROR);
        Assert.assertEquals(registerUser.getErrorMsg("privacy"), ErrorMessages.REG_MANDATORYFIELDERROR);
        Assert.assertEquals(registerUser.getErrorMsg("terms"), ErrorMessages.REG_MANDATORYFIELDERROR);
    }

    @Test(dataProvider = "getData", dataProviderClass = DataProviderUtil.class, retryAnalyzer = RetryFailedTest.class)
    public void registerWoTermsPrivacyTest(Map<String, String> map) {
        homePage.clickJoinForFreeBtn()
                .enterEmail(map.get("Email"))
                .enterUsername(map.get("Username"))
                .selectBirthMonth(map.get("MM"), SelectBy.VISIBILETEXT)
                .selectBirthDay(map.get("DD"), SelectBy.VALUE)
                .selectBirthYear(map.get("YY"), SelectBy.INDEX)
                .enterPwd(map.get("Password"))
                .enterConfirmPwd(map.get("CPassword"))
                .clickRegisterBtn();
        Assert.assertEquals(registerUser.getErrorMsg("privacy"), ErrorMessages.REG_MANDATORYFIELDERROR);
        Assert.assertEquals(registerUser.getErrorMsg("terms"), ErrorMessages.REG_MANDATORYFIELDERROR);
    }

    @Test(dataProvider = "getData", dataProviderClass = DataProviderUtil.class, retryAnalyzer = RetryFailedTest.class)
    public void registerInvalidBdayTest(Map<String, String> map) {
        homePage.clickJoinForFreeBtn()
                .enterEmail(map.get("Email"))
                .enterUsername(map.get("Username"))
                .selectBirthMonth(map.get("MM"), SelectBy.VISIBILETEXT)
                .selectBirthDay(map.get("DD"), SelectBy.VISIBILETEXT)
                .selectBirthYear(map.get("YY"), SelectBy.VISIBILETEXT)
                .enterPwd(map.get("Password"))
                .enterConfirmPwd(map.get("CPassword"))
                .clickTermsCheckBx()
                .clickPrivacyCheckBx()
                .clickRegisterBtn();
        Assert.assertEquals(homePage.getConfirmationMsg(), ErrorMessages.REG_INVALIDAGEERROR);
    }

    @Test(dataProvider = "getData", dataProviderClass = DataProviderUtil.class, retryAnalyzer = RetryFailedTest.class)
    public void registerSubLinksTest(Map<String, String> map) {
        homePage.clickJoinForFreeBtn().clickTncLink();
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.TERMSOFUSEURL);
        homePage.clickJoinForFreeBtn().clickPrivacyLink1();
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.PRIVACYPOLICYURL);
        homePage.clickJoinForFreeBtn().clickPrivacyLink2();
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.PRIVACYPOLICYURL);
        homePage.clickJoinForFreeBtn().clickLoginLink();
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.LOGINPAGEURL);
    }

    @Test(dataProvider = "getData", dataProviderClass = DataProviderUtil.class, retryAnalyzer = RetryFailedTest.class)
    public void registerExistingUsernameEmailTest(Map<String, String> map) {
        homePage.clickJoinForFreeBtn()
                .enterEmail(map.get("Email"))
                .enterUsername(map.get("Username"))
                .selectBirthMonth(map.get("MM"), SelectBy.VISIBILETEXT);
        Assert.assertEquals(registerUser.getErrorMsg("email"), ErrorMessages.REG_EXISTINGEMAIL);
        Assert.assertEquals(registerUser.getErrorMsg("username"), ErrorMessages.REG_EXISTINGUSERNAME);
    }

    @Test(dataProvider = "getData", dataProviderClass = DataProviderUtil.class, retryAnalyzer = RetryFailedTest.class)
    public void registerNonMatchingPwdTest(Map<String, String> map) {
        homePage.clickJoinForFreeBtn()
                .enterEmail(map.get("Email"))
                .enterUsername(map.get("Username"))
                .selectBirthMonth(map.get("MM"), SelectBy.VISIBILETEXT)
                .selectBirthDay(map.get("DD"), SelectBy.VISIBILETEXT)
                .selectBirthYear(map.get("YY"), SelectBy.VISIBILETEXT)
                .enterPwd(map.get("Password"))
                .enterConfirmPwd(map.get("CPassword"))
                .clickTermsCheckBx()
                .clickPrivacyCheckBx()
                .clickRegisterBtn();
        Assert.assertEquals(homePage.getConfirmationMsg(), ErrorMessages.REG_NONMATCHINGPWD);
    }

    @Test(dataProvider = "getData", dataProviderClass = DataProviderUtil.class, retryAnalyzer = RetryFailedTest.class)
    public void registerBlankDOBTest(Map<String, String> map) {
        homePage.clickJoinForFreeBtn()
                .enterEmail(map.get("Email"))
                .enterUsername(map.get("Username"))
                .enterPwd(map.get("Password"))
                .enterConfirmPwd(map.get("CPassword"))
                .clickTermsCheckBx()
                .clickPrivacyCheckBx()
                .clickRegisterBtn();
        Assert.assertEquals(homePage.getConfirmationMsg(), ErrorMessages.REG_BlankDOB);
    }

    @Test(dataProvider = "getData", dataProviderClass = DataProviderUtil.class, retryAnalyzer = RetryFailedTest.class)
    public void registerE2ETest(Map<String, String> map) {
        homePage.clickJoinForFreeBtn()
                .enterEmail(map.get("Email"))
                .enterUsername(map.get("Username"))
                .selectBirthMonth(map.get("MM"), SelectBy.VISIBILETEXT)
                .selectBirthDay(map.get("DD"), SelectBy.VISIBILETEXT)
                .selectBirthYear(map.get("YY"), SelectBy.VISIBILETEXT)
                .enterPwd(map.get("Password"))
                .enterConfirmPwd(map.get("CPassword"))
                .clickTermsCheckBx()
                .clickPrivacyCheckBx()
                .selectTimezone(map.get("Value"), SelectBy.VALUE)
                .clickRegisterBtn();
        Assert.assertEquals(securityQuestions.getHeading().getText(), ConstantTexts.SECURITYQUESPAGEHEADING);
        homePage.clickCreateMatchIcon();
        securityQuestions.clickCmWarningDialogLink1();
        homePage.clickCreateMatchIcon();
        Assert.assertEquals(securityQuestions.getCmWarningDialog().getText(), ConstantTexts.REG_ACCOUNTACTIVATIONWARNING_CM);
        securityQuestions.closeCmWarningDialog();
        homePage.clickCreateTeamBox();
        securityQuestions.clickCtWarningDialogLink1();
        homePage.clickCreateTeamBox();
        Assert.assertEquals(securityQuestions.getCtWarningDialog().getText(), ConstantTexts.REG_ACCOUNTACTIVATIONWARNING_CT);
        securityQuestions.closeCtWarningDialog();

        headerElements.clickMenuOrSubmenu("Leaderboards").clickMenuOrSubmenu("Monthly");
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickMenuOrSubmenu("Leaderboards").clickMenuOrSubmenu("Lifetime");
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickMenuOrSubmenu("Support").clickMenuOrSubmenu("Support Center");
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickMenuOrSubmenu("Support").clickMenuOrSubmenu("Tickets");
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickMenuOrSubmenu("Support").clickMenuOrSubmenu("Live Chat");
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickMenuOrSubmenu("Store");
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickMenuOrSubmenu("Play").clickMenuOrSubmenu("Home");
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickMenuOrSubmenu("Matchfinder");
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickMenuOrSubmenu("Tournaments");
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickSearchIcon();
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickBellIcon().clickNotifViewAll();
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickMySchedule();
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickMyTeams().clickCreateTeam();
        Assert.assertEquals(securityQuestions.getCtWarningDialog().getText(), ConstantTexts.REG_ACCOUNTACTIVATIONWARNING_CT);
        securityQuestions.closeCtWarningDialog();
        headerElements.clickPrimeLogo();
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickProfileBox().clickMenuOrSubmenu("Dashboard");
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickProfileBox().clickMenuOrSubmenu("Account");
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickProfileBox().clickMenuOrSubmenu("Bank");
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickProfileBox().clickMenuOrSubmenu("My Profile");
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickProfileBox().clickMenuOrSubmenu("My Teams");
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickProfileBox().clickMenuOrSubmenu("Orders");
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickProfileBox().clickMenuOrSubmenu("Withdraw");
        Assert.assertTrue(CoreBase.getDriver().getCurrentUrl().contains(SubUrls.SECURITYQUESPAGEURL));
        headerElements.clickProfileBox().clickMenuOrSubmenu("Deposit Cash");
        Assert.assertTrue(CoreBase.getDriver().getCurrentUrl().contains(SubUrls.SECURITYQUESPAGEURL));
        headerElements.clickProfileBox().clickMenuOrSubmenu("Buy Credits");
        Assert.assertTrue(CoreBase.getDriver().getCurrentUrl().contains(SubUrls.SECURITYQUESPAGEURL));
        headerElements.clickProfileBox().clickMenuOrSubmenu("Prime Membership");
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickProfileBox().clickProfileInvites();
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);
        headerElements.clickProfileBox().clickProfilePrizeClaim();
        Assert.assertEquals(CoreBase.getDriver().getCurrentUrl(), SubUrls.SECURITYQUESPAGEURL);

        securityQuestions.selectQuestion("1", map.get("SecQuestion1")).enterAnswer("1", map.get("SecAnswer1"))
                .selectQuestion("2", map.get("SecQuestion2")).enterAnswer("2", map.get("SecAnswer2"))
                .selectQuestion("3", map.get("SecQuestion3")).enterAnswer("3", map.get("SecAnswer3"))
                .clickContinue();
        Assert.assertEquals(activateAccount.getgdprConfirmModalMsg(), ConstantTexts.GDPRTEXT);
        activateAccount.clickgdprAcceptBtn();
        Assert.assertEquals(activateAccount.getVerifyEmailHdr(), ConstantTexts.VERIFYEMAILPAGEHEADER);
        Assert.assertEquals(activateAccount.getUserEmailId(), map.get("Email"));
    }

    @AfterMethod
    public void navigateToHome() {
        registerUser.clickUmgLogo();
    }
}