package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.entidades.AdminSQLiteOpenHelper;
import com.example.proyecto.entidades.Usuario;
import com.example.proyecto.utilidades.Utilidades;

public class Login extends AppCompatActivity {
    private EditText nombreUsuario,contraseña;
    AdminSQLiteOpenHelper admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        admin = new AdminSQLiteOpenHelper(this,
                "PST_G6", null, 1);

        nombreUsuario=(EditText)findViewById(R.id.nom);
        contraseña=(EditText)findViewById(R.id.contr);



    }

    public void Ingresar(View v) {
        validarUsuarioYContraseña( nombreUsuario.getText().toString(),contraseña.getText().toString());
    }

    //METODO QUE CONSULTA SI EL USUARIO YA EXISTE EN LA BASE DE DATOS
   /* public boolean validarUsuario(String usuario){
        SQLiteDatabase db=admin.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM Utilidades.TABLA_USUARIO where Utilidades.KEY_USUARIO_NOMBRE=?" , new String[]{usuario});

        if(cursor.getCount()>0){
            Toast.makeText(getApplicationContext(),"El usuario o contraseña no existen", Toast.LENGTH_SHORT).show();

        }else {
            Intent i = new Intent(this, UsuarioAc.class);
            i.putExtra("nombre", nombreUsuario.getText().toString());
            startActivity(i);
        }*/




    public void validarUsuarioYContraseña(String usuario, String contraseña){
        SQLiteDatabase db=admin.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM Utilidades.TABLA_USUARIO where Utilidades.KEY_USUARIO_NOMBRE=? and Utilidades.KEY_USUARIO_CONTRASEÑA=?" , new String[]{usuario,contraseña});

        if(cursor.getCount()>0){
            Intent i = new Intent(this, UsuarioAc.class);
            i.putExtra("nombre", nombreUsuario.getText().toString());
            startActivity(i);
        }else
            Toast.makeText(getApplicationContext(),"El usuario o contraseña no existen", Toast.LENGTH_SHORT).show();
            limpiar();


    }
    //METODO QUE LIMPIA LOS CAMPOS DE ENTRADA DE TEXTO DEL LOGIN
    private void limpiar() {
        nombreUsuario.setText("");
        contraseña.setText("");
    }


}