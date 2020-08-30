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
import com.example.proyecto.entidades.Usuario;
import com.example.proyecto.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class RegistroMascota extends AppCompatActivity {
    EditText razaMascota,nombreMascota, tamaño, edadMascota;
    Spinner spinnerTamaño;
    List tamañosList = new ArrayList( );
   // ArrayList<String> listaUsuarios;

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

        tamañosList.add("Pequeño");
        tamañosList.add("Mediano");
        tamañosList.add("Grande");

        //consultarListaUsuarios();

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,tamañosList);

        spinnerTamaño.setAdapter(adaptador);

        spinnerTamaño.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    private void registrarMascota() {

        SQLiteDatabase db=admin.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidades.KEY_MASCOTA_NOMBRE,nombreMascota.getText().toString());
        values.put(Utilidades.KEY_RAZA,razaMascota.getText().toString());
        values.put(Utilidades.KEY_TAMAÑO,tamaño.getText().toString());
        values.put(Utilidades.KEY_MASCOTA_EDAD,edadMascota.getText().toString());

        int idCombo= (int)spinnerTamaño.getSelectedItemId();
        /**
         * Valida la seleccion del combo de los tamaños, si el usuario elige "seleccione" entonces
         * se retorna el id 0 ya que la palabra "seleccione" se encuentra en la pos 0 del combo,
         * sinó entonces se retorna la posicion del combo para consultar el usuario almacenado en la lista
         */
        if (idCombo!=0){
            Log.i("TAMAÑO",tamañosList.size()+"");
            Log.i("id combo",idCombo+"");
            Log.i("id combo - 1",(idCombo-1)+"");//se resta 1 ya que se quiere obtener la posicion de la lista, no del combo

          //  int tamaño=tamañosList.get(idCombo-1).;
          //  Log.i("id DUEÑO",idDuenio+"");

         //   values.put(Utilidades.KEY_TAMAÑO,tamaño);

            Long tamañoEscogido=db.insert(Utilidades.TABLA_MASCOTA,Utilidades.KEY_TAMAÑO,values);
            Toast.makeText(getApplicationContext(),"Tamaño: "+tamañoEscogido,Toast.LENGTH_SHORT).show();
            db.close();
            limpiar();

        }else{
            Toast.makeText(getApplicationContext(),"Debe seleccionar un Tamaño",Toast.LENGTH_LONG).show();
        }
    }

   /* private void consultarListaUsuarios() {
        SQLiteDatabase db=admin.getReadableDatabase();

        Usuario persona=null;
        usuariosList =new ArrayList<Usuario>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            persona=new Usuario();
            persona.setId(cursor.getInt(0));

            persona.setNombreUsuario(cursor.getString(1));
            persona.setNombres(cursor.getString(2));
            persona.setTelefono(cursor.getString(3));
            persona.setCorreo(cursor.getString(4));
            persona.setUbicacion(cursor.getString(5));

            Log.i("id",persona.getId().toString());
            Log.i("Nombre de usuario",persona.getNombreUsuario());
            Log.i("Nombre",persona.getNombres());
            Log.i("Correo",persona.getCorreo());
            Log.i("Ubicacion",persona.getUbicacion());
            Log.i("Telefono",persona.getTelefono());

            usuariosList.add(persona);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaUsuarios=new ArrayList<String>();
        listaUsuarios.add("Seleccione");

        for(int i=0;i<usuariosList.size();i++){
            listaUsuarios.add(usuariosList.get(i).getId()+" - "+usuariosList.get(i).getNombreUsuario());
        }
    }*/

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registroMascota2:
                registrarMascota();
        }
    }
    private void limpiar() {
        razaMascota.setText("");
        nombreMascota.setText("");
        tamaño.setText("");
        edadMascota.setText("");
    }

}
