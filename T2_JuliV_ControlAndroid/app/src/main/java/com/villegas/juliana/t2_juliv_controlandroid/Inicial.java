package com.villegas.juliana.t2_juliv_controlandroid;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.IOException;

public class Inicial extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    public void undioInstruccion(View v){
        try {
            Conexion.getInstance().enviar("siguiente");
        } catch (IOException e) {
            e.printStackTrace();
        }
       Intent intent= new Intent(this,Instrucciones.class);
        startActivity(intent);


    }
}
