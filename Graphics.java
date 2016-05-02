package com.example.ashley.battleship;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Graphics extends AppCompatActivity {


    Globals global;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        global = (Globals) getApplication();

        Button setMenu = (Button) findViewById(R.id.set2);
        Button monochromeSet = (Button) findViewById(R.id.mono);
        Button colorSet = (Button) findViewById(R.id.color);

        setMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setting = new Intent(Graphics.this, SettingMenu.class);
                Graphics.this.startActivity(setting);
            }
        });

        monochromeSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!global.getGraphics()){
                    return;
                }
                else{
                    global.setColorGraphics(false);
                    Toast.makeText(getApplicationContext(), "Monochrome Graphics set.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        colorSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(global.getGraphics()){
                    return;
                }
                else {
                    global.setColorGraphics(true);
                    Toast.makeText(getApplicationContext(), "Colored Graphics set.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
