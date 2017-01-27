package com.example.administrator.scrabblecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 */
public class scorePage extends Activity {

    private int player1Score;
    private int player2Score;
    private int player3Score;
    private int player4Score;
    private int numPlayers;
    private String scoreString;
    private TextView scoreTextView;
    private int playerIndex;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_page);
          Bundle scores = getIntent().getExtras();

        scoreString = "";
        scoreTextView =  (TextView)findViewById(R.id.scoreTextView);


        Button scoreButton = (Button)findViewById(R.id.scoringButton);
        scoreButton.setOnClickListener(scoreButtonOnClick);

        Button newGame = (Button)findViewById(R.id.newGame);
        newGame.setOnClickListener(newGameButtonListener);


        player1Score = scores.getInt("playerOneScore");
        player2Score = scores.getInt("playerTwoScore");
        player3Score = scores.getInt("playerThreeScore");
        player4Score = scores.getInt("playerFourScore");
        numPlayers = scores.getInt("numOfPlayers");
        playerIndex = scores.getInt("playerIndex");


        if(numPlayers > 0)
            scoreString = "Player 1's Score........................" + player1Score + "\n";
        if(numPlayers > 1)
            scoreString += "Player 2's Score........................" + player2Score + "\n";
        if(numPlayers > 2)
            scoreString += "Player 3's Score........................" + player3Score+ "\n";
        if(numPlayers > 3)
            scoreString += "Player 4's Score........................" + player4Score+ "\n";

               scoreTextView.setText(scoreString);

        }

    public View.OnClickListener scoreButtonOnClick = new View.OnClickListener(){

        @Override
        public void onClick(View v){
            Intent mainPage = new Intent(scorePage.this, MainActivity.class);
            Bundle mainBundle = new Bundle();

            mainBundle.putInt("playerIndex", playerIndex);
            mainBundle.putInt("playerOneScore", player1Score);
            mainBundle.putInt("playerTwoScore", player2Score);
            mainBundle.putInt("playerThreeScore", player3Score);
            mainBundle.putInt("playerFourScore", player4Score);
            mainBundle.putInt("numOfPlayers", numPlayers);
            mainPage.putExtras(mainBundle);
            startActivity(mainPage);
        }


    };
    public View.OnClickListener newGameButtonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            startActivity(new Intent(scorePage.this, startScreen.class));
        }
    };


}
