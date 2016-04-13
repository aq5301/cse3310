package com.example.ashley.battleship;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SettingMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_menu);


        Button volume = (Button) findViewById(R.id.vol);
        Button difficulty = (Button) findViewById(R.id.diff);
        Button graphics = (Button) findViewById(R.id.graph);
        Button mm = (Button) findViewById(R.id.return_mm);

        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volSetting = new Intent(SettingMenu.this, Volume.class);
                SettingMenu.this.startActivity(volSetting);
            }
        });

        difficulty.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent diffSetting = new Intent(SettingMenu.this, Difficulty.class);
                SettingMenu.this.startActivity(diffSetting);
            }
        });

        graphics.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent graphSetting = new Intent(SettingMenu.this, Graphics.class);
                SettingMenu.this.startActivity(graphSetting);
            }
        });

        mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnMM = new Intent(SettingMenu.this, MainMenu.class);
                SettingMenu.this.startActivity(returnMM);
            }
        });


}
}