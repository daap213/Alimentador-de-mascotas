package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.entidades.AdminSQLiteOpenHelper;
import com.example.proyecto.utilidades.Utilidades;

public class UsuarioAc extends AppCompatActivity {

    //Se crean las diferentes variables de estado de la actividad
    private TextView bienvenida;
    private String nombreUsuario;
    AdminSQLiteOpenHelper admin;
    Button btn1;
    Button btn2;
    TextView txtresultado;
    boolean servo1= false;
    boolean servo2= false;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_ac);
        admin =new  AdminSQLiteOpenHelper(this,"PST_G6",null,1);

       Bundle bundle = getIntent().getExtras();

       bienvenida= (TextView) findViewById(R.id.mensajeBienvenida);
       bienvenida.setText("Bienvenid@ "+bundle.getString("nombre"));

       nombreUsuario=bundle.getString("nombre");//BORRAR ESTA LINES Si se eliminan los botones d actualizar Datos d usuario
        //Se instancian los botonees creados en el XML
        btn1= (Button)findViewById(R.id.comida);
        btn2= (Button)findViewById(R.id.agua);
        //txtresultado=(TextView)findViewById(R.id.txtResultado);
        //solicitud();
        //Comando para actualizar la aplicacion y el localhost
        handler.postDelayed(actualizacion,0);

        //se crea la acciones de los botones
        //asiganciones de valor a el contrsuctor del metodo solicitud
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solicitud("?H2");
                servo1= true;
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solicitud("?H3");
                servo2= true;
            }
        });

    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.registroMascota:
                miIntent=new Intent(UsuarioAc.this,RegistroMascota.class);
                break;
            case R.id.verMascota:
                miIntent=new Intent(UsuarioAc.this, ListaMascota.class);
                break;
            case R.id.actualizarDatos:
                miIntent=new Intent(UsuarioAc.this,ActualizarUsuario.class);
                break;
            case R.id.eliminarUsuario:
                eliminarUsuario();
                break;
            case R.id.horario:
                miIntent=new Intent(UsuarioAc.this,HorariodeComida.class);
                break;
            case R.id.cerrarSesion:
                miIntent=new Intent(UsuarioAc.this,MainActivity.class);
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
    }

    private void eliminarUsuario() {
        SQLiteDatabase db=admin.getWritableDatabase();
        String[] parametros={nombreUsuario};

        db.delete(Utilidades.TABLA_USUARIO,Utilidades.KEY_USUARIO_NOMBREUSUARIO+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Se Eliminó el usuario",Toast.LENGTH_LONG).show();
        db.close();
        Intent i = new Intent(this, UsuarioAc.class);
        startActivity(i);
    }

    //Metodo creado para la conectividad con las diferentes extensiones web que posee el localhost
    public void solicitud(String comando){
        //Se conecta al servidor
        ConnectivityManager connMgr= (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        //Nombre de la ip mas la extension del localhost
        String url= "http://192.168.0.20/"+comando;
        if(networkInfo != null && networkInfo.isConnected()) {
            //Conexion directa a la extension
            new DownloadWebpageTask().execute(url);
        }else{
            //txtresultado.setText("Conexion detectada");
        }
    }

    //Hilo que actualiza la aplicacion cada 2 segundo
    private Runnable actualizacion= new Runnable() {
        @Override
        public void run() {
            solicitud("");
            handler.postDelayed(this, 2000);
        }
    };

    //metodo que permite crear la conexion directa desde la clase Conexion
    private class DownloadWebpageTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... urls) {
            Conexion conexion= new Conexion();

            return conexion.GetArduino(urls[0]);
        }
        //@Override
        protected void onPostExecute(String result) {
            if(result!= null){
                if(result.contains("Switch ON - Pin2") && servo1==true){
                    //btn1.setText("ACTIVADO");
                    System.out.println("hola1");
                    servo1= false;
                    //handler.postDelayed(actualizacion,0);
                }
                if (result.contains("Switch ON - Pin3") && servo2==true){
                    //btn2.setText("ACTIVADO");
                    System.out.println("hola2");
                    servo2= false;
                    //handler.postDelayed(actualizacion,0);
                }else {
                    //txtresultado.setText("Conexion a la red");
                }
            }   //txtresultado.setText(result);
        }
    }
}