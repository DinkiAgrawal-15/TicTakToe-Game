package com.example.tictaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0:yellow,1:red:2:empty
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningpositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};
    int activeplayer = 0;
    boolean gameactive = true;


    public void dropin(View view) {

        ImageView counter = (ImageView) view;

        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter] == 2 && gameactive) {


            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1500);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.cross);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.circle);
                activeplayer = 0;

            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningposition : winningpositions) {
                if (gamestate[winningposition[0]] == gamestate[winningposition[1]] && gamestate[winningposition[1]] == gamestate[winningposition[2]] && gamestate[winningposition[0]] != 2) {
                    gameactive = false;
                    TextView textView = (TextView) findViewById(R.id.textView);

                    String winner = "";
                    if (activeplayer == 1) {
                        winner = "cross";
                        textView.setText(winner + " has won :)");

                    } else if(activeplayer==0) {
                        winner = "circle";
                        textView.setText(winner + " has won :)");
                    }else{
                        winner="game is tie :(" ;
                        textView.setText(winner );


                    }
                    Button button = (Button) findViewById(R.id.button);

                    button.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                }


            }
        }
    }
          public void playagain(View view){

            Button button = (Button) findViewById(R.id.button);
            TextView textView = (TextView) findViewById(R.id.textView);
            button.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);
            GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
            for (int i = 0; i < gridLayout.getChildCount(); i++) {
                ImageView counter = (ImageView) gridLayout.getChildAt(i);
                counter.setImageDrawable(null);
            }
            for (int i = 0; i < gamestate.length; i++) {
                gamestate[i] = 2;
            }

            activeplayer = 0;
            gameactive = true;
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



}
