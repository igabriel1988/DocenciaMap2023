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

    TextView modalidad;
    TextView duracion;
    TextView url;
    TextView estatus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalle_curso_fragment,container,false);
        nombre = view.findViewById(R.id.nombre_detalle);
        imagen = view.findViewById(R.id.imagen_detalleid);

        modalidad = view.findViewById(R.id.modalidad);
        duracion = view.findViewById(R.id.duracion);
        url = view.findViewById(R.id.url);
        estatus = view.findViewById(R.id.estatus);

        //Crear bundle para recibir el objeto enviado por parametro.
        Bundle objetoCursos = getArguments();
        Cursos curso = null;;
        //validacion para verificar si existen argumentos para mostrar
        if(objetoCursos !=null){
            curso = (Cursos) objetoCursos.getSerializable("objeto");
            imagen.setImageResource(curso.getCursoImagenEstado());
            nombre.setText(curso.getCursoNombre());

            modalidad.setText(curso.getCursoModalidad());
            duracion.setText(curso.getCursoDuracion());
            url.setText(curso.getCursoUrl());
            estatus.setText(curso.getCursoStatus());
        }
        return view;
    }
}