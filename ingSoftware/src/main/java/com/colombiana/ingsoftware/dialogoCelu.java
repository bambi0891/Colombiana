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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class dialogoCelu extends Activity {

    boolean valida=false;

    public dialogoCelu() {

    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_dialogo_celu);

        final Button boton=(Button) findViewById(R.id.button);
        final Button boton2=(Button) findViewById(R.id.button2);
        final EditText celu=(EditText) findViewById(R.id.editText);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = ProgressDialog.show(dialogoCelu.this,"","Validando...",true,false);

                if((!celu.getText().toString().isEmpty())) {

                        if (celu.getText().length() == 10) {
                            valida = true;
                            progressDialog.dismiss();
                        }
                        else {
                            progressDialog.dismiss();
                            valida = false;
                            celu.setText("");
                            celu.setHint("Nuevo No. Celular");
                            celu.setHintTextColor(Color.RED);
                            Toast.makeText(dialogoCelu.this, "Celular Min 10 Caract", Toast.LENGTH_SHORT).show();
                        }
                }

                else{
                    progressDialog.dismiss();
                    valida=false;
                    celu.setText("");
                    celu.setHint("Nuevo No. Celular");
                    celu.setHintTextColor(Color.RED);
                    Toast.makeText(dialogoCelu.this, "Ingrese un No. Celular", Toast.LENGTH_SHORT).show();
                }


                if(valida){
                    final ProgressDialog progressDialog2 = ProgressDialog.show(dialogoCelu.this,"","Actualizando...",true,false);

                                ParseUser.getCurrentUser().put("celular",celu.getText().toString());
                                ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                                    @Override
                                   public void done(ParseException e) {
                                        if(e == null)
                                        {
                                            progressDialog2.dismiss();
                                            new AlertDialog.Builder(dialogoCelu.this)
                                                    .setMessage("Cambio de Exitoso")
                                                    .setNeutralButton("Ok",new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            finish();

                                                        }
                                                    }).show();


                                        }
                                        else{
                                            progressDialog2.dismiss();
                                            new AlertDialog.Builder(dialogoCelu.this).setTitle("Error!")
                                                    .setMessage("No pudo realizarse el cambio,intente de nuevo")
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

        });


        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        
    }


}
