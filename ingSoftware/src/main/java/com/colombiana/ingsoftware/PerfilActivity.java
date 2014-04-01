package com.colombiana.ingsoftware;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;


public class PerfilActivity extends Activity {

    ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_perfil);

        user = ParseUser.getCurrentUser();

        TextView nombre = (TextView) findViewById(R.id.textView);
        TextView celular = (TextView) findViewById(R.id.textView2);
        TextView cedula = (TextView) findViewById(R.id.textView3);
        TextView username = (TextView) findViewById(R.id.textView4);
        Button pass = (Button) findViewById(R.id.button);
        Button update = (Button) findViewById(R.id.button);

        nombre.setText(nombre.getText() + user.getString("nombre") + " "+user.getString("apellido"));
        celular.setText(celular.getText()+ user.getString("celular"));
        cedula.setText(cedula.getText()+ user.getString("cedula"));

        System.out.println("hola");

        username.setText(username.getText()+ user.getUsername());


    }

}
