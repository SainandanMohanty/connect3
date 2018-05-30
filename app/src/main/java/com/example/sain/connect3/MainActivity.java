package com.example.sain.connect3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean turn;
    int clickCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turn = false;
        initialise();
    }

    private void initialise() {
        clickCounter = 0;

        TextView textView;
        for (int i = 0; i < 9; i++) {
            textView = findViewById(getResources().getIdentifier("textView" + (i + 2), "id", getPackageName()));
            textView.animate().alpha(0).setDuration(500);
            textView.setTag(0);
        }
    }


    public void onClick(View view) {
        clickCounter++;

        TextView textView = (TextView) view;
        int tag = (int) textView.getTag();

        if (tag == 0) {
            if (turn) {
                textView.setText("O");
                textView.setTag(1);
            } else {
                textView.setText("X");
                textView.setTag(2);
            }
            textView.animate().alpha(1).setDuration(500);

            if (clickCounter > 4) {
                if (isGameOver(textView)) {
                    TextView textView1 = findViewById(R.id.textView);
                    textView1.setAlpha(0);

                    if (turn) {
                        textView1.setText("O has won");
                    } else {
                        textView1.setText("X has won");
                    }
                    textView1.animate().alpha(1).setDuration(500);

                    gameOver();
                } else if (clickCounter == 9) {
                    TextView textView1 = findViewById(R.id.textView);
                    textView1.setAlpha(0);
                    textView1.setText("The game is tied");
                    textView1.animate().alpha(1).setDuration(500);

                    gameOver();
                }
            }

            turn = !turn;
        }
    }

    private void gameOver() {
        TextView textView;
        for (int i = 0; i < 9; i++) {
            textView = findViewById(getResources().getIdentifier("textView" + (i + 2), "id", getPackageName()));
            textView.setTag(-1);
        }
    }

    public void onClickRestart(View view) {
        initialise();
        TextView textView = findViewById(R.id.textView);
        textView.animate().alpha(0).setDuration(500);
    }

    private boolean isGameOver(TextView textView0) {

        boolean flag = true;
        Object tag = textView0.getTag();
        int id = Integer.parseInt(getResources().getResourceName(textView0.getId()).substring(37)) - 2;
        TextView textView;

        for (int i = (id / 3) * 3; i < ((id / 3) * 3) + 3; i++) {
            textView = findViewById(getResources().getIdentifier("textView" + (i + 2), "id", getPackageName()));
            if (!textView.getTag().equals(tag)) {
                flag = false;
                break;
            }

            flag = true;
        }

        if (flag) {
            return true;
        }

        for (int i = id % 3; i < (id % 3) + 9; i += 3) {
            textView = findViewById(getResources().getIdentifier("textView" + (i + 2), "id", getPackageName()));
            if (!textView.getTag().equals(tag)) {
                flag = false;
                break;
            }

            flag = true;
        }

        if (flag) {
            return true;
        }

        for (int i = 0; i < 12; i += 4) {
            textView = findViewById(getResources().getIdentifier("textView" + (i + 2), "id", getPackageName()));
            if (!textView.getTag().equals(tag)) {
                flag = false;
                break;
            }

            flag = true;
        }

        if (flag) {
            return true;
        }

        for (int i = 2; i < 8; i += 2) {
            textView = findViewById(getResources().getIdentifier("textView" + (i + 2), "id", getPackageName()));
            if (!textView.getTag().equals(tag)) {
                flag = false;
                break;
            }

            flag = true;
        }

        return flag;
    }
}
