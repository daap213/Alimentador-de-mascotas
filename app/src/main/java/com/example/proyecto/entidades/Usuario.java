package com.example.proyecto.entidades;
import java.io.Serializable;

public class Usuario implements Serializable {

    private Integer id;
    private String nombreUsuario;
    private String nombres;
    private String contraseña;
    private String correo;
    private String ubicacion;
    private String telefono;

    public Usuario() {
    }

    public Usuario(Integer id, String nombreUsuario, String nombres, String contraseña, String correo, String ubicacion, String telefono) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.nombres = nombres;
        this.contraseña = contraseña;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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
