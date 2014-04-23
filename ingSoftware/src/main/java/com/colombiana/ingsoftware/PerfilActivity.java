package com.colombiana.ingsoftware;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;


public class PerfilActivity extends Activity {

    ParseUser user;
    TextView celular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_perfil);

        user = ParseUser.getCurrentUser();

        TextView nombre = (TextView) findViewById(R.id.textView);
        celular = (TextView) findViewById(R.id.textView2);
        TextView cedula = (TextView) findViewById(R.id.textView3);
        TextView username = (TextView) findViewById(R.id.textView4);
        Button pass = (Button) findViewById(R.id.button);
        Button update = (Button) findViewById(R.id.button2);
        final ImageView image = (ImageView) findViewById(R.id.imageView);

        ParseFile file = user.getParseFile("foto");
        file.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {

                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                image.setImageBitmap(bitmap);
            }
        });



        nombre.setText(nombre.getText() + user.getString("nombre") + " "+user.getString("apellido"));
        celular.setText(celular.getText()+ user.getString("celular"));
        cedula.setText(cedula.getText()+ user.getString("cedula"));

        username.setText(username.getText()+ user.getUsername());

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogoPass();

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogCelu();
            }
        });


    }

    private void DialogoPass() {
        Intent pass = new Intent(PerfilActivity.this,dialogoCont.class);
        startActivity(pass);
    }

    private void DialogCelu(){
        Intent celu=new Intent(PerfilActivity.this,dialogoCelu.class);
        startActivity(celu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        celular.setText("Celular:"+ user.getString("celular"));
    }
}
