package com.example.proyecto.entidades;

import java.io.Serializable;

public class Mascota implements Serializable {

    private Integer idMascota;
    private Usuario dueño;
    private Integer idUsuario;
    private String nombreMascota;
    private String raza;
    private Integer edad;
    private String tamaño;

    public Mascota() {
    }

    public Mascota(Integer idMascota, Integer idUsuario, String nombreMascota, String raza, Integer edad, String tamaño) {
        this.idUsuario = idUsuario;
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.raza = raza;
        this.edad = edad;
        this.tamaño = tamaño;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }
}

