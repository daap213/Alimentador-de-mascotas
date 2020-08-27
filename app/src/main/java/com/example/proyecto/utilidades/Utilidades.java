package com.example.proyecto.utilidades;

public class Utilidades {

    //NOMBRES DE LAS TABLAS
    public static final String TABLA_USUARIO= "usuario";
    public static final String TABLA_MASCOTA= "mascota";

    //COLUMNAS DE LA TABLA DE USUARIO
    public static final String KEY_USUARIO_ID= "id";
    public static final String KEY_USUARIO_NOMBRE= "nombre";
    public static final String KEY_USUARIO_GENERO= "genero";
    public static final String KEY_USUARIO_CONTRASEÑA= "contraseña";
    public static final String KEY_USUARIO_CORREO= "correo";
    public static final String KEY_USUARIO_UBICACION="ubicacion";
    private static final String KEY_USUARIO_TELEFONO="telefono";


    //COLUMNAS DE LA TABLA DE MASCOTA
    public static final String KEY_MASCOTA_ID= "id";
    public static final String KEY_MASCOTA_ID_USUARIO= "usuarioId";//llave foranea
    public static final String KEY_MASCOTA_NOMBRE= "mascotaNombre";
    public static final String KEY_RAZA= "raza";
    public static final String KEY_MASCOTA_EDAD= "edadMascota";
    public static final String KEY_TAMAÑO="tamaño";

    //CREACION DE LAS TABLAS
    public static final String CREAR_TABLA_USUARIO="CREATE TABLE " +
            ""+TABLA_USUARIO+" ("+KEY_USUARIO_ID + " "+
            " INTEGER, "+
            KEY_USUARIO_NOMBRE + " VARCHAR," +
            KEY_USUARIO_GENERO + " VARCHAR," +
            KEY_USUARIO_CONTRASEÑA + " VARCHAR," +
            KEY_USUARIO_CORREO + " VARCHAR," +
            KEY_USUARIO_TELEFONO + " VARCHAR,"+
            KEY_USUARIO_UBICACION +" VARCHAR "+
            ")";

    public static final String CREAR_TABLA_MASCOTA="CREATE TABLE " +
            ""+TABLA_MASCOTA+" ("+KEY_MASCOTA_ID + " "+
            " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            KEY_MASCOTA_ID_USUARIO+ " INTEGER,"+
            KEY_MASCOTA_NOMBRE + " VARCHAR," +
            KEY_MASCOTA_EDAD + " VARCHAR," +
            KEY_RAZA + " VARCHAR," +
            ")";

}
