package com.colombiana.ingsoftware;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        Parse.initialize(this, "A4cw5wjpC7yBcw9aeAAQXAgXjM03C55DwQqb8fNx", "xlSbsqMKMMP9YavjRrQrqTi7TQVDvrzW1z7wqiST");


        final ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent profile = new Intent(LoginActivity.this, VisitadorActivity.class);
            startActivity(profile);
            finish();
        }

        TextView registro=(TextView)findViewById(R.id.textView4);
        TextView login = (TextView) findViewById(R.id.textView3);
        final EditText userName = (EditText) findViewById(R.id.textView);
        final EditText pass = (EditText) findViewById(R.id.textView2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this,"","Conectando...",true,false);
                if(!userName.getText().toString().isEmpty() && !pass.getText().toString().isEmpty())
                {
                    ParseUser.logInInBackground(userName.getText().toString(),pass.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser parseUser, ParseException e) {
                            if(parseUser != null)
                            {
                                progressDialog.dismiss();
                                log();
                            }else{
                                progressDialog.dismiss();
                                e.printStackTrace();
                            }
                        }
                    });
                }

            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(mainIntent);
            }
        });
    }

    private void log() {
        Intent login = new Intent(LoginActivity.this, VisitadorActivity.class);
        startActivity(login);
        finish();
    }


}

