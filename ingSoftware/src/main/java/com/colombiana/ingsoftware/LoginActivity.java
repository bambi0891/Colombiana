package com.colombiana.ingsoftware;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //Se quita ActionBar para LogIn
    }

}
