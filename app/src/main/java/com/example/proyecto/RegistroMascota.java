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
    EditText razaMascota,nombreMascota, edadMascota;
    Spinner spinnerTamaño;
    List tamañosList = new ArrayList( );


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
        tamañosList.add("Seleccione un tamaño");
        tamañosList.add("Pequeño");
        tamañosList.add("Mediano");
        tamañosList.add("Grande");

        //consultarListaUsuarios();

        //Adapatador necesario para el spinner del tamaño
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
    //Este metodo falta mejorarlo, no lo borren
    private void registrarMascota() {

        SQLiteDatabase db=admin.getWritableDatabase();

        int position= (int)spinnerTamaño.getSelectedItemId();
        /**
         * Valida la seleccion del combo de los tamaños, si el usuario elige "seleccione tamaño" entonces
         * se retorna el id 0 ya que la palabra "seleccione tamaño" se encuentra en la pos 0 del spinner,
         * caso contrario se retorna la posicion del spinner para consultar el usuario almacenado en la lista
         */

        if (position!=0){
            String tamañoEscogido= tamañosList.get(position-1).toString();
            ContentValues values=new ContentValues();
            values.put(Utilidades.KEY_MASCOTA_NOMBRE,nombreMascota.getText().toString());
            values.put(Utilidades.KEY_RAZA,razaMascota.getText().toString());
            values.put(Utilidades.KEY_MASCOTA_EDAD,edadMascota.getText().toString());

            values.put(Utilidades.KEY_TAMAÑO,tamañoEscogido );
            //Long tamañoEscogido=db.insert(Utilidades.TABLA_MASCOTA,Utilidades.KEY_TAMAÑO,values);
            Toast.makeText(getApplicationContext(),"Su Mascota: "+nombreMascota.getText().toString()+" se registró con éxito",Toast.LENGTH_SHORT).show();
            db.close();
            limpiar();

        }else{
            Toast.makeText(getApplicationContext(),"Debe seleccionar un Tamaño",Toast.LENGTH_LONG).show();
        }

      /*  if (idCombo!=0){
            Log.i("TAMAÑO",tamañosList.size()+"");
            Log.i("id combo",idCombo+"");
            Log.i("id combo - 1",(idCombo-1)+"");//se resta 1 ya que se quiere obtener la posicion de la lista, no del combo

    //      int tamaño=tamañosList.get(idCombo-1).getId();
    //  Log.i("id DUEÑO",idDuenio+"");


            values.put(Utilidades.KEY_TAMAÑO,spinnerTamaño.toString() );
            Long tamañoEscogido=db.insert(Utilidades.TABLA_MASCOTA,Utilidades.KEY_TAMAÑO,values);
            Toast.makeText(getApplicationContext(),"Su Mascota: "+nombreMascota.getText().toString()+" se registró con éxito",Toast.LENGTH_SHORT).show();
            db.close();
            limpiar();

        }else{
            Toast.makeText(getApplicationContext(),"Debe seleccionar un Tamaño",Toast.LENGTH_LONG).show();
        }*/
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

}
