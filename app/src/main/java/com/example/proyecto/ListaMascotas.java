package com.example.proyecto;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.entidades.AdminSQLiteOpenHelper;
import com.example.proyecto.entidades.Mascota;
import com.example.proyecto.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;


class ListaMascotas extends AppCompatActivity {
   TextView nombreMas,edadMas,razaMas,tamañoMas;
    Spinner spinnerMascota;
    List tamañosList = new ArrayList( );

    ArrayList<String> listaInformacion;
    ArrayList<Mascota> listaMascotas; //Se almacenan las mascotas de tipo Mascotas

    AdminSQLiteOpenHelper admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascotas);

        //Inicializacion de instancias
        admin=new AdminSQLiteOpenHelper(getApplicationContext(),"PST_G6",null,1);
        spinnerMascota= (Spinner)findViewById(R.id.spinnerMascota);
        nombreMas= (TextView)findViewById(R.id.nombreMascota);
        edadMas= (TextView)findViewById(R.id.edadMas);
        razaMas= (TextView)findViewById(R.id.razaMas);
        tamañoMas= (TextView)findViewById(R.id.tamañoMas);

        //Se llama al método que consulta la lista d mascotas de la db
        consultarListaMascotas();

        //Adapatador necesario para el Spinner de Mascota
        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaMascotas);

        spinnerMascota.setAdapter(adaptador);

        spinnerMascota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                if (position!=0){
                    nombreMas.setText(listaMascotas.get(position-1).getNombreMascota());
                    edadMas.setText(listaMascotas.get(position-1).getEdad());
                    razaMas.setText(listaMascotas.get(position-1).getRaza());
                    tamañoMas.setText(listaMascotas.get(position-1).getTamaño());
                }else{
                    nombreMas.setText("");
                    edadMas.setText("");
                    razaMas.setText("");
                    tamañoMas.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void consultarListaMascotas() { //Metodo que consulta toda la base de datos de la TABLA MASCOTA
        SQLiteDatabase db=admin.getReadableDatabase();

        Mascota mascota=null;
        listaMascotas=new ArrayList<Mascota>();

        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_MASCOTA,null);

        while (cursor.moveToNext()){
            mascota=new Mascota();
            mascota.setIdMascota(cursor.getInt(0));
            mascota.setIdUsuario(cursor.getInt(1));
            mascota.setNombreMascota(cursor.getString(2));
            mascota.setEdad(cursor.getInt(3));
            mascota.setRaza(cursor.getString(4));
            mascota.setTamaño(cursor.getString(5));

            listaMascotas.add(mascota);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();
        listaInformacion.add("Seleccione una Mascota");
        for (int i=0; i<listaMascotas.size();i++){
            listaInformacion.add(listaMascotas.get(i).getIdMascota()+" - "
                    +listaMascotas.get(i).getNombreMascota());
        }
    }
}
