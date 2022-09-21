import com.core.listeners.RetryFailedTest;
import com.core.utilities.DataProviderUtil;
import com.umggaming.pages.EditTeam;
import com.umggaming.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class CreateATeamTest {
    HomePage homePage = new HomePage();
    EditTeam editTeam = new EditTeam();

    @Test(dataProvider = "getData", dataProviderClass = DataProviderUtil.class, retryAnalyzer = RetryFailedTest.class)
    public void createTeamTest(Map<String, String> map) {
        if (!homePage.isLoggedIn()) {
            homePage.login(map.get("Username"), map.get("Password"));
        }
        homePage.clickCreateTeamBox()
                .ctSelectGame(map.get("GameKeyword"))
                .ctSelectGameDetails(map.get("TeamName"), map.get("Region"), map.get("Platform"), map.get("Ladder"));
        Assert.assertEquals(editTeam.getConfirmationMsg().getText(), "SUCCESS");
        editTeam.clickCloseSMsg();
        homePage.logout();
    }
}
