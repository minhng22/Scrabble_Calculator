package com.example.administrator.scrabblecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PlayerNum extends AppCompatActivity {


    //NOT CHANGING PLAYERNUM ON SCORING SCREEN
    //ALSO NOT SHOWING ANY TEXTVIEW AFTER PLAYERINDEX OF 0
    //NOT UPDATING SCORES EITHER
    private int numOfPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_num);

        Button scoring = (Button)findViewById(R.id.scoringButton);

        ImageButton onePlayer = (ImageButton)findViewById(R.id.onePlayer);
        onePlayer.setOnClickListener(onePlayerButton);

        ImageButton twoPlayer = (ImageButton)findViewById(R.id.twoPlayer);
        twoPlayer.setOnClickListener(twoPlayerButton);

        ImageButton threePlayer = (ImageButton)findViewById(R.id.threePlayer);
        threePlayer.setOnClickListener(threePlayerButton);

        ImageButton fourPlayer = (ImageButton)findViewById(R.id.fourPlayer);
        fourPlayer.setOnClickListener(fourPlayerButton);

        scoring.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent main = new Intent(PlayerNum.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("numOfPlayers", numOfPlayers);
                main.putExtras(bundle);
                startActivity(main);
            }
        });

    }

    public View.OnClickListener onePlayerButton = new View.OnClickListener(){

        @Override
        public void onClick(View v){
            numOfPlayers = 1;


        }

    };
    public View.OnClickListener twoPlayerButton = new View.OnClickListener(){

        @Override
        public void onClick(View v){
            numOfPlayers = 2;


        }

    };
    public View.OnClickListener threePlayerButton = new View.OnClickListener(){

        @Override
        public void onClick(View v){
            numOfPlayers = 3;


        }

    };
    public View.OnClickListener fourPlayerButton = new View.OnClickListener(){

        @Override
        public void onClick(View v){
            numOfPlayers = 4;


        }

    };



}
