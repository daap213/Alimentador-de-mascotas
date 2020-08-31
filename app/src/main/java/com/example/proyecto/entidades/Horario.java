package com.example.proyecto.entidades;

import android.media.ToneGenerator;

import java.io.Serializable;
import java.time.DateTimeException;

public class Horario implements Serializable {
    private Integer id;
    private String hora; // no se si ese tipo de variable sea el q se necesita o el DATE de SQL
    private String descripcion;
    private Integer iDUsuario;
    private ToneGenerator Tono;

    public Horario(){
    }

    public Horario(Integer id, String hora, String descripcion, Integer iDUsuario, ToneGenerator tono) {
        this.id = id;
        this.hora = hora;
        this.descripcion = descripcion;
        this.iDUsuario = iDUsuario;
        Tono = tono;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
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
