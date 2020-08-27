package com.example.proyecto.entidades;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.proyecto.UsuarioAc;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    //TODAS LAS VARIABLES ESTATICAS
    private static AdminSQLiteOpenHelper sInstance; // instancia para evitar perdidas de memoria
    private static final int DATABASE_VERSION=1;

    //NOMBRE DE LA BASE DE DATOS
    private static final String DATABASE_NOMBRE= "PST_GRUPO6";

    //NOMBRES DE LAS TABLAS
    private static final String USUARIO= "usuario";
    private static final String MASCOTA= "mascota";

    //COLUMNAS DE LA TABLA DE USUARIO
    private static final String KEY_USUARIO_ID= "id";
    private static final String KEY_USUARIO_NOMBRE= "nombre";
    private static final String KEY_GENERO= "genero";
    private static final String KEY_FN="Fecha de nacimiento";
    private static final String KEY_CONTRASEÑA= "contraseña";
    private static final String KEY_CORREO= "correo";
    private static final String KEY_UBICACION="ubicacion";

    //COLUMNAS DE LA TABLA DE MASCOTA
    private static final String KEY_MASCOTA_ID= "id";
    private static final String KEY_MASCOTA_ID_FK= "usuarioId";//llave foranea
    private static final String KEY_MASCOTA_NOMBRE= "mascotaNombre";
    private static final String KEY_RAZA= "raza";
    private static final String KEY_EDAD= "edad";
    private static final String KEY_TAMAÑO="tamaño";

   //METODO PARA OBTENER LA INSTANCIA
    public static synchronized AdminSQLiteOpenHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AdminSQLiteOpenHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    /* El constructor debe ser privado para evitar la instanciación directa.
       Hacer una llamada al método estático "getInstance ()" en su lugar.
    */
    private AdminSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NOMBRE, null,DATABASE_VERSION );
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    //CREACION DE LAS TABLAS
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MASCOTA_TABLE = "CREATE TABLE "+ MASCOTA+
                "(" +
                KEY_MASCOTA_ID + " INTEGER PRIMARY KEY," + // Define la primary key
                KEY_MASCOTA_ID_FK + " INTEGER REFERENCES " + USUARIO + "," + //llave foranea
                KEY_EDAD + "INTEGER " +
                KEY_MASCOTA_NOMBRE + "VARCHAR"  +
                KEY_RAZA + "VARCHAR"  +
                KEY_TAMAÑO +  "VARCHAR" +
                ")";

        String CREATE_USUARIO_TABLE = "CREATE TABLE " + USUARIO +
                "(" +
                KEY_USUARIO_ID + " INTEGER PRIMARY KEY,"+
                KEY_USUARIO_NOMBRE+ "VARCHAR "+
                KEY_GENERO+ "VARCHAR" +
                KEY_FN+ "DATE"+
                KEY_CONTRASEÑA+ "VARCHAR "+
                KEY_CORREO+ "VARCHAR  "+
                KEY_UBICACION +"VARCHAR "+
                ")";

        db.execSQL(CREATE_MASCOTA_TABLE);
        db.execSQL(CREATE_USUARIO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int viejaVersion, int nuevaVersion) {
        if (viejaVersion != nuevaVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + MASCOTA);
            db.execSQL("DROP TABLE IF EXISTS " + USUARIO);
            onCreate(db);
        }
    }

    // METODO QUE AGREGA UNA MASCOTA A LA BASE DE DATOS
    public void agregarMascota(Mascota mascota) {
        // Crear y/o abrir la base de datos para la escritura
        SQLiteDatabase bd = getWritableDatabase();

        bd.beginTransaction();
        try {
            long usuarioId = agregarOActualizarUsuario(mascota.usuario);
            ContentValues values = new ContentValues();
            values.put(KEY_MASCOTA_ID_FK, usuarioId);
            values.put(KEY_MASCOTA_NOMBRE, mascota.nombre);

            bd.insertOrThrow(MASCOTA, null, values);
            bd.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error al ingresar dato");
        } finally {
            bd.endTransaction();
        }
    }

    public long agregarOActualizarUsuario(Usuario usuario) {
        SQLiteDatabase bd = getWritableDatabase();
        long usuarioId = -1;

        bd.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_USUARIO_ID, usuario.getId());
            values.put(KEY_USUARIO_NOMBRE, usuario.getNombre());

            // Primero intenta actualizar el usuario en caso de que el usuario ya exista en la base de datos
            // Esto asume que los nombres de usuario son únicos
            int rows = bd.update(USUARIO, values, KEY_USUARIO_NOMBRE + "= ?", new String[]{usuario.getNombre()});

            // Comprueba si la actualización se realizó correctamente
            if (rows == 1) {
                //Obtener la clave principal del usuario que acabamos de actualizar
                String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                        KEY_USUARIO_ID, USUARIO, KEY_USUARIO_NOMBRE);
                Cursor cursor = bd.rawQuery(usersSelectQuery, new String[]{String.valueOf(usuario.getNombre())});
                try {
                    if (cursor.moveToFirst()) {
                        usuarioId = cursor.getInt(0);
                        bd.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
            // el usuario con este nombre de usuario aún no existía, así que inserte un nuevo usuario
                usuarioId = bd.insertOrThrow(USUARIO, null, values);
                bd.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.d(TAG, "Error al agregar el usuario");
        } finally {
            bd.endTransaction();
        }
        return usuarioId;
    }

    //ALTERNATIVA A CONSULTAR REGISTROS NO SE SI FUNCIONE
    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        String USUARIO_SELECT_QUERY =
                String.format("SELECT * FROM %s LEFT OUTER JOIN %s ON %s.%s = %s.%s",
                        MASCOTA,
                        USUARIO,
                        MASCOTA, KEY_MASCOTA_ID_FK,
                        USUARIO, KEY_USUARIO_NOMBRE,
                        USUARIO, KEY_CONTRASEÑA);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(USUARIO_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Usuario newUser = new Usuario();
                    newUser.setNombre(cursor.getString(cursor.getColumnIndex(KEY_USUARIO_NOMBRE)))  ;
                    newUser.setContraseña(cursor.getString(cursor.getColumnIndex(KEY_CONTRASEÑA)));
                    usuarios.add(newUser);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error mientras intentaba obtener dato del usuario");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return usuarios;
    }

    //ELIMINAR REGISTROS
    public void deleteAllPostsAndUsers() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            // Order of deletions is important when foreign key relationships exist.
            db.delete(MASCOTA, null, null);
            db.delete(USUARIO, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error mientras intetaba eliminar todo el registro del usuario");
        } finally {
            db.endTransaction();
        }
    }




}
