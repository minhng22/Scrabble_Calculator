package com.example.administrator.scrabblecalculator;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.nfc.FormatException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class
        MainActivity extends Activity {

    private EditText playerWord;
    private TextView playerNum;
    private String playerWordString;
    private TextView playerScoreTextView;
    private String[] alphabet;
    private int[] scores;
    private int[] flag = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private int totalScore;
   // private boolean doubleLetter = false;
   // private  boolean tripleLetter = false;
    private String word;
    private int afterScore;
    private int flagIndex;
    private int playerIndex;
    private  int[] playerScores = {0, 0, 0, 0};
    private int numPlayers;

    private int playerOneScore;
    private int playerTwoScore;
    private int playerThreeScore;
    private int playerFourScore;

    private boolean ranOnce = true;

    //double/triple letter won't add on to total score overall



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            numPlayers = bundle.getInt("numOfPlayers");

        }
        Bundle mainBundle = getIntent().getExtras();
        if(mainBundle != null){

           playerIndex = mainBundle.getInt("playerIndex");
           playerOneScore =   mainBundle.getInt("playerOneScore");
            playerTwoScore = mainBundle.getInt("playerTwoScore");
            playerThreeScore = mainBundle.getInt("playerThreeScore");
            playerFourScore = mainBundle.getInt("playerFourScore");
            numPlayers = mainBundle.getInt("numOfPlayers");




        }
        playerScores[0] = playerOneScore;
        playerScores[1] = playerTwoScore;
        playerScores[2] = playerThreeScore;
        playerScores[3] = playerFourScore;

      /*  if(playerIndex != 0){
            Bundle mainBundle = getIntent().getExtras();
            playerIndex = mainBundle.getInt("playerIndex");
        }
        else
            playerIndex = 0;*/

        //Text-oriented views
        playerScoreTextView = (TextView) findViewById(R.id.playerScoreTextView);
        playerWord = (EditText) findViewById(R.id.playerWord);
        playerWord.addTextChangedListener(playerWordTextWatcher);

        playerNum = (TextView)findViewById(R.id.playerNum);
        playerNum.setText("Player " + (playerIndex+1) + " Word");

        //Button/score views
        Button scoreButton = (Button)findViewById(R.id.scoreButton);
        scoreButton.setOnClickListener(scoreButtonListener);

        Button tripleWord = (Button) findViewById(R.id.tripleWord);
        tripleWord.setOnClickListener(tripleWordListener);

        Button doubleWord = (Button) findViewById(R.id.doubleWord);
        doubleWord.setOnClickListener(doubleWordListener);

        Button doubleLetter = (Button) findViewById(R.id.doubleLetter);
        doubleLetter.setOnClickListener(doubleLetterListener);

        Button tripleLetter = (Button) findViewById(R.id.tripleLetter);
        tripleLetter.setOnClickListener(tripleLetterListener);

    }

    protected void onStart(Bundle savedInstanceState){
        flag[0] = 1;
        playerWordString = "";
        playerScoreTextView.setText(String.valueOf(0));

    }


    private TextWatcher playerWordTextWatcher = new TextWatcher(){

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


            playerWordString = "";
            playerWordString = s.toString();
            totalScore = 0;
            flagIndex = updateScore(playerWordString);


            if(playerWord.getText().toString().matches("")) {
                playerScoreTextView.setText(String.valueOf(0));
                for(int i = 0; i < flag.length; i++)
                    flag[i] = 1;
            }
            playerScoreTextView.setText(String.valueOf(totalScore));


        }
        @Override
        public void afterTextChanged(Editable s){


        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after){

        }
    };

    public int updateScore(String word){

        String[] alphabet = getResources().getStringArray(R.array.alphabet_array);
        int[] scores= getResources().getIntArray(R.array.letter_scores);

        String s;
        int letterScoreIndex;
        int letterScore;
        int lastIndex = 0;

        for(int count = 0; count < word.length(); count++){

            //flag = 1;

            s = word.substring(count, count + 1);
            letterScoreIndex = java.util.Arrays.asList(alphabet).indexOf(s);
            letterScore = scores[letterScoreIndex];

            letterScore = letterScore*flag[count];
            totalScore += letterScore;
            playerScoreTextView.setText(String.valueOf(totalScore));
            afterScore = totalScore;
            //flag = 0;
            lastIndex = count;

        }
        return lastIndex;

    }
    public OnClickListener doubleLetterListener = new OnClickListener(){
        @Override
        public void onClick(View v){
            flag[flagIndex] = 2;
        }
    };

    public OnClickListener tripleLetterListener = new OnClickListener(){
        @Override
        public void onClick(View v) {
            flag[flagIndex] = 3;
        }
    };

    public OnClickListener doubleWordListener = new OnClickListener(){
        @Override
        public void onClick(View v){

            totalScore = afterScore*2;
            playerScoreTextView.setText(String.valueOf(totalScore));

        }
    };

    public OnClickListener tripleWordListener = new OnClickListener(){
        @Override
        public void onClick(View v){

            totalScore = afterScore*3;
            playerScoreTextView.setText(String.valueOf(totalScore));

        }
    };
    public OnClickListener scoreButtonListener = new OnClickListener(){
        @Override
        public void onClick(View v){
            playerScores[playerIndex] += totalScore;
            playerOneScore = playerScores[0];
            playerTwoScore = playerScores[1];
            playerThreeScore = playerScores[2];
            playerFourScore = playerScores[3];

            Intent scorePage = new Intent(MainActivity.this, scorePage.class);
            Bundle scores = new Bundle();

           // String playerScoresString = "";


           /* if(numPlayers > 0)
                playerScoresString = "Player 1's Score.........." + playerOneScore + "\n";
            if(numPlayers > 1)
                playerScoresString += "Player 2's Score.........." + playerTwoScore + "\n";
            if(numPlayers > 2)
                playerScoresString += "Player 3's Score.........." + playerThreeScore+ "\n";
            if(numPlayers > 3)
                playerScoresString += "Player 4's Score.........." + playerFourScore+ "\n";*/



            if(playerIndex == (numPlayers-1))
                playerIndex =0;
            else
                playerIndex++;

            scores.putInt("playerIndex", playerIndex);
            scores.putInt("playerOneScore", playerOneScore);
            scores.putInt("playerTwoScore", playerTwoScore);
            scores.putInt("playerThreeScore", playerThreeScore);
            scores.putInt("playerFourScore", playerFourScore);
            scores.putInt("numOfPlayers", numPlayers);
            scorePage.putExtras(scores);
            startActivity(scorePage);

        }
    };

}

