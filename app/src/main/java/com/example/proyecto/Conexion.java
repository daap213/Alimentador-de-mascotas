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

    private String datos= null;

    public String GetArduino(String urlString){

        try{
            URL url= new URL(urlString);
            HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
            if(httpsURLConnection.getResponseCode() == 200){

                InputStream inputStream= new BufferedInputStream(httpsURLConnection.getInputStream());

                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

                StringBuilder stringBuilder= new StringBuilder();

                String linea;
                while((linea= bufferedReader.readLine()) != null){
                    stringBuilder.append(linea);
                }
                datos= stringBuilder.toString();

                httpsURLConnection.disconnect();
            }

        }catch (IOException io){
            return null;
        }
        return datos;
    }
}