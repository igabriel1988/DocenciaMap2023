package com.example.docenciamap2023.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.docenciamap2023.Adaptador.InicioAdapter;
import com.example.docenciamap2023.Interfaces.iComunicaFragments;
import com.example.docenciamap2023.Modelo.Cursos;
import com.example.docenciamap2023.R;

import java.util.ArrayList;


public class MainFragment extends Fragment implements SearchView.OnQueryTextListener {
    InicioAdapter adapterCursos;
    RecyclerView recyclerViewCursos;
    ArrayList<Cursos> listaCursos;

    EditText txtnombre;
    LinearLayout colorCurso;

    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;
    SearchView txtBuscar;

    @SuppressLint({"ResourceAsColor", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment,container,false);

        txtBuscar = view.findViewById(R.id.buscaCurso);

        colorCurso = view.findViewById(R.id.pendientesColor);



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
        listaCursos.add(new Cursos( "DIMENSIÓN DEL PERFIL DOCENTE",
                "CAMPECHE",
                "INSTITUCION1",
                "20 HORAS",
                "HOME OFFICE",
                "WWW.GOOGLE.COM",
                "PENDIENTE",
                R.drawable.campeche)
        );
        listaCursos.add(new Cursos( "DIDÁCTICA DE ELEMENTOS DE EJECUCIÓN",
                "CELAYA",
                "INSTITUCION1",
                "PRESENCIAL",
                "20 HORAS",
                "WWW.GOOGLE.COM",
                "PENDIENTE",
                R.drawable.celaya)
        ) ;
        listaCursos.add(new Cursos( "EL ESTILO DE ESCRITURA APA PARA LA PUBLICACIÓN ACADÉMICA",
                "GUADALAJARA",
                "INSTITUCION2",
                "HOME OFFICE",
                "120 HORAS",
                "WWW.GOOGLE.COM",
                "PENDIENTE",
                R.drawable.guadalajara)
        ) ;
        listaCursos.add(new Cursos( "EL PENSAMIENTO MATEMÁTICO & LA SOLUCIÓN DE PROBLEMAS OLÍMPICOS",
                "GUANAJUATO",
                "INSTITUCION20",
                "HOME OFFICE",
                "120 HORAS",
                "WWW.GOOGLE.COM",
                "PENDIENTE",
                R.drawable.guanajuato)
        ) ;
        listaCursos.add(new Cursos( "LA PEDAGOGÍA Y LOS PRINCIPIOS DE LA NUEVA ESCUELA MEXICANA.",
                "MERIDA",
                "INSTITUCION30",
                "PRESENCIAL",
                "120 HORAS",
                "WWW.GOOGLE.COM",
                "PENDIENTE",
                R.drawable.merida)
        ) ;
        ////
        listaCursos.add(new Cursos( "METODOLOGÍAS ACTIVAS EN EL PROCESO DE ENSEÑANZA EN EDUCACIÓN BÁSICA",
                "NAYARIT",
                "INSTITUCION30",
                "PRESENCIAL",
                "120 HORAS",
                "WWW.GOOGLE.COM",
                "PENDIENTE",
                R.drawable.nayarit)
        ) ;
        listaCursos.add(new Cursos( "TEORÍAS DEL APRENDIZAJE Y EDUCABILIDAD",
                "OAXACA",
                "INSTITUCION30",
                "PRESENCIAL",
                "120 HORAS",
                "WWW.GOOGLE.COM",
                "PENDIENTE",
                R.drawable.oaxaca)
        ) ;
        listaCursos.add(new Cursos( "CLUB DE LECTURA",
                "TAMAULIPAS",
                "INSTITUCION30",
                "PRESENCIAL",
                "120 HORAS",
                "WWW.GOOGLE.COM",
                "PENDIENTE",
                R.drawable.tamaulipas)
        ) ;
        listaCursos.add(new Cursos( "LENGUA DE SEÑAS MEXICANAS NIVEL AVANZADO",
                "TUXTLA",
                "INSTITUCION30",
                "PRESENCIAL",
                "120 HORAS",
                "WWW.GOOGLE.COM",
                "PENDIENTE",
                R.drawable.tuxtla)
        ) ;
        listaCursos.add(new Cursos( "TALLER DE ARTES PLÁSTICAS",
                "VERACRUZ",
                "INSTITUCION30",
                "PRESENCIAL",
                "120 HORAS",
                "WWW.GOOGLE.COM",
                "PENDIENTE",
                R.drawable.veracruz)
        ) ;


    }
    private void mostrarData(){
        recyclerViewCursos.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterCursos = new InicioAdapter(getContext(), listaCursos);
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
