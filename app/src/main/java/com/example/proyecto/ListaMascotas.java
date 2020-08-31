package com.example.proyecto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.R;
import com.example.proyecto.entidades.AdminSQLiteOpenHelper;
import com.example.proyecto.entidades.Mascota;
import com.example.proyecto.utilidades.Utilidades;

import java.util.ArrayList;


 class ListaMascota extends AppCompatActivity {

    ListView listViewMascota;
    ArrayList<String> listaInformacion;
    ArrayList<Mascota> listaMascotas;

    AdminSQLiteOpenHelper admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascotas);

        admin=new AdminSQLiteOpenHelper(getApplicationContext(),"PST_G6",null,1);

        listViewMascota= (ListView) findViewById(R.id.listViewMascotas);

        consultarListaPersonas();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewMascota.setAdapter(adaptador);

        listViewMascota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                Mascota mascota=listaMascotas.get(pos);

                Intent intent=new Intent(ListaMascota.this, DetalleMascotas.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("mascota",mascota);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=admin.getReadableDatabase();

        Mascota mascota=null;
        listaMascotas=new ArrayList<Mascota>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_MASCOTA,null);

        while (cursor.moveToNext()){
            mascota=new Mascota();
            mascota.setIdMascota(cursor.getInt(0));
            mascota.setNombreMascota(cursor.getString(1));
            mascota.setRaza(cursor.getString(2));
            mascota.setIdUsuario(cursor.getInt(3));


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
