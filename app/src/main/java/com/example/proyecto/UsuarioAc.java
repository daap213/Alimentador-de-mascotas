package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class UsuarioAc extends AppCompatActivity {

    public String nombre;
    public String contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
    }



}