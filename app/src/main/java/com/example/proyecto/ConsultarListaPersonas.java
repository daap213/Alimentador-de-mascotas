package com.example.proyecto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.entidades.AdminSQLiteOpenHelper;
import com.example.proyecto.entidades.Usuario;
import com.example.proyecto.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultarListaPersonas extends AppCompatActivity {

    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUsuarios;

    AdminSQLiteOpenHelper admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_lista_list_view);

        admin=new AdminSQLiteOpenHelper(getApplicationContext(),"PST_G6",null,1);

        listViewPersonas= (ListView) findViewById(R.id.listViewPersonas);

        consultarListaPersonas();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewPersonas.setAdapter(adaptador);

        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
              /*  String informacion="id: "+listaUsuarios.get(pos).getId()+"\n";
                informacion+="Nombre de Usuario: "+listaUsuarios.get(pos).getNombreUsuario()+"\n";
                informacion+="Nombres: "+listaUsuarios.get(pos).getNombres()+"\n";
                informacion+="Contraseña: "+listaUsuarios.get(pos).getContraseña()+"\n";
                informacion+="Telefono: "+listaUsuarios.get(pos).getTelefono()+"\n";
                informacion+="Correo: "+listaUsuarios.get(pos).getCorreo()+"\n";
                informacion+="Ubicación: "+listaUsuarios.get(pos).getUbicacion()+"\n";
                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();*/

                Usuario user=listaUsuarios.get(pos);

                Intent intent=new Intent(ConsultarListaPersonas.this,DetallesdelUsuario.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("usuario",user);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
    //Lee la base de datos de la tabla USUARIO y los almacena en un arrayList el cual es de tipo Usuario
    public void consultarListaPersonas() {
        SQLiteDatabase db=admin.getReadableDatabase();

        Usuario usuario=null;
        listaUsuarios=new ArrayList<Usuario>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            usuario=new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombreUsuario(cursor.getString(1));
            usuario.setNombres(cursor.getString(2));
            usuario.setContraseña(cursor.getString(3));
            usuario.setCorreo(cursor.getString(4));
            usuario.setUbicacion(cursor.getString(5));
            usuario.setTelefono(cursor.getString(6));

            listaUsuarios.add(usuario);
        }
        obtenerLista();
    }

    public void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        for (int i=0; i<listaUsuarios.size();i++){
            listaInformacion.add(listaUsuarios.get(i).getId()+" - "
                    +listaUsuarios.get(i).getNombreUsuario());
        }

    }

}

