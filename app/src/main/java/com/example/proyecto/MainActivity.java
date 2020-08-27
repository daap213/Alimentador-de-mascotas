package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.proyecto.entidades.AdminSQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdminSQLiteOpenHelper conex= AdminSQLiteOpenHelper.getInstance(this);

    }
    public void inicio(View view) {
        Intent i = new Intent(this, Login.class );
        startActivity(i);
    }


}