package com.example.proyecto;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.entidades.AdminSQLiteOpenHelper;
import com.example.proyecto.entidades.Mascota;
import com.example.proyecto.utilidades.Utilidades;


public class DetalleMascotas extends AppCompatActivity {

    AdminSQLiteOpenHelper admin;
    TextView campoIdMascota, campoNombreMascota, campoRaza,campoIdPersona, campoNombrePersona, campotamaño;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        admin=new AdminSQLiteOpenHelper(getApplicationContext(),"bd_usuarios",null,1);

        campoIdPersona = (TextView) findViewById(R.id.campoIdPersona);
        campoNombrePersona = (TextView) findViewById(R.id.campoNombrePersona);
        campoIdMascota = (TextView) findViewById(R.id.campoIdMascota);
        campoNombreMascota = (TextView) findViewById(R.id.campoNombreMascota);
        campoRaza = (TextView) findViewById(R.id.campoRaza);
        campotamaño = (TextView) findViewById(R.id.tamaño);

        Bundle objetoEnviado=getIntent().getExtras();
        Mascota mascota=null;

        if(objetoEnviado!=null){
            mascota= (Mascota) objetoEnviado.getSerializable("mascota");
            campoIdMascota.setText(mascota.getIdMascota().toString());
            campoNombreMascota.setText(mascota.getNombreMascota());
            campoRaza.setText(mascota.getRaza());
            consultarPersona(mascota.getIdUsuario());
        }

    }
//esto ver si borro
    private void consultarPersona(Integer idPersona) {
        SQLiteDatabase db=admin.getReadableDatabase();
        String[] parametros={idPersona.toString()};
        String[] campos={Utilidades.KEY_USUARIO_NOMBREUSUARIO,Utilidades.KEY_USUARIO_CONTRASEÑA};
        Toast.makeText(getApplicationContext(),"Id"+idPersona,Toast.LENGTH_LONG).show();
        try {
            Cursor cursor =db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.KEY_USUARIO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            campoIdPersona.setText(idPersona.toString());
            campoNombrePersona.setText(cursor.getString(0));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El id del dueño no existe",Toast.LENGTH_LONG).show();
            campoNombrePersona.setText("");

        }
    }
}
