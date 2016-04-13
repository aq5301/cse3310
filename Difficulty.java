package com.example.ashley.battleship;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Difficulty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);


        Button setMenu = (Button) findViewById(R.id.set3);
        Button setEasy = (Button) findViewById(R.id.easy);
        Button setNorm = (Button) findViewById(R.id.normal);
        Button setHard = (Button) findViewById(R.id.hard);

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
                    //mode = "Easy";
            }
        });
       setNorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //mode = "Normal";
            }
        });
        setHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mode = "Hard";
            }
        });

    }

}
