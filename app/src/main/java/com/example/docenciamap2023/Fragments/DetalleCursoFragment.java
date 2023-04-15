package com.example.docenciamap2023.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.docenciamap2023.Modelo.Cursos;
import com.example.docenciamap2023.R;

public class DetalleCursoFragment extends Fragment {
    TextView nombre;
    ImageView imagen;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalle_curso_fragment,container,false);
        nombre = view.findViewById(R.id.nombre_detalle);
        imagen = view.findViewById(R.id.imagen_detalleid);
        //Crear bundle para recibir el objeto enviado por parametro.
        Bundle objetoCursos = getArguments();
        Cursos curso = null;;
        //validacion para verificar si existen argumentos para mostrar
        if(objetoCursos !=null){
            curso = (Cursos) objetoCursos.getSerializable("objeto");
            imagen.setImageResource(curso.getCursoImagenEstado());
            nombre.setText(curso.getCursoNombre());
        }
        return view;
    }
}