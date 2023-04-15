package com.example.docenciamap2023.Modelo;

import java.io.Serializable;

public class Cursos implements Serializable {
    private String cursoNombre;
    private String cursoEstado;
    private String cursoInstitucion;
    private String cursoModalidad;
    private String cursoDuracion;
    private String cursoUrl;
    private String cursoStatus;
    private int cursoImagenEstado;


    public Cursos(String cursoNombre,String cursoEstado, String cursoInstitucion,String cursoModalidad,String cursoDuracion,String cursoUrl,String cursoStatus, int cursoImagenEstado){
        this.cursoNombre = cursoNombre;
        this.cursoEstado = cursoEstado;
        this.cursoInstitucion = cursoInstitucion;
        this.cursoModalidad = cursoModalidad;
        this.cursoDuracion = cursoDuracion;
        this.cursoUrl = cursoUrl;
        this.cursoStatus = cursoStatus;
        this.cursoImagenEstado = cursoImagenEstado;
    }

    public String getCursoNombre() {
        return cursoNombre;
    }

    public void setCursoNombre(String cursoNombre) {
        this.cursoNombre = cursoNombre;
    }

    public int getCursoImagenEstado() {
        return cursoImagenEstado;
    }

    public void setCursoImagenEstado(int cursoImagenEstado) {
        this.cursoImagenEstado = cursoImagenEstado;
    }

    public String getCursoEstado() {
        return cursoEstado;
    }

    public void setCursoEstado(String cursoEstado) {
        this.cursoEstado = cursoEstado;
    }

    public String getCursoInstitucion() {
        return cursoInstitucion;
    }

    public void setCursoInstitucion(String cursoInstitucion) {
        this.cursoInstitucion = cursoInstitucion;
    }

    public String getCursoModalidad() {
        return cursoModalidad;
    }

    public void setCursoModalidad(String cursoModalidad) {
        this.cursoModalidad = cursoModalidad;
    }

    public String getCursoDuracion() {
        return cursoDuracion;
    }

    public void setCursoDuracion(String cursoDuracion) {
        this.cursoDuracion = cursoDuracion;
    }

    public String getCursoUrl() {
        return cursoUrl;
    }

    public void setCursoUrl(String cursoUrl) {
        this.cursoUrl = cursoUrl;
    }

    public String getCursoStatus() {
        return cursoStatus;
    }

    public void setCursoStatus(String cursoStatus) {
        this.cursoStatus = cursoStatus;
    }
}
