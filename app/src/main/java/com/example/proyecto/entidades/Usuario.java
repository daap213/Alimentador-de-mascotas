package com.example.proyecto.entidades;

public class Usuario {
    private Integer id;
    private String nombre;
    private String correo;
    private String ubicacion;
    private String telefono;


    public Usuario(Integer id, String nombre, String correo, String ubicacion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
