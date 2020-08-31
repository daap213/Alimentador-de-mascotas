package com.example.proyecto;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.entidades.Usuario;

public class DetallesdelUsuario extends AppCompatActivity {
    TextView usuarioId, usuarioNombre, nombres, contraseña,email,ubicacion, telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);

        usuarioId = (TextView) findViewById(R.id.idUser);
        usuarioNombre = (TextView) findViewById(R.id.tvNombreUsuario);
        nombres= (TextView) findViewById(R.id.tvNombre);
        contraseña= (TextView) findViewById(R.id.tvContraseña);
        email = (TextView) findViewById(R.id.tvEmail);
        ubicacion = (TextView) findViewById(R.id.tvUbicacion);
        telefono = (TextView) findViewById(R.id.tvTelefono);

        Bundle objetoEnviado=getIntent().getExtras();
        Usuario user=null;

        if(objetoEnviado!=null){
            user= (Usuario) objetoEnviado.getSerializable("usuario");
            usuarioId.setText("ID: "+user.getId().toString());
            usuarioNombre.setText("Nombre de usuario: "+user.getNombreUsuario());
            nombres.setText("Nombre : "+user.getNombres());
            contraseña.setText("Contraseña: "+user.getContraseña());
            email.setText("Correo: "+user.getCorreo());
            telefono.setText("Teléfono: "+user.getTelefono());
            ubicacion.setText("Ubicación: "+user.getUbicacion());
        }
    }
}