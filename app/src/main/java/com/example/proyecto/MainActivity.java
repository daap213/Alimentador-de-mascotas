package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.proyecto.entidades.AdminSQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"PST_G6",null,1);

    }



   public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.registrarseInicio:
                miIntent=new Intent(MainActivity.this,RegistroUsuario.class);
                break;
            case R.id.loginUsuario:
                miIntent=new Intent(MainActivity.this,Login.class);
                break;
            case R.id.ConsultaListaUsuario:
                miIntent=new Intent(MainActivity.this, ConsultarListaPersonas.class);
                break;

        }
        if (miIntent!=null){
            startActivity(miIntent);
        }

}}