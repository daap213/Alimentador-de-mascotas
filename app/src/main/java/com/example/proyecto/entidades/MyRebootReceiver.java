package com.example.proyecto.entidades;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
//Se activa si recibe un evento de reinicio y llama a un servicio  que configura la alarma guarda
public class MyRebootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, RebootServiceClass.class);
        serviceIntent.putExtra("caller", "RebootReceiver");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent);
        } else {
            context.startService(serviceIntent);
        }
    }
}