package com.example.proyecto.entidades;

import android.media.ToneGenerator;

import java.io.Serializable;
import java.time.DateTimeException;

public class Horario implements Serializable {
    private DateTimeException hora; // no se si ese tipo de variable sea el q se necesita o el DATE de SQL
    private String descripcion;
    private Integer iDUsuario;
    private ToneGenerator Tono;

    public Horario(){
    }

    public Horario(DateTimeException hora, String descripcion, Integer iDUsuario, ToneGenerator tono) {
        this.hora = hora;
        this.descripcion = descripcion;
        this.iDUsuario = iDUsuario;
        Tono = tono;
    }

    public DateTimeException getHora() {
        return hora;
    }

    public void setHora(DateTimeException hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getiDUsuario() {
        return iDUsuario;
    }

    public void setiDUsuario(Integer iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public ToneGenerator getTono() {
        return Tono;
    }

    public void setTono(ToneGenerator tono) {
        Tono = tono;
    }
}
