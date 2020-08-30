package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.proyecto.entidades.AdminSQLiteOpenHelper;
import com.example.proyecto.utilidades.Utilidades;
import android.widget.Toast;

public class RegistroUsuario extends AppCompatActivity {
    private EditText nombre, correo, ubicacion,  telefono, contraseña;

    AdminSQLiteOpenHelper admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre=(EditText)findViewById(R.id.nombre);
        contraseña=(EditText)findViewById(R.id.contraseña);
        correo=(EditText)findViewById(R.id.correo);
        ubicacion=(EditText)findViewById(R.id.ubicacion);
        telefono= (EditText)findViewById(R.id.telefono);
        }

    public void Registrar(View v) {
       registrarUsuarios();
     //   registrarUsuariosSql();

    }
    //METODO ALTERNATIVO A REGISTRAR USUARIOS
 /*   private void registrarUsuariosSql() {
        AdminSQLiteOpenHelper admin=new  AdminSQLiteOpenHelper(this,"PST_G6",null,1);

        SQLiteDatabase db=admin.getWritableDatabase();

        String insert="INSERT INTO "+Utilidades.TABLA_USUARIO
                +" ( " +Utilidades.KEY_USUARIO_NOMBRE+"," +
                ""+Utilidades.KEY_USUARIO_CONTRASEÑA+"," +
                ""+Utilidades.KEY_USUARIO_CORREO+"," +
                ""+Utilidades.KEY_USUARIO_UBICACION+","+
                ""+Utilidades.KEY_USUARIO_TELEFONO+
                ") " +
                " VALUES ("+nombre.getText().toString()+"','"
                +contraseña.getText().toString()+"','"
                +correo.getText().toString()+"','"
                +telefono.getText().toString()+"','"
                +ubicacion.getText().toString()+"')";
        db.execSQL(insert);
        db.close();
    }*/

//METODO QUE REGISTRA Y VALIDA LOS USUARIOS
    private void registrarUsuarios() {
        AdminSQLiteOpenHelper admin= new  AdminSQLiteOpenHelper(this,"PST_G6",null,1);
        SQLiteDatabase db= admin.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidades.KEY_USUARIO_NOMBRE,nombre.getText().toString());
        values.put(Utilidades.KEY_USUARIO_CONTRASEÑA,contraseña.getText().toString());
        values.put(Utilidades.KEY_USUARIO_CORREO,correo.getText().toString());
        values.put(Utilidades.KEY_USUARIO_TELEFONO,telefono.getText().toString());
        values.put(Utilidades.KEY_USUARIO_UBICACION,ubicacion.getText().toString());


        Long idResultante=db.insert(Utilidades.TABLA_USUARIO,Utilidades.KEY_USUARIO_ID,values);

        Toast.makeText(getApplicationContext(),"Registro exitoso de "+ nombre.getText().toString()+"\n Id de Usuario:"+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
        limpiar();
    }

    // VUELVE AL LOGIN
    public void Volver(View v) {
        finish();
    }

    private void limpiar() {
        nombre.setText("");
        contraseña.setText("");
        correo.setText("");
        ubicacion.setText("");
        telefono.setText("");
    }
}