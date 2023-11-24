package com.miempresa.sesion10;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragmento1 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragmento1, container, false);

        // Configurar acciones o eventos en los elementos del fragmento
        Button btnCambiarFragmento = rootView.findViewById(R.id.btnCambiarFragmento);
        btnCambiarFragmento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en el botón dentro del Fragmento1
                // Cambiar al Fragmento2

                Fragmento2 fragmento2 = new Fragmento2();

                // Obtener el FragmentManager de la actividad principal
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                // Iniciar una transacción de fragmentos
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Reemplazar el fragmento actual por el Fragmento2 en el contenedor adecuado
                fragmentTransaction.replace(R.id.contenedorFragmento2, fragmento2);

                // Añadir la transacción a la pila de retroceso (opcional)
                fragmentTransaction.addToBackStack(null);

                // Confirmar la transacción
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }
}
