package com.tianzunchina.android.api.login;

/**
 * CraetTime 2016-3-22
 * @author SunLiang
 */
public class Luncher {
    private LoginListenner listenner;
    private String url;
    private String method;

    public Luncher(String url, String method, LoginListenner listenner) {
        this.url = url;
        this.method = method;
        this.listenner = listenner;
    }

    public void login(SignInUser user) {

    }

    public void success(){
        listenner.success("");
    }

}
