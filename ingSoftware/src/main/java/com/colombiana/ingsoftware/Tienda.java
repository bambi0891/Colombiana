package com.colombiana.ingsoftware;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;



public class Tienda extends Fragment {


    public Tienda() {
    }


    TextView fech;
    TextView hor;
    ImageView refresh;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat df1=new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1= inflater.inflate(R.layout.fragment_tienda, container, false);
        fech=(TextView) view1.findViewById(R.id.textViewFecha);
        hor=(TextView) view1.findViewById(R.id.textViewHora);
        refresh=(ImageView) view1.findViewById(R.id.imageView2);
        fech.setText(df2.format(c.getTime()));
        hor.setText(df1.format(c.getTime()));

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=Calendar.getInstance();
                fech.setText(df2.format(c.getTime()));
                hor.setText(df1.format(c.getTime()));
            }
        });
        return  view1;
    }


}
