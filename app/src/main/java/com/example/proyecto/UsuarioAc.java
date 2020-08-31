package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.entidades.AdminSQLiteOpenHelper;
import com.example.proyecto.utilidades.Utilidades;

public class UsuarioAc extends AppCompatActivity {

    private TextView bienvenida;
    private String nombreUsuario;
    AdminSQLiteOpenHelper admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_ac);
        admin =new  AdminSQLiteOpenHelper(this,"PST_G6",null,1);

       Bundle bundle = getIntent().getExtras();

       bienvenida= (TextView) findViewById(R.id.mensajeBienvenida);
       bienvenida.setText("Bienvenid@ "+bundle.getString("nombre"));

       nombreUsuario=bundle.getString("nombre");//BORRAR ESTA LINES Si se eliminan los botones d actualizar Datos d usuario
    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.registroMascota:
                miIntent=new Intent(UsuarioAc.this,RegistroMascota.class);
                break;
            case R.id.verMascota:
                miIntent=new Intent(UsuarioAc.this, ListaMascota.class);
                break;
            case R.id.actualizarDatos:
                miIntent=new Intent(UsuarioAc.this,ActualizarUsuario.class);
                break;
            case R.id.eliminarUsuario:
                eliminarUsuario();
                break;
            case R.id.horario:
                miIntent=new Intent(UsuarioAc.this,HorariodeComida.class);
                break;
            case R.id.cerrarSesion:
                miIntent=new Intent(UsuarioAc.this,MainActivity.class);
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
    }

    private void eliminarUsuario() {
        SQLiteDatabase db=admin.getWritableDatabase();
        String[] parametros={nombreUsuario};

        db.delete(Utilidades.TABLA_USUARIO,Utilidades.KEY_USUARIO_NOMBREUSUARIO+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Se Eliminó el usuario",Toast.LENGTH_LONG).show();
        db.close();
        Intent i = new Intent(this, UsuarioAc.class);
        startActivity(i);
    }

}