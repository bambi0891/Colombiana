package com.colombiana.ingsoftware;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class RegisterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                if((!nombre.getText().toString().equals(""))&&(!apellido.getText().toString().equals(""))&&
                   (!cedula.getText().toString().equals(""))&&(!celular.getText().toString().equals(""))&&
                   (!usuario.getText().toString().equals(""))&&(!password.getText().toString().equals(""))&&
                   (!password2.getText().toString().equals(""))){

                    if(celular.getText().length()==10) {
                        if (password.getText().toString().equals(password2.getText().toString())) {
                            if(password.getText().length()>=8){
                                validador=true;
                            }
                            else{
                                validador = false;
                                password.setText("");
                                password.setHintTextColor(Color.RED);
                                password2.setText("");
                                password2.setHintTextColor(Color.RED);
                                new AlertDialog.Builder(RegisterActivity.this).setTitle("Error!")
                                        .setMessage("La contraseña debe tener por lo menos 8 caracteres!")
                                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                            }
                                        }).show();
                            }
                        }
                        else{
                            validador = false;
                            password.setText("");
                            password.setHintTextColor(Color.RED);
                            password2.setText("");
                            password2.setHintTextColor(Color.RED);
                            new AlertDialog.Builder(RegisterActivity.this).setTitle("Error!")
                                    .setMessage("Las contraseñas no coinciden!")
                                    .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    }).show();
                        }
                    }

                    else{
                        validador = false;
                        celular.setText("");
                        celular.setHintTextColor(Color.RED);
                        new AlertDialog.Builder(RegisterActivity.this).setTitle("Error!")
                                .setMessage("El No. Celular debe contener 10 digitos!")
                                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).show();
                    }


                    if(validador) {
                        new AlertDialog.Builder(RegisterActivity.this).setTitle("Congratulations!")
                                .setMessage("Registro Exitoso!")
                                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).show();
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

}
