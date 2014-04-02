package com.colombiana.ingsoftware;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.io.ByteArrayOutputStream;


public class RegisterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(this,"A4cw5wjpC7yBcw9aeAAQXAgXjM03C55DwQqb8fNx","xlSbsqMKMMP9YavjRrQrqTi7TQVDvrzW1z7wqiST");
        setContentView(R.layout.activity_register);


        TextView registrarse = (TextView) findViewById(R.id.textView3);
        final EditText nombre=(EditText) findViewById(R.id.editText);
        final EditText apellido=(EditText) findViewById(R.id.editText2);
        final EditText cedula=(EditText) findViewById(R.id.editText3);
        final EditText celular=(EditText) findViewById(R.id.editText4);
        final EditText usuario=(EditText) findViewById(R.id.editText5);
        final EditText password=(EditText) findViewById(R.id.editText6);
        final EditText password2=(EditText) findViewById(R.id.editText7);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean validador;
                if((!nombre.getText().toString().isEmpty())&&(!apellido.getText().toString().isEmpty())&&
                   (!cedula.getText().toString().isEmpty())&&(!celular.getText().toString().isEmpty())&&
                   (!usuario.getText().toString().isEmpty())&&(!password.getText().toString().isEmpty())&&
                   (!password2.getText().toString().isEmpty())){

                    if(celular.getText().length()==10) {
                        if (password.getText().toString().equals(password2.getText().toString())) {
                            if(password.getText().length()>=6){
                                validador=true;
                            }
                            else{
                                validador = false;
                                password.setText("");
                                password.setHintTextColor(Color.RED);
                                password2.setText("");
                                password2.setHintTextColor(Color.RED);
                                Toast.makeText(RegisterActivity.this,"Contraseña Min 6 Caract",Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            validador = false;
                            password.setText("");
                            password.setHintTextColor(Color.RED);
                            password2.setText("");
                            password2.setHintTextColor(Color.RED);
                            Toast.makeText(RegisterActivity.this, "Contraseñas no concuerdan", Toast.LENGTH_LONG).show();
                        }
                    }

                    else{
                        validador = false;
                        celular.setText("");
                        celular.setHintTextColor(Color.RED);
                        Toast.makeText(RegisterActivity.this,"Celular debe tener 10 digitos",Toast.LENGTH_LONG).show();
                    }


                    if(validador) {

                        ParseUser user= new ParseUser();
                        user.setUsername(usuario.getText().toString());
                        user.setPassword(password.getText().toString());
                        user.setEmail(usuario.getText().toString()+"@unisabana.edu.co");

                        user.put("nombre",nombre.getText().toString());
                        user.put("apellido", apellido.getText().toString());
                        user.put("cedula", cedula.getText().toString());
                        user.put("celular",celular.getText().toString());

                        Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.picture);

                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bmp.compress(Bitmap.CompressFormat.PNG,5,stream);
                        byte[] data = stream.toByteArray();

                        ParseFile file = new ParseFile("foto.png",data);

                        user.put("foto",file);

                        user.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e==null) {
                                    new AlertDialog.Builder(RegisterActivity.this).setTitle("Congratulations!")
                                            .setMessage("Registro Exitoso!")
                                            .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    final ProgressDialog progressDialog = ProgressDialog.show(RegisterActivity.this,"","Conectando....",true,false);
                                                    ParseUser.logInInBackground(usuario.getText().toString(),password.getText().toString(), new LogInCallback() {
                                                        @Override
                                                        public void done(ParseUser parseUser, ParseException e) {

                                                            if(parseUser != null) {
                                                                progressDialog.dismiss();
                                                                login();
                                                            }else{
                                                                progressDialog.dismiss();
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    });
                                                }
                                            }).show();
                                }  else{

                                    e.printStackTrace();
                                }
                            }
                        });


                    }
                }

                else{
                    new AlertDialog.Builder(RegisterActivity.this).setTitle("Error!")
                                                                  .setMessage("Uno o más campos vacios, ingrese todos los datos!")
                                                                  .setNeutralButton("Ok",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();
                }
            }

        });

    }

    private void login() {
        Intent login = new Intent(RegisterActivity.this,VisitadorActivity.class);
        startActivity(login);
        finish();
    }

}
