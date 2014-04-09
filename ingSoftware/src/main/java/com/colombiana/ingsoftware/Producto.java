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
import android.widget.TextView;
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
    EditText tienda;
    EditText direccion;
    TextView fecha;
    TextView hora;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_producto, container, false);

        boton=(Button) view.findViewById(R.id.button);
        boton2=(Button) view.findViewById(R.id.button2);

        producto=(EditText) view.findViewById(R.id.editText);

        precio=(EditText) view.findViewById(R.id.editText2);

        marca=(EditText) view.findViewById(R.id.editText3);

        observaciones=(EditText) view.findViewById(R.id.editText4);

        tienda=(EditText) getActivity().findViewById(R.id.editTextTienda);



        direccion=(EditText) getActivity().findViewById(R.id.editTextDireccion);

        fecha=(TextView) getActivity().findViewById(R.id.textViewFecha);
        hora=(TextView) getActivity().findViewById(R.id.textViewHora);




        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),"","Validando...",true,false);

                if((!producto.getText().toString().isEmpty())&&(!precio.getText().toString().isEmpty())&&
                        (!marca.getText().toString().isEmpty())&&(!observaciones.getText().toString().isEmpty())){

                    if((!tienda.getText().toString().isEmpty()) && (!direccion.getText().toString().isEmpty())){
                        validador=true;
                    }
                    else{
                        validador=false;
                        progressDialog.dismiss();
                        new AlertDialog.Builder(getActivity()).setTitle("Error!")
                                .setMessage("Uno o más campos vacios del Local <----Devolverse")
                                .setNeutralButton("Ok",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).show();
                    }

                }
                else{
                    validador=false;
                    progressDialog.dismiss();
                    new AlertDialog.Builder(getActivity()).setTitle("Error!")
                            .setMessage("Uno o más campos vacios del Producto, ingrese todos los datos!")
                            .setNeutralButton("Ok",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();
                }

                if(validador){
                    if(sw) {
                        store = new ParseObject("Store");
                        store.put("nombre", tienda.getText().toString());
                        store.put("direccion", direccion.getText().toString());
                        store.put("fecha", fecha.getText().toString());
                        store.put("hora", hora.getText().toString());
                        store.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e==null) {
                                    sw = false;
                                    product = new ParseObject("Product");
                                    product.put("nombre_tienda", tienda.getText().toString());
                                    product.put("producto", producto.getText().toString());
                                    product.put("precio", precio.getText().toString());
                                    product.put("marca", marca.getText().toString());
                                    product.put("observaciones", observaciones.getText().toString());
                                    product.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            if (e == null) {
                                                progressDialog.dismiss();
                                                Toast.makeText(getActivity(), "Producto guardado!", Toast.LENGTH_LONG).show();
                                                contador++;

                                                producto.setText("");
                                                precio.setText("");
                                                marca.setText("");
                                                observaciones.setText("");
                                                producto.setHint("Nombre del Producto");
                                                precio.setHint("Precio");
                                                marca.setHint("Marca");
                                                observaciones.setHint("(Escriba aqui...)");

                                                tienda.setEnabled(false);
                                                direccion.setEnabled(false);
                                            }
                                            else{
                                                progressDialog.dismiss();
                                                e.printStackTrace();
                                            }
                                        }
                                    });
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
                        product.put("nombre_tienda", tienda.getText().toString());
                        product.put("producto", producto.getText().toString());
                        product.put("precio", precio.getText().toString());
                        product.put("marca", marca.getText().toString());
                        product.put("observaciones", observaciones.getText().toString());
                        product.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(), "Producto guardado!", Toast.LENGTH_LONG).show();
                                    contador++;

                                    producto.setText("");
                                    precio.setText("");
                                    marca.setText("");
                                    observaciones.setText("");
                                    producto.setHint("Nombre del Producto");
                                    precio.setHint("Precio");
                                    marca.setHint("Marca");
                                    observaciones.setHint("(Escriba aqui...)");

                                    tienda.setEnabled(false);
                                    direccion.setEnabled(false);
                                }
                                else{
                                    progressDialog.dismiss();
                                    e.printStackTrace();
                                }
                            }
                        });
                    }


                }
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contador>0) {


                    sw=true;
                    tienda.setText("");
                    tienda.setHint("Local/Establecimiento");
                    direccion.setText("");
                    direccion.setHint("Direccion");
                    producto.setText("");
                    producto.setHint("Nombre del Producto");
                    precio.setText("");
                    precio.setHint("Precio");
                    marca.setText("");
                    marca.setHint("Marca");
                    observaciones.setText("");
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
