package com.villegas.juliana.t2_juliv_controlandroid;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import java.io.IOException;

public class Instrucciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruccion);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public void undioJugar(View v){
        try {
            Conexion.getInstance().enviar("siguiente");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent= new Intent(this,Control.class);
        startActivity(intent);


    }
}
