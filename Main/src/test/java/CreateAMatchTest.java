import com.core.listeners.RetryFailedTest;
import com.core.utilities.DataProviderUtil;
import com.umggaming.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class CreateAMatchTest extends BaseTest {

    private CreateAMatchTest() {
        super();
    }

    HomePage homePage = new HomePage();
    EditTeam editTeam = new EditTeam();
    CreateAMatch createAMatch = new CreateAMatch();
    Match match = new Match();

    @Test(priority = 1, dataProvider = "getData", dataProviderClass = DataProviderUtil.class)
    public void validatePageTitle(Map<String, String> map) {
        Assert.assertEquals(homePage.loginPageTitle(), map.get("Title"));
    }

    @Test(priority = 2, dataProvider = "getData", dataProviderClass = DataProviderUtil.class, retryAnalyzer = RetryFailedTest.class)
    public void loginTest(Map<String, String> map) {
        boolean flag = homePage.login(map.get("Username"), map.get("Password"));
        Assert.assertTrue(flag);
    }

    @Test(priority = 3, dataProvider = "getData", dataProviderClass = DataProviderUtil.class, retryAnalyzer = RetryFailedTest.class)
    public void createMatchTest(Map<String, String> map) {
        if (!homePage.isLoggedIn()) {
            homePage.login(map.get("Username"), map.get("Password"));
        }
        homePage.clickCreateMatchIcon()
                .selectGame(map.get("GameKeyword"))
                .selectGameDetails(map.get("TeamName"), map.get("Region"), map.get("Platform"));
        Assert.assertEquals(editTeam.getConfirmationMsg().getText(), "SUCCESS");
        editTeam.clickCloseSMsg();
        Assert.assertEquals(editTeam.getEligibility().getText(), "Eligible");
        editTeam.clickCreateMatch().clickCreateMatchBtn();
        Assert.assertEquals(match.getConfirmationMsg().getText(), "SUCCESS");
        homePage.logout();
    }

}