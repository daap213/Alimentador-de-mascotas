package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.nom);
        et2=(EditText)findViewById(R.id.cont);

    }
    public void Registrar(View v) {
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }

    public void Ingresar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();
        String usuario = et1.getText().toString();
        String contr = et2.getText().toString();

        Cursor cont = bd.rawQuery(
                "select usuario, telefono, email from articulos where contraseña=" + contr, null);
        Cursor usu = bd.rawQuery(
                "select contraseña,telefono, email from articulos where usuario=" + usuario, null);
        if (usu.moveToFirst()) {
            if( cont.moveToFirst()){
                Intent i = new Intent(this, Usuario.class);
                startActivity(i);
            }
        } else
            Toast.makeText(this, "El Usuario o Contraseña no son Válidos",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }
}