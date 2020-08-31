package com.example.proyecto;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.entidades.AdminSQLiteOpenHelper;
import com.example.proyecto.utilidades.Utilidades;

public class ActualizarUsuario extends AppCompatActivity {
    private EditText nombreUsuario,nombres, correo, ubicacion,  telefono, contraseña;
    AdminSQLiteOpenHelper admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombreUsuario=(EditText)findViewById(R.id.nombreUsuario);
        nombres=(EditText)findViewById(R.id.nombre);
        contraseña=(EditText)findViewById(R.id.contraseña);
        correo=(EditText)findViewById(R.id.correo);
        ubicacion=(EditText)findViewById(R.id.ubicacion);
        telefono= (EditText)findViewById(R.id.telefono);

    }
    public void Actualizar(View v) {
      //  actualizarUsuario();
    }
    public void Volver(View v) {
        finish();
    }

   /* private void actualizarUsuario() { //AQUI pueden agregar mas campos del usuario

        SQLiteDatabase db=admin.getWritableDatabase();

        String[] parametros={nombreUsuario.getText().toString(),nombres.getText().toString(),
                contraseña.getText().toString(),correo.getText().toString(),telefono.getText().toString(),
                ubicacion.getText().toString()};
        ContentValues values=new ContentValues();
        values.put(Utilidades.KEY_USUARIO_NOMBREUSUARIO,nombreUsuario);
        values.put(Utilidades.KEY_USUARIO_CONTRASEÑA,contraseña.getText().toString());

        db.update(Utilidades.TABLA_USUARIO,values,Utilidades.KEY_USUARIO_NOMBREUSUARIO+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Se actualizaron los datos del usuario con éxito",Toast.LENGTH_LONG).show();
        db.close();
    }*/
}
