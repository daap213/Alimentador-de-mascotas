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
        consultarUsuario();
    }

    //Método que Valida el login
    private void consultarUsuario() {
        SQLiteDatabase db=admin.getReadableDatabase();
        String[] parametros={nombreUsuario.getText().toString()};
        String[] campos={Utilidades.KEY_USUARIO_NOMBREUSUARIO,Utilidades.KEY_USUARIO_CONTRASEÑA};
        String[] parametros2={contraseña.getText().toString()};
        try {
            Cursor usuario =db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.KEY_USUARIO_NOMBREUSUARIO+"=?",parametros,null,null,null);
            usuario.moveToFirst();
            nombreUsuario.setText(usuario.getString(0));

            try {
                Cursor contraseña =db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.KEY_USUARIO_CONTRASEÑA+"=?",parametros2,null,null,null);
                Intent i = new Intent(this, UsuarioAc.class);
                i.putExtra("nombre", nombreUsuario.getText().toString());
                contraseña.move(1);
                nombreUsuario.setText(contraseña.getString(1));
                usuario.close();
                contraseña.close();

                startActivity(i);
            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(),"La contraseña esta incorrecta",Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El usuario no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }

    }


    //METODO QUE LIMPIA LOS CAMPOS DE ENTRADA DE TEXTO DEL LOGIN
    private void limpiar() {
        nombreUsuario.setText("");
        contraseña.setText("");
    }


}