package com.example.proyecto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.entidades.AdminSQLiteOpenHelper;
import com.example.proyecto.entidades.Mascota;
import com.example.proyecto.entidades.Usuario;
import com.example.proyecto.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;


public class ListaMascotas extends AppCompatActivity {
    TextView nombreMas,edadMas,razaMas,tamañoMas;
    Spinner spinnerMascota;
    ArrayList<String> listaInformacion;
    ArrayList<Mascota> listaMascotas; //Se almacenan las mascotas de tipo Mascotas
    ListView listViewMascota;
    AdminSQLiteOpenHelper admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascotas);

        //Inicializacion de instancias
        admin=new AdminSQLiteOpenHelper(getApplicationContext(),"PST_G6",null,1);
        listViewMascota= (ListView) findViewById(R.id.listViewMascotas);
      /* spinnerMascota= (Spinner)findViewById(R.id.spinnerMascota);
        nombreMas= (TextView)findViewById(R.id.nombreMascota);
        edadMas= (TextView)findViewById(R.id.edadMas);
        razaMas= (TextView)findViewById(R.id.razaMas);
        tamañoMas= (TextView)findViewById(R.id.tamañoMas);*/

        //Se llama al método que consulta la lista d mascotas de la db
        consultarListaPersonas();

        //Adapatador necesario para el Spinner de Mascota
        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewMascota.setAdapter(adaptador);

        listViewMascota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                Mascota mascota=listaMascotas.get(pos);

                Intent intent=new Intent(ListaMascotas.this,DetalleMascotas.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("mascota",mascota);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=admin.getReadableDatabase();


        listaMascotas=new ArrayList<Mascota>();

        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_MASCOTA,null);

        while (cursor.moveToNext()){
            Mascota mascota=new Mascota();
            mascota.setIdMascota(cursor.getInt(0));
            mascota.setNombreMascota(cursor.getString(3));
            mascota.setRaza(cursor.getString(4));
            mascota.setIdUsuario(cursor.getInt(2));


            listaMascotas.add(mascota);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        for (int i=0; i<listaMascotas.size();i++){
            listaInformacion.add(listaMascotas.get(i).getIdMascota()+" - "
                    +listaMascotas.get(i).getNombreMascota());
        }
    }
}
