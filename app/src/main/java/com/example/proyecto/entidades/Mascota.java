package com.example.proyecto.entidades;

public class Mascota {
    public com.example.proyecto.Usuario usuario;
    public String nombre;
    public String raza;
    public int edad;
    public String tamaño;

    public Mascota() {
    }

    public Mascota(Usuario usuario, String nombre, String raza, int edad, String tamaño) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.tamaño = tamaño;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    @Override
    public String toString() {
        return "Macota{" +
                "usuario=" + usuario +
                ", nombre='" + nombre + '\'' +
                ", raza='" + raza + '\'' +
                ", edad=" + edad +
                ", tamaño='" + tamaño + '\'' +
                '}';
    }
}
