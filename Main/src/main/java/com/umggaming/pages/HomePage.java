package com.umggaming.pages;

import com.core.base.CoreBase;
import com.core.enums.WaitStrategy;
import com.core.utilities.CoreUtils;
import com.umggaming.utils.RenderDynamicLocator;
import org.openqa.selenium.By;

public class HomePage extends CoreBase {

    CoreUtils utils = new CoreUtils(getDriver());

    By joinBtn = By.xpath("//a[normalize-space()='Join For Free!']");
    By loginBtn = By.xpath("//a[@class='btn-header']");
    By username = By.id("login");
    By password = By.xpath("//div[@class='modal-content']//input[@id='password']");
    By loginAcct = By.cssSelector("button[class='btn btn-primary btn-your-account']");
    By loggedUser = By.xpath("//strong[@class='name']");
    By dashLink = By.xpath("//a[normalize-space()='Dashboard']");
    By confirmationMsg = By.xpath("//div[@class='jq-toast-single animated fadeInUpBig']");
    By closeSMsg = By.xpath("//span[@class='close-jq-toast-single']");
    By createMatchIcon = By.xpath("//i[@class='fas fa-gamepad']");
    By filterGameTxtBx = By.xpath("//div[@id='cm-step1']//input[@id='filtergame']");
    By typeFree = By.id("cm-ladder-select");
    By typeCash = By.id("cm-wager-select");
    By typePrime = By.id("cm-prime-select");
    By ladderSingle = By.xpath("//div[@id='cm-ladder']/ul/li[contains(text(), 'Singles') or contains(text(), '1v1')]");
    By teamName = By.id("createteam-cm");
    By btnCreateTeam = By.id("cm-continue");
    By logoutBtn = By.xpath("//a[normalize-space()='Logout']/parent::li");
    String gameImg = "//ul[@id='filtergameul']//li[@alt='%s']//div[@class='background-image']";
    String region = "//li[normalize-space()='%s']";
    String platform = "//i[@class='umg umg-%s umg-lg']/parent::li[@id]";
    By joinTournamentIcon = By.cssSelector("#aside-tournaments");

    By createTeamBox = By.id("aside-createteam");
    By ctTeamName = By.id("createteam-ct");
    By ctTypeFree = By.id("ct-ladder-select");
    By ctBtnCreateTeam = By.id("ct-continue");
    String ctGameImg = "//ul[@id='ct-filtergameul']//li[@alt='%s']//div[@class='background-image']";
    String ctLadder = "//div[@id='ct-ladder']/ul/li[contains(text(),'%s')]";

    public String loginPageTitle() {
        return getDriver().getTitle();
    }

    public boolean login(String un, String pwd) {
        if (utils.verifyElementExists(loginBtn)) {
            utils.click(loginBtn);
            utils.sendKeys(username, un);
            pwd = utils.decodedString(pwd);
            utils.sendKeys(password, pwd);
            utils.click(loginAcct);
            return utils.getElement(loggedUser, WaitStrategy.CLICKABLE).isDisplayed();
        }
        return false;
    }

    public boolean isLoggedIn() {
        return utils.verifyElementExists(loggedUser);
    }

    public void logout() {
        if (isLoggedIn()) {
            utils.click(loggedUser);
            utils.click(logoutBtn);
        } else System.out.println("User is not logged in.");
    }

    public HomePage clickCreateMatchIcon() {
        utils.click(createMatchIcon);
        return this;
    }

    public HomePage selectGame(String gameKeyword) {
        utils.sendKeys(filterGameTxtBx, gameKeyword);
        utils.click(RenderDynamicLocator.getByLocator("xpath", gameImg, gameKeyword));
        return this;
    }

    public HomePage selectGameDetails(String tname, String region, String platform) {
        utils.click(RenderDynamicLocator.getByLocator("xpath", this.platform, platform));
        utils.click(typeFree);
        utils.click(RenderDynamicLocator.getByLocator("xpath", this.region, region));
        utils.click(ladderSingle);
        utils.sendKeys(teamName, tname);
        utils.click(btnCreateTeam);
        return this;
    }

    public HomePage clickCreateTeamBox() {
        utils.click(createTeamBox);
        return this;
    }

    public HomePage ctSelectGame(String gameKeyword) {
        utils.click(RenderDynamicLocator.getByLocator("xpath", ctGameImg, gameKeyword));
        return this;
    }

    public EditTeam ctSelectGameDetails(String tname, String region, String platform, String ladder) {
        utils.click(RenderDynamicLocator.getByLocator("xpath", this.platform, platform));
        utils.click(ctTypeFree);
        utils.click(RenderDynamicLocator.getByLocator("xpath", this.region, region));
        utils.click(RenderDynamicLocator.getByLocator("xpath", ctLadder, ladder));
        utils.sendKeys(ctTeamName, tname);
        utils.click(ctBtnCreateTeam);
        return new EditTeam();
    }

    public RegisterUser clickJoinForFreeBtn() {
        utils.click(joinBtn);
        return new RegisterUser();
    }

    public String getConfirmationMsg() {
        return utils.getElement(confirmationMsg, WaitStrategy.PRESENCE).getText().substring(2);
    }

    public HomePage clickCloseSMsg() {
        utils.click(closeSMsg);
        return this;
    }

    public HomePage clickJoinTournamentIcon() {
        utils.click(joinTournamentIcon);
        return this;
    }
}
