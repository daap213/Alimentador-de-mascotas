package com.example.proyecto;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import   javax.net.ssl.HttpsURLConnection;

public class Conexion {
    //se crea una variable tipo String de datos para guardar la informacion que existe en el localHost
    private String datos= null;
    //se crea un metodo que permite hacer la conexion con el localHost que a su vez esta conectado a arduino
    public String GetArduino(String urlString){

        try{
            URL url= new URL(urlString);
            //Se conecta a la URL designada en el constructor del método
            HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
            if(httpsURLConnection.getResponseCode() == 200){
                //Se empieza a recolectar toda la informacion que existe en el LocalHost, codigo HTML
                InputStream inputStream= new BufferedInputStream(httpsURLConnection.getInputStream());

                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

                StringBuilder stringBuilder= new StringBuilder();

                String linea;
                //Se recorre la informacion linea a linea y se lo almacena en un variable de titpo StringBuilder
                while((linea= bufferedReader.readLine()) != null){
                    stringBuilder.append(linea);
                }
                //Se copia toda la informacion guardad en la variable stringBuilder en la variable datos
                datos= stringBuilder.toString();
                //se desconecta la conexion con el LocalHost
                httpsURLConnection.disconnect();
            }

        }catch (IOException io){
            return null;
        }
        //este metodo retorna la informacion que se encuentra en la variable datos
        return datos;
    }
}