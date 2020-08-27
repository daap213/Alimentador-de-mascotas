package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.proyecto.entidades.AdminSQLiteOpenHelper;
import com.example.proyecto.entidades.*;

public class Registro extends AppCompatActivity {
    private EditText nombre,contra,correo,ubicacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre=(EditText)findViewById(R.id.nombre);
        contra=(EditText)findViewById(R.id.contra);
        correo=(EditText)findViewById(R.id.correo);
        ubicacion=(EditText)findViewById(R.id.ubicacion);
    }

    public void Registrar(View v) {
        AdminSQLiteOpenHelper admin = AdminSQLiteOpenHelper.getInstance(this); // usar metodo singleton
      /*AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, //alternartiva al metodo singleton
                "administracion", null, 1);*/
        SQLiteDatabase bd = admin.getWritableDatabase();
        Usuario user= new Usuario();
        user.setNombre(nombre.getText().toString()) ;
        user.setContraseña( contra.getText().toString());
        user.setCorreo(correo.getText().toString());
        user.setUbicacion(ubicacion.getText().toString());

        admin.agregarOActualizarUsuario( user);

     /*   Toast.makeText(this, "Se Creo un Nuevo Usuario",
                Toast.LENGTH_SHORT).show();*/
    }


    public void Volver(View v) {
        finish();
    }
}