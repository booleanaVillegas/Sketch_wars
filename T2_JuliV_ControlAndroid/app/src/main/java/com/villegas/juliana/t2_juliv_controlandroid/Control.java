package com.villegas.juliana.t2_juliv_controlandroid;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class Control extends AppCompatActivity implements View.OnTouchListener {
ImageButton arri,abaj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        arri= (ImageButton) findViewById(R.id.arriba);
arri.setOnTouchListener(this);
        abaj= (ImageButton) findViewById(R.id.abajo);
        abaj.setOnTouchListener(this);
    }
    public void reiniciar(View v){
        try {
            Conexion.getInstance().enviar("reiniciar");
        } catch (IOException e) {
            e.printStackTrace();
        }
}
    public void izquierda(View v){
        try {
            Conexion.getInstance().enviar("izq");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void derecha(View v){
        try {
            Conexion.getInstance().enviar("der");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override

    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){

            case R.id.arriba:

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    try {
                        Conexion.getInstance().enviar("arri");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(event.getAction()==MotionEvent.ACTION_UP){
                    try {
                        Conexion.getInstance().enviar(" ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;
            case R.id.abajo:

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    try {
                        Conexion.getInstance().enviar("aba");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(event.getAction()==MotionEvent.ACTION_UP){
                    try {
                        Conexion.getInstance().enviar(" ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;

        }


        return false;
    }
}
