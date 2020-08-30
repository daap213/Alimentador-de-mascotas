package com.example.proyecto.utilidades;

public class Utilidades {

    //NOMBRES DE LAS TABLAS
    public static final String TABLA_USUARIO= "usuario";
    public static final String TABLA_MASCOTA= "mascota";
    public static final String TABLA_HORARIO= "horario";

    //COLUMNAS DE LA TABLA DE USUARIO
    public static final String KEY_USUARIO_ID= "id";
    public static final String KEY_USUARIO_NOMBRE= "nombre";
    public static final String KEY_USUARIO_CONTRASEÑA= "contraseña";
    public static final String KEY_USUARIO_CORREO= "correo";
    public static final String KEY_USUARIO_UBICACION="ubicacion";
    public static final String KEY_USUARIO_TELEFONO="telefono";


    //COLUMNAS DE LA TABLA DE MASCOTA
    public static final String KEY_MASCOTA_ID= "id";
    public static final String KEY_MASCOTA_ID_USUARIO= "usuarioId";//llave foranea
    public static final String KEY_MASCOTA_NOMBRE= "mascotaNombre";
    public static final String KEY_RAZA= "raza";
    public static final String KEY_MASCOTA_EDAD= "edadMascota";
    public static final String KEY_TAMAÑO="tamaño";

    //COLUMNAS DE LA TABLA DE HORARIO
    public static final String KEY_HORARIO_HORA= "hora";
    public static final String KEY_HORARIO_DESCRIPCION= "descripcion";
    public static final String KEY_HORARIO_IDUSUARIO= "idUsuario";
    public static final String KEY_HORARIO_TONO="tono";


    //CREACION DE LAS TABLAS
    public static final String CREAR_TABLA_USUARIO="CREATE TABLE " +
            ""+TABLA_USUARIO+" ("+KEY_USUARIO_ID + " "+
            "INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KEY_USUARIO_NOMBRE + " VARCHAR," +
            KEY_USUARIO_CONTRASEÑA + " VARCHAR," +
            KEY_USUARIO_CORREO + " VARCHAR," +
            KEY_USUARIO_TELEFONO + " VARCHAR,"+
            KEY_USUARIO_UBICACION + " VARCHAR)";

    public static final String CREAR_TABLA_MASCOTA="CREATE TABLE " +
            ""+TABLA_MASCOTA+" ("+KEY_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KEY_MASCOTA_ID_USUARIO+ " INTEGER REFERENCES "+ TABLA_USUARIO+","+
            KEY_MASCOTA_NOMBRE + " VARCHAR," +
            KEY_MASCOTA_EDAD + " VARCHAR," +
            KEY_RAZA + " VARCHAR," +
            KEY_TAMAÑO+ " INTEGER)";

    public static final String CREAR_TABLA_HORARIO="CREATE TABLE " +
            ""+TABLA_HORARIO+" ("+KEY_HORARIO_HORA+ " DATE,"+
            KEY_HORARIO_DESCRIPCION + " VARCHAR," +
            KEY_HORARIO_IDUSUARIO+ " INTEGER REFERENCES "+ TABLA_USUARIO+","+
            KEY_HORARIO_TONO+ " VARCHAR)";//NO SE SI ESTE ESTE BIEN, EL VARCHAR
}
