package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean active = true;
    public void onDoingSomething(View v) {
        ImageView counter = (ImageView) v;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && active) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    active = false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    Toast.makeText(getApplicationContext(), winner + " has won.", Toast.LENGTH_SHORT).show();
                    Button btnPlayAgain = findViewById(R.id.btnPlayAgain);
                    TextView txtWinnerTextView = findViewById(R.id.textView);
                    txtWinnerTextView.setText(winner + " has won!");
                    btnPlayAgain.setVisibility(View.VISIBLE);
                    txtWinnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View v) {
        Button btnPlayAgain = findViewById(R.id.btnPlayAgain);
        TextView txtWinnerTextView = findViewById(R.id.textView);
        btnPlayAgain.setVisibility(View.INVISIBLE);
        txtWinnerTextView.setVisibility(View.INVISIBLE);
        androidx.gridlayout.widget.GridLayout gridLayout=findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ImageView child=(ImageView) gridLayout.getChildAt(i);
            child.setImageDrawable(null);
        }
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
         activePlayer = 0;
         active = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
