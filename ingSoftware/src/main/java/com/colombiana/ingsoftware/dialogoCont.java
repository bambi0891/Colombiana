package com.colombiana.ingsoftware;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class dialogoCont extends Activity {

    boolean valida=false;

    public dialogoCont() {

    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_dialogo_cont);

        final Button boton=(Button) findViewById(R.id.button);
        final Button boton2=(Button) findViewById(R.id.button2);
        final EditText password=(EditText) findViewById(R.id.editText);
        final EditText password2=(EditText) findViewById(R.id.editText2);
        final EditText password3=(EditText) findViewById(R.id.editText3);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = ProgressDialog.show(dialogoCont.this,"","Validando...",true,false);

                if((!password.getText().toString().isEmpty())&&(!password2.getText().toString().isEmpty())&&(!password3.getText().toString().isEmpty())) {
                    if (password2.getText().toString().equals(password3.getText().toString())) {
                        if (password2.getText().length() >= 6) {
                            valida = true;
                            progressDialog.dismiss();
                            Toast.makeText(dialogoCont.this, "Todo bien!", Toast.LENGTH_SHORT).show();
                            } else {
                                progressDialog.dismiss();
                                valida = false;
                                password2.setText("");
                                password2.setHintTextColor(Color.RED);
                                password3.setText("");
                                password3.setHintTextColor(Color.RED);
                                Toast.makeText(dialogoCont.this, "Contraseña Min 6 Caract", Toast.LENGTH_SHORT).show();
                            }
                    }
                    else{
                        progressDialog.dismiss();
                        valida = false;
                        password2.setText("");
                        password2.setHintTextColor(Color.RED);
                        password3.setText("");
                        password3.setHintTextColor(Color.RED);
                        Toast.makeText(dialogoCont.this, "Contraseñas no concuerdan", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    progressDialog.dismiss();
                    valida=false;
                    Toast.makeText(dialogoCont.this, "Campos Vacios", Toast.LENGTH_SHORT).show();
                }


                if(valida){
                    final ProgressDialog progressDialog2 = ProgressDialog.show(dialogoCont.this,"","Comprobando...",true,false);

                    ParseUser.logInInBackground(ParseUser.getCurrentUser().getUsername(),password.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser parseUser, ParseException e) {
                            if(parseUser != null){
                                progressDialog.dismiss();
                                ParseUser.getCurrentUser().setPassword(password2.getText().toString());
                                ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if(e == null)
                                        {
                                            progressDialog2.dismiss();
                                            new AlertDialog.Builder(dialogoCont.this)
                                                    .setMessage("Cambio de Contraseña Exitoso")
                                                    .setNeutralButton("Ok",new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            finish();
                                                        }
                                                    }).show();
                                            finish();

                                        }else{
                                            progressDialog2.dismiss();
                                            new AlertDialog.Builder(dialogoCont.this).setTitle("Error!")
                                                    .setMessage("La contraseña no se pudo cambiar, intente de nuevo")
                                                    .setNeutralButton("Ok",new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {

                                                        }
                                                    }).show();
                                        }
                                    }
                                });
                            }
                            else{
                                progressDialog2.dismiss();
                                password.setText("");
                                password.setHintTextColor(Color.RED);
                                password2.setText("");
                                password3.setText("");
                                Toast.makeText(dialogoCont.this, "Contraseña Invalida", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }

        });


        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        
    }


}
