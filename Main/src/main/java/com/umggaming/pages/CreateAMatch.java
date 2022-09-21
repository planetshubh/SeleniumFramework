package com.umggaming.pages;

import com.core.base.CoreBase;
import com.core.utilities.CoreUtils;
import org.openqa.selenium.By;

public class CreateAMatch extends CoreBase {

    CoreUtils utils = new CoreUtils(getDriver());

    By startTimeSimple = By.id("matchtime_simple");
    By btnCreateMatch = By.id("create_match_btn");

    public CreateAMatch clickCreateMatchBtn() {
        utils.click(btnCreateMatch);
        return this;
    }
}
