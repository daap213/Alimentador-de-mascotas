package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.entidades.AdminSQLiteOpenHelper;
import com.example.proyecto.utilidades.Utilidades;

public class UsuarioAc extends AppCompatActivity {

    private EditText nombreUsuario,contraseña;
    private TextView bienvenida;
    AdminSQLiteOpenHelper admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_ac);
       admin =new  AdminSQLiteOpenHelper(this,"PST_G6",null,1);

       //please hacer q se vea el nombre d usuario de la bienvenida
       bienvenida= (TextView) findViewById(R.id.mensajeBienvenida);
       bienvenida.setText("Bienvenid@");
       ////////////////////////////////////////////////
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registroMascota:
                Intent i = new Intent(this, RegistroMascota.class);
                startActivity(i);
                break;
            case R.id.actualizarDatos:
                actualizarUsuario();
                break;
            case R.id.eliminarUsuario:
                eliminarUsuario();
                break;
        }
    }

    private void eliminarUsuario() {
        SQLiteDatabase db=admin.getWritableDatabase();
        String[] parametros={nombreUsuario.getText().toString()};

        db.delete(Utilidades.TABLA_USUARIO,Utilidades.KEY_USUARIO_NOMBRE+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se Eliminó el usuario",Toast.LENGTH_LONG).show();
        nombreUsuario.setText("");
        limpiar();
        db.close();
    }

    private void actualizarUsuario() { //AQUI pueden agregar mas campos del usuario
        SQLiteDatabase db=admin.getWritableDatabase();
        String[] parametros={nombreUsuario.getText().toString()};
        ContentValues values=new ContentValues();
        values.put(Utilidades.KEY_USUARIO_NOMBRE,nombreUsuario.getText().toString());
        values.put(Utilidades.KEY_USUARIO_CONTRASEÑA,contraseña.getText().toString());

        db.update(Utilidades.TABLA_USUARIO,values,Utilidades.KEY_USUARIO_NOMBRE+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Se actualizaron los datos del usuario con éxito",Toast.LENGTH_LONG).show();
        db.close();
    }


    private void limpiar() {
        nombreUsuario.setText("");
        contraseña.setText("");
    }

}