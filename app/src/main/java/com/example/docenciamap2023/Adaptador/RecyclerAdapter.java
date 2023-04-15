package com.example.docenciamap2023.Adaptador;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.docenciamap2023.Modelo.Cursos;
import com.example.docenciamap2023.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements View.OnClickListener{
        private List<Cursos> items;
    LayoutInflater inflater;
    ArrayList<Cursos> model;
    private View.OnClickListener listener;
    ArrayList<Cursos>  creaCursos;


    public RecyclerAdapter(Context context, ArrayList<Cursos> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
        this.creaCursos = new ArrayList<>();
        creaCursos.addAll(model);
    }


    ////1


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.disponibles_lista_curso, parent, false);
        view.setOnClickListener(this);
        return new RecyclerAdapter.ViewHolder(view);
    }
    ////2
    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    ////3
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        String cursoNombre = model.get(position).getCursoNombre();
        int cursoImagenEstado = model.get(position).getCursoImagenEstado();
        String cursoDuracion = model.get(position).getCursoDuracion();
        String curoUrl = model.get(position).getCursoUrl();
        String cursoStatus = model.get(position).getCursoStatus();
        String cursoEstado = model.get(position).getCursoEstado();
        String cursoModalidad = model.get(position).getCursoModalidad();


        holder.cursoNombre.setText(cursoNombre);
        holder.cursoEstado.setText(cursoEstado);
        holder.cursoImagenEstado.setImageResource(cursoImagenEstado);
        holder.curoUrl.setText(curoUrl);
        holder.cursoStatus.setText(cursoStatus);
        holder.cursoDuracion.setText(cursoDuracion);
        holder.cursoModalidad.setText(cursoModalidad);


    }
    ////4
    @Override
    public int getItemCount() {


        return model.size();
    }


    public void filter(final String strBusca){
        if(strBusca.length()==0){
            model.clear();
            model.addAll(creaCursos);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            model.clear();
            ArrayList<Cursos> collect = (ArrayList<Cursos>) creaCursos.stream()
                    .filter(i -> i.getCursoNombre().toLowerCase().contains(strBusca))
                    .collect(Collectors.toList());
            model.addAll(collect);
        }else {
            model.clear();
            for(Cursos i : creaCursos){
                if(i.getCursoNombre().toLowerCase().contains(strBusca)){
                    model.add(i);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    ////
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView cursoNombre,cursoDuracion,curoUrl,cursoStatus,cursoEstado,cursoModalidad;
        ImageView cursoImagenEstado;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            cursoNombre = itemView.findViewById(R.id.cursoNombre);
            cursoEstado = itemView.findViewById(R.id.cursoEstado);
            cursoImagenEstado = itemView.findViewById(R.id.cursoImagenEstado);
            cursoDuracion = itemView.findViewById(R.id.cursoDuracion);
            cursoModalidad = itemView.findViewById(R.id.cursoModalidad);
            curoUrl = itemView.findViewById(R.id.curoUrl);
            cursoStatus = itemView.findViewById(R.id.cursoStatus);
        }

    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            model.clear();
            model.addAll(creaCursos);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Cursos> collecion = model.stream()
                        .filter(
                                i -> i.getCursoNombre().toLowerCase().contains(txtBuscar.toLowerCase())||
                                         i.getCursoEstado().toLowerCase().contains(txtBuscar.toLowerCase())||
                                        i.getCursoInstitucion().toLowerCase().contains(txtBuscar.toLowerCase())||
                                        i.getCursoModalidad().toLowerCase().contains(txtBuscar.toLowerCase())
                                )//Fin filter
                        .collect(Collectors.toList());
                model.clear();
                model.addAll(collecion);
            } else {
                for (Cursos c : creaCursos) {
                    if (c.getCursoNombre().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        model.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
}
