package com.miempresa.sesion10;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragmento2 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragmento2, container, false);

        // Configurar elementos del fragmento
        TextView textView = rootView.findViewById(R.id.textViewFragmento2);
        textView.setText("Fragmento 2");

        return rootView;
    }
}
