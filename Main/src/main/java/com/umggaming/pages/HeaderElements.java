package com.umggaming.pages;

import com.core.base.CoreBase;
import com.core.utilities.CoreUtils;
import com.umggaming.utils.RenderDynamicLocator;
import org.openqa.selenium.By;


public class HeaderElements extends CoreBase {

    CoreUtils utils = new CoreUtils(getDriver());

    String menuOrSubmenu = "//a[normalize-space()='%s']";

    By searchIcon = By.cssSelector(".icon-search");
    By bellIcon = By.xpath("//a[@role='button']//i[@class='icon-bell']");
    By notifViewAll = By.cssSelector("a[class='nt-footer-btn']");
    By notifMarkAllRead = By.cssSelector(".nt-footer-btn.mark-all-as-read");
    By profileBox = By.xpath("//a[@class='profile-box']");
    By profileInvites = By.xpath("//a[@href='https://qa.umggaming.com/my-invites']");
    By profilePrizeClaim = By.xpath("//i[@class='icon-gift']");
    By darkThemeToggle = By.xpath("//div[contains(@class,'dark-theme-link')]/following-sibling::div/label");
    By mySchedule = By.xpath("//ul[@class='submenu-right']//a[normalize-space()='My Schedule']");
    By myTeams = By.cssSelector("#menu-team");
    By createTeam = By.className("dropdown-item");
    By primeLogo = By.xpath("//a[@class='btn btn-primary p-logo']");

    public HeaderElements clickMenuOrSubmenu(String menuOrSubmenuName) {
        By renderedXpath = RenderDynamicLocator.getByLocator("xpath", menuOrSubmenu, menuOrSubmenuName);
        utils.click(renderedXpath);
        return this;
    }


    public HeaderElements clickSearchIcon() {
        utils.click(searchIcon);
        return this;
    }

    public HeaderElements clickBellIcon() {
        utils.click(bellIcon);
        return this;
    }

    public HeaderElements clickNotifViewAll() {
        utils.click(notifViewAll);
        return this;
    }

    public HeaderElements clickNotifMarkAllRead() {
        utils.click(notifMarkAllRead);
        return this;
    }

    public HeaderElements clickProfileBox() {
        utils.click(profileBox);
        return this;
    }

    public HeaderElements clickProfileInvites() {
        utils.click(profileInvites);
        return this;
    }

    public HeaderElements clickProfilePrizeClaim() {
        utils.click(profilePrizeClaim);
        return this;
    }

    public HeaderElements clickDarkThemeToggle() {
        utils.click(darkThemeToggle);
        return this;
    }

    public HeaderElements clickMySchedule() {
        utils.click(mySchedule);
        return this;
    }

    public HeaderElements clickMyTeams() {
        utils.click(myTeams);
        return this;
    }

    public HeaderElements clickCreateTeam() {
        utils.click(createTeam);
        return this;
    }

    public HeaderElements clickPrimeLogo() {
        utils.click(primeLogo);
        return this;
    }

}
