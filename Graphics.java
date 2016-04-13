package com.example.ashley.battleship;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Graphics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        final Globals global = (Globals) getApplication();

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
                    global.setColorGraphics(false);
            }
        });

        colorSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    global.setColorGraphics(true);
            }
        });
    }

}
