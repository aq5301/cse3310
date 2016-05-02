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

public class Difficulty extends AppCompatActivity {


    Globals global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        global = (Globals) getApplication();

        Button setMenu = (Button) findViewById(R.id.set3);
        Button setEasy = (Button) findViewById(R.id.easy);
        Button setNorm = (Button) findViewById(R.id.normal);

        setMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setting = new Intent(Difficulty.this, SettingMenu.class);
                Difficulty.this.startActivity(setting);
            }
        });
        setEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(global.getDifficulty() == 0){
                    return;
                }
                else{
                global.setDifficulty(0);
                Toast.makeText(getApplicationContext(), "Easy mode set.",
                        Toast.LENGTH_SHORT).show();
                }
            }
        });
       setNorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(global.getDifficulty() == 1){
                    return;
                }
                else{
                global.setDifficulty(1);
                Toast.makeText(getApplicationContext(), "Normal mode set.",
                        Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
