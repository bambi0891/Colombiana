package com.colombiana.ingsoftware;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class Tienda extends Fragment {


    public Tienda() {
    }

    EditText tienda;
    EditText direccion;
    TextView fecha;
    TextView hora;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat df1=new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");


    boolean validador;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_tienda, container, false);

        tienda=(EditText) view.findViewById(R.id.editText);
        direccion=(EditText) view.findViewById(R.id.editText2);
        fecha=(TextView) view.findViewById(R.id.textView2);
        hora=(TextView) view.findViewById(R.id.textView3);

        fecha.setText(fecha.getText()+": "+df2.format(c.getTime()));
        hora.setText(hora.getText()+": "+df1.format(c.getTime()));

        tienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tienda.setText("");
            }
        });

        direccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                direccion.setText("");
            }
        });


        return  view;
    }

    public boolean validar(){
        validador = (!tienda.getText().toString().isEmpty()) && (!direccion.getText().toString().isEmpty());

        return validador;
    }


}
