package com.example.proyecto.entidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.proyecto.utilidades.*;


public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


   /* public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }*/

    //LAS TABLAS SE CREARON DENTRO DEL PAQUETE UTILIDADES
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Utilidades.CREAR_TABLA_MASCOTA);
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_MASCOTA);
        onCreate(db);
    }

   /* // METODO QUE AGREGA UNA MASCOTA A LA BASE DE DATOS
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
    }*/

}
