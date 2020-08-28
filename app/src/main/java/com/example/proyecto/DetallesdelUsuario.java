package com.example.proyecto;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.entidades.Usuario;

public class DetallesdelUsuario extends AppCompatActivity {
    TextView usuarioId, usuarioNombre, telefono,email,ubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);

        usuarioId = (TextView) findViewById(R.id.idUser);
        usuarioNombre = (TextView) findViewById(R.id.tvNombre);
        telefono = (TextView) findViewById(R.id.tvTelefono);
        email = (TextView) findViewById(R.id.tvEmail);
        ubicacion = (TextView) findViewById(R.id.tvUbicacion);

        Bundle objetoEnviado=getIntent().getExtras();
        Usuario user=null;

        if(objetoEnviado!=null){
            user= (Usuario) objetoEnviado.getSerializable("usuario");
            usuarioId.setText(user.getId().toString());
            usuarioNombre.setText(user.getNombre());
            telefono.setText(user.getTelefono());
            email.setText(user.getCorreo());
            ubicacion.setText(user.getUbicacion());

        }

    }
}