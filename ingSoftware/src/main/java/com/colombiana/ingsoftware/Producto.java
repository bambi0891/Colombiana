package com.colombiana.ingsoftware;




import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;


public class Producto extends Fragment {


    public Producto() {
    }

    boolean sw=true;

    int contador=0;

    ParseObject store;
    ParseObject product;

    Button boton;
    Button boton2;
    EditText producto;
    EditText precio;
    EditText marca;
    EditText observaciones;


    boolean validador;
    //Variables de la Tienda
    String tienda;
    String direccion;
    String fecha;
    String hora;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_producto, container, false);

        boton=(Button) view.findViewById(R.id.button);
        boton2=(Button) view.findViewById(R.id.button2);

        producto=(EditText) view.findViewById(R.id.editText);
        producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                producto.setText("");
            }
        });

        precio=(EditText) view.findViewById(R.id.editText2);
        precio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                precio.setText("");
            }
        });

        marca=(EditText) view.findViewById(R.id.editText3);
        marca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marca.setText("");
            }
        });

        observaciones=(EditText) view.findViewById(R.id.editText);
        observaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                observaciones.setText("");
            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),"","Validando...",true,false);

                if((!producto.getText().toString().isEmpty())&&(!precio.getText().toString().isEmpty())&&
                        (!marca.getText().toString().isEmpty())&&(!observaciones.getText().toString().isEmpty())){

                    Tienda tiendita=new Tienda();
                    validador=tiendita.validar();

                    if(validador){
                        tienda=tiendita.tienda.getText().toString();
                        direccion=tiendita.direccion.getText().toString();
                        fecha=tiendita.fecha.getText().toString();
                        hora=tiendita.fecha.getText().toString();

                        tiendita.tienda.setEnabled(false);
                        tiendita.direccion.setEnabled(false);

                        if(sw) {
                            store = new ParseObject("Store");
                            store.put("Nombre", tienda);
                            store.put("Dirección", direccion);
                            store.put("Fecha", fecha);
                            store.put("Hora", hora);
                            store.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if(e==null){
                                        sw=false;
                                    }
                                    else {
                                        progressDialog.dismiss();
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                        if(!sw) {
                            product = new ParseObject("Product");
                            product.put("Nombre Tienda", tienda);
                            product.put("Producto", producto.getText().toString());
                            product.put("Precio", precio.getText().toString());
                            product.put("Marca", marca.getText().toString());
                            product.put("Observaciones", observaciones.getText().toString());
                            product.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getActivity(), "Producto guardado!", Toast.LENGTH_LONG).show();
                                        contador++;
                                        producto.setHint("Nombre del Producto");
                                        precio.setHint("Precio");
                                        marca.setHint("Marca");
                                        observaciones.setHint("(Escriba aqui...)");
                                    }
                                    else{
                                        progressDialog.dismiss();
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }


                    }
                    else{
                        progressDialog.dismiss();
                        new AlertDialog.Builder(getActivity()).setTitle("Error!")
                                .setMessage("Uno o más campos vacios de la TIENDA <----(Devolvere)")
                                .setNeutralButton("Ok",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).show();
                    }

                }
                else{
                    progressDialog.dismiss();
                    new AlertDialog.Builder(getActivity()).setTitle("Error!")
                            .setMessage("Uno o más campos vacios del PRODUCTO, ingrese todos los datos!")
                            .setNeutralButton("Ok",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();
                }
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contador>0) {
                    Tienda tiendita=new Tienda();
                    tiendita.tienda.setText("Local/Establecimiento");
                    tiendita.direccion.setText("Direccion");
                    sw=true;
                    tiendita.hora.setText(tiendita.df1.format(tiendita.c.getTime()));
                    tiendita.fecha.setText(tiendita.df2.format(tiendita.c.getTime()));
                    producto.setHint("Nombre del Producto");
                    precio.setHint("Precio");
                    marca.setHint("Marca");
                    observaciones.setHint("(Escriba aqui...)");

                    new AlertDialog.Builder(getActivity()).setTitle("Exitoso!")
                            .setMessage("Información almacenada en el servidor!)")
                            .setNeutralButton("Ok",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();
                }

                else{
                    Toast.makeText(getActivity(),"Agregue Productos!",Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }


}
