package com.example.docenciamap2023.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.docenciamap2023.Adaptador.RecyclerAdapter;
import com.example.docenciamap2023.Modelo.Cursos;
import com.example.docenciamap2023.R;
import com.example.docenciamap2023.Interfaces.iComunicaFragments;

import java.util.ArrayList;



public class DisponiblesFragment extends Fragment implements SearchView.OnQueryTextListener{

    RecyclerAdapter adapterCursos;
    RecyclerView recyclerViewCursos;
    ArrayList<Cursos> listaCursos;

    EditText txtnombre;

    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;
    SearchView txtBuscar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_disponibles,container,false);

        txtBuscar = view.findViewById(R.id.buscaCurso);
        txtBuscar.setQueryHint("CURSO|ESTADO|MODALIDAD|INSTITUCION");
        txtBuscar.setSubmitButtonEnabled(true);
        txtBuscar.clearFocus();

        txtBuscar.setOnQueryTextListener(this);
        recyclerViewCursos = view.findViewById(R.id.rvLista);

        listaCursos = new ArrayList<>();
        cargarLista();
        mostrarData();

        return view;
    }

    /////

    public void cargarLista(){
        listaCursos.add(new Cursos( "ACTIVIDADES EXTRACLASE",
                                        "BAJA CALIFORNIA",
                            "INSTITUCION1",
                                        "20 HORAS",
                                        "HOME OFFICE",
                                        "WWW.GOOGLE.COM",
                                        "DISPONIBLE",
                        R.drawable.baja_california)
                    );
        listaCursos.add(new Cursos( "ADAPTACIÓN Y DISEÑO DE SITUACIONES",
                "CDMX",
                "INSTITUCION1",
                "PRESENCIAL",
                "19 HORAS",
                "WWW.GOOGLE.COM",
                "DISPONIBLE",
                R.drawable.cdmx)
            ) ;
        listaCursos.add(new Cursos( "PROCESO DE ADMINISIÓN LA DOCENCIA  EN EDUCACIÓN BÁSICA",
                "QUERETA ROO",
                "INSTITUCION2",
                "HOME OFFICE",
                "32 HORAS",
                "WWW.GOOGLE.COM",
                "DISPONIBLE",
                R.drawable.queretaro)
                ) ;
        listaCursos.add(new Cursos( "DOCENCIA PRINCIPIOS",
                "SONORA",
                "INSTITUCION20",
                "HOME OFFICE",
                "10 HORAS",
                "WWW.GOOGLE.COM",
                "DISPONIBLE",
                R.drawable.sonota)
        ) ;
        listaCursos.add(new Cursos( "PREPARACIÓN DE CLASES",
                "YUCATAN",
                "INSTITUCION30",
                "PRESENCIAL",
                "20 HORAS",
                "WWW.GOOGLE.COM",
                "DISPONIBLE",
                R.drawable.yucatan)
        ) ;


    }
    private void mostrarData(){
        recyclerViewCursos.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterCursos = new RecyclerAdapter(getContext(), listaCursos);
        recyclerViewCursos.setAdapter(adapterCursos);

        adapterCursos.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Seleccionó: "+listaCursos.get(recyclerViewCursos.getChildAdapterPosition(view)).getCursoNombre(), Toast.LENGTH_SHORT).show();
                //enviar mediante la interface el objeto seleccionado al detalle
                //se envia el objeto completo
                //se utiliza la interface como puente para enviar el objeto seleccionado
                    interfaceComunicaFragments.enviarCurso(listaCursos.get(recyclerViewCursos.getChildAdapterPosition(view)));
                //luego en el mainactivity se hace la implementacion de la interface para implementar el metodo enviarcurso
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //esto es necesario para establecer la comunicacion entre la lista y el detalle
        //si el contexto que le esta llegando es una instancia de un activity:
        if(context instanceof Activity){
            //voy a decirle a mi actividad que sea igual a dicho contesto. castin correspondiente:
            this.actividad= (Activity) context;
            ////que la interface icomunicafragments sea igual ese contexto de la actividad:
            interfaceComunicaFragments= (iComunicaFragments) this.actividad;
            //esto es necesario para establecer la comunicacion entre la lista y el detalle
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapterCursos.filtrado(s);
        return false;
    }
}