package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
    private EditText edt1,edt2,edt3,edt4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edt1=(EditText)findViewById(R.id.edt1);
        edt2=(EditText)findViewById(R.id.edt2);
        edt3=(EditText)findViewById(R.id.edt2);
        edt4=(EditText)findViewById(R.id.edt2);

    }

    public void Registrar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String usu = edt1.getText().toString();
        String contr = edt2.getText().toString();
        String telf = edt1.getText().toString();
        String mail = edt2.getText().toString();

        bd.execSQL("insert into articulos (usuario,contraseña,telefono,email) values ("+usu+",'"+contr+"',"+telf+","+mail+")");
        bd.close();
        edt1.setText("");
        edt2.setText("");
        edt3.setText("");
        edt4.setText("");
        Toast.makeText(this, "Se Creo un Nuevo Usuario",
                Toast.LENGTH_SHORT).show();
    }

    public void Volver(View v) {
        finish();
    }
}