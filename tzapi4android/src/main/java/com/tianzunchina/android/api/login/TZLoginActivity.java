package com.tianzunchina.android.api.login;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by admin on 2016/3/22 0022.
 */
public class TZLoginActivity extends Activity implements LoginListenner {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Luncher luncher = new Luncher("","",this);
    }

    protected void setAccountRegex(String regex){

    }

    public void login(SignInUser user){

    }

    @Override
    public void success(String str) {

    }

    @Override
    public void err(String str) {

    }
}
