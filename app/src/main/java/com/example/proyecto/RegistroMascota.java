package com.example.proyecto;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.entidades.AdminSQLiteOpenHelper;
import com.example.proyecto.entidades.Mascota;
import com.example.proyecto.entidades.Usuario;
import com.example.proyecto.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;

import static com.example.proyecto.utilidades.Utilidades.KEY_MASCOTA_ID;
import static com.example.proyecto.utilidades.Utilidades.TABLA_MASCOTA;

public class RegistroMascota extends AppCompatActivity {

    EditText razaMascota,nombreMascota, edadMascota;
    Spinner spinnerTamaño;
   // String []tamañosList;
   List tamañosList = new ArrayList( );
    String tamañoEscogido;

    AdminSQLiteOpenHelper admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascota);

        razaMascota = (EditText) findViewById(R.id.raza);
        nombreMascota = (EditText) findViewById(R.id.nombreMascota);
        edadMascota = (EditText) findViewById(R.id.edadMascota);
        spinnerTamaño= (Spinner) findViewById(R.id.spinnerTamaño);//para escoger el tamaño de la mascota

        admin=new AdminSQLiteOpenHelper(getApplicationContext(),"PST_G6",null,1);
    /*    tamañosList[0]="Seleccione un tamaño";
        tamañosList[1]="Miniatura";
        tamañosList[2]="Pequeño";
        tamañosList[3]="Mediano";
        tamañosList[4]="Grande";
        tamañosList[5]="Extra Grande";*/

        tamañosList.add("Seleccione un tamaño");
        tamañosList.add("Miniatura");
        tamañosList.add("Pequeño");
        tamañosList.add("Mediano");
        tamañosList.add("Grande");
        tamañosList.add("Extra Grande");

        //Adapatador necesario para el spinner del tamaño
        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,tamañosList);

        spinnerTamaño.setAdapter(adaptador);

        spinnerTamaño.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //Este método es solo para seleccionar el tamaño y registrarlo
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }
    //Este metodo falta mejorarlo, no lo borren
    public void registrarMascota() {

        SQLiteDatabase db=admin.getWritableDatabase();

        /**
         * Valida la seleccion del combo de los tamaños, si el usuario elige "seleccione tamaño" entonces
         * se retorna el id 0 ya que la palabra "seleccione tamaño" se encuentra en la pos 0 del spinner,
         * caso contrario se retorna la posicion del spinner para consultar el usuario almacenado en la lista
         */
        int position= (int) spinnerTamaño.getSelectedItemId();

        if (position!=0 ){
            Log.i("TAMAÑO",tamañosList.size()+"");
            Log.i("id combo",position+"");
            Log.i("id combo - 1",(position-1)+"");//se resta 1 ya que se quiere obtener la posicion de la lista, no del combo

          Log.i("tamaño",tamañoEscogido+"");
            tamañoEscogido =tamañosList.get(position-1).toString();
            ContentValues values=new ContentValues();

           // values.put(Utilidades.KEY_MASCOTA_ID_USUARIO,);

            values.put(Utilidades.KEY_MASCOTA_NOMBRE,nombreMascota.getText().toString());
            values.put(Utilidades.KEY_RAZA,razaMascota.getText().toString());
            values.put(Utilidades.KEY_MASCOTA_EDAD,edadMascota.getText().toString());
            values.put(Utilidades.KEY_TAMAÑO,tamañoEscogido);

            Long idResultante=db.insert(Utilidades.TABLA_MASCOTA, KEY_MASCOTA_ID,values);
            Toast.makeText(getApplicationContext(),"Su Mascota: "+nombreMascota.getText().toString()+" se registró con éxito"+ idResultante,Toast.LENGTH_SHORT).show();
            db.close();
            limpiar();
        }
     else{
            Toast.makeText(getApplicationContext(),"Debe seleccionar un Tamaño",Toast.LENGTH_LONG).show();
        }

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registroMascota2:
                registrarMascota();
        }
    }

    private void limpiar() {
        razaMascota.setText("");
        nombreMascota.setText("");
        edadMascota.setText("");
    }

    public List<Mascota> ObtenerTodasLasMascotas() {
    List<Mascota> mascotas = new ArrayList<>();

    // SELECT * FROM TABLA_MASCOTA
    // LEFT OUTER JOIN USERS
    // ON POSTS.KEY_POST_USER_ID_FK = USERS.KEY_USER_ID
    String POSTS_SELECT_QUERY =
            String.format("SELECT * FROM TABLA_MASCOTA LEFT OUTER JOIN TABLA_USUARIO ON TABLA_MASCOTA.KEY_MASCOTA_ID_USUARIO = KEY_USUARIO_ID",
                    Utilidades.TABLA_MASCOTA,
                    Utilidades.TABLA_USUARIO,
                    Utilidades.TABLA_MASCOTA,Utilidades.KEY_MASCOTA_ID_USUARIO ,
                    Utilidades.TABLA_USUARIO, Utilidades.KEY_USUARIO_ID);

    SQLiteDatabase db = admin.getReadableDatabase();
    Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);
        try {
        if (cursor.moveToFirst()) {
            do {
                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setNombreUsuario(cursor.getString(cursor.getColumnIndex(Utilidades.KEY_USUARIO_NOMBREUSUARIO))) ;
                Mascota nuevaMascota = new Mascota();
                nuevaMascota.setNombreMascota(cursor.getString(cursor.getColumnIndex(Utilidades.KEY_MASCOTA_NOMBRE)));
                nuevaMascota.dueño= nuevoUsuario;

                mascotas.add(nuevaMascota);
            } while(cursor.moveToNext());
        }
    } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Error al intentar obtener mascotras",Toast.LENGTH_LONG).show();
    } finally {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }
        return mascotas;
}

}
