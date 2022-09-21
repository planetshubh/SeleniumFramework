package com.umggaming.constants;

import com.core.base.PropertyLoader;

public class SubUrls {
    public static final String BASEURL = PropertyLoader.getProp("url");
    public static final String PRIVACYPOLICYURL = BASEURL + "/privacy";
    public static final String TERMSOFUSEURL = BASEURL + "/terms";
    public static final String LOGINPAGEURL = BASEURL + "/login";
    public static final String SECURITYQUESPAGEURL = BASEURL + "/account/security";

}
