package com.example.proyecto.entidades;

import android.app.IntentService;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.proyecto.R;
//Revisa la hora de la alarma para poder leer a que hora debe sonar y activarla cuando se dispare el evento
public class RebootServiceClass extends IntentService {
    public RebootServiceClass(String name) {
        super(name);
        startForeground(1, new Notification());
    }

    public RebootServiceClass() {
        super("RebootServiceClass");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    //Cuando obtiene el extra y es el Rebootreceiver, va a leer el id de la alarma y su hora y reguardarla
    protected void onHandleIntent(Intent intent) {
        String intentType = intent.getExtras().getString("caller");
        if (intentType == null) return;
        if (intentType.equals("RebootReceiver")) {
            SharedPreferences settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
            Utils.setAlarm(settings.getInt("alarmID", 0), settings.getLong("alarmTime", 0), this);
        }
    }
}
