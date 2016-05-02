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

public class Volume extends AppCompatActivity {

    Globals global;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

        global = (Globals) getApplication();

        Button setMenu = (Button) findViewById(R.id.set);
        Button soundOff = (Button) findViewById(R.id.soundOff);
        Button soundOn = (Button) findViewById(R.id.soundOn);

        setMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setting = new Intent(Volume.this, SettingMenu.class);
                Volume.this.startActivity(setting);
            }
        });
        soundOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!global.getVolume()){
                    return;
                }
                else {
                    global.setVolumeOn(false);
                    Toast.makeText(getApplicationContext(), "Volume Off!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        soundOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(global.getVolume()){
                    return;
                }
                else {
                    global.setVolumeOn(true);
                    Toast.makeText(getApplicationContext(), "Volume On!.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
