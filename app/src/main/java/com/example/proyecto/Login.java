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
   //     validarUsuarioYContraseña( nombreUsuario.getText().toString(),contraseña.getText().toString());
        consultarUsuario();
    }

    //ESTE METODO SI  FUNCIONA

    private void consultarUsuario() {
        SQLiteDatabase db=admin.getReadableDatabase();
        String[] parametros={nombreUsuario.getText().toString()};
        String[] campos={Utilidades.KEY_USUARIO_NOMBRE,Utilidades.KEY_USUARIO_CONTRASEÑA};

        try {
            Cursor cursor =db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.KEY_USUARIO_NOMBRE+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            nombreUsuario.setText(cursor.getString(0));
            contraseña.setText(cursor.getString(1));
            cursor.close();

            Intent i = new Intent(this, UsuarioAc.class);
            i.putExtra("nombre", nombreUsuario.getText().toString());
            startActivity(i);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El usuario no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }

    }



//ESTE METODO NO ME FUNCIONA :C
   /* public void validarUsuarioYContraseña(String usuario, String contraseña){
        SQLiteDatabase db=admin.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM Utilidades.TABLA_USUARIO where Utilidades.KEY_USUARIO_NOMBRE=? and Utilidades.KEY_USUARIO_CONTRASEÑA=?" , new String[]{usuario,contraseña});

        if(cursor.getCount()>0){
            cursor.close();
            Intent i = new Intent(this, UsuarioAc.class);
            i.putExtra("nombre", nombreUsuario.getText().toString());
            startActivity(i);
        }else
            Toast.makeText(getApplicationContext(),"El usuario o contraseña no existen", Toast.LENGTH_SHORT).show();
            limpiar();


    }*/
    //METODO QUE LIMPIA LOS CAMPOS DE ENTRADA DE TEXTO DEL LOGIN
    private void limpiar() {
        nombreUsuario.setText("");
        contraseña.setText("");
    }


}