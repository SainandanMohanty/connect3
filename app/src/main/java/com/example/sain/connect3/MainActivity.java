package com.example.sain.connect3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
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

        ImageView imageView;
        for (int i = 0; i < 9; i++) {
            imageView = findViewById(getResources().getIdentifier("imageView" + (i + 2), "id", getPackageName()));
            if (imageView.getAlpha() != 0) {
                imageView.animate().alpha(0).setDuration(250);
            }
            imageView.setTag(0);
        }
    }

    public void onClick(View view) {
        clickCounter++;

        ImageView imageView = (ImageView) view;
        int tag = (int) imageView.getTag();

        if (tag == 0) {
            if (turn) {
                imageView.setImageResource(R.drawable.o);
                imageView.setTag(1);
            } else {
                imageView.setImageResource(R.drawable.x);
                imageView.setTag(2);
            }
            imageView.animate().alpha(1).setDuration(1000);

            if (clickCounter > 4) {
                if (isGameOver(imageView)) {
                    TextView textView = findViewById(R.id.textView);
                    textView.setAlpha(0);

                    if (turn) {
                        textView.setText("O wins");
                    } else {
                        textView.setText("X wins");
                    }
                    textView.animate().alpha(1).setDuration(250);

                    gameOver();
                } else if (clickCounter == 9) {
                    TextView textView = findViewById(R.id.textView);
                    textView.setAlpha(0);
                    textView.setText("The game is tied");
                    textView.animate().alpha(1).setDuration(250);

                    gameOver();
                }
            }

            turn = !turn;
        }
    }

    private void gameOver() {
        ImageView imageView;
        for (int i = 0; i < 9; i++) {
            imageView = findViewById(getResources().getIdentifier("imageView" + (i + 2), "id", getPackageName()));
            imageView.setTag(-1);
        }
    }

    public void onClickRestart(View view) {
        initialise();
        TextView textView = findViewById(R.id.textView);
        textView.animate().alpha(0).setDuration(250);
    }

    private boolean isGameOver(ImageView imageView0) {

        boolean flag = true;
        Object tag = imageView0.getTag();
        int id = Integer.parseInt(getResources().getResourceName(imageView0.getId()).substring(38)) - 2;
        ImageView imageView;

        for (int i = (id / 3) * 3; i < ((id / 3) * 3) + 3; i++) {
            imageView = findViewById(getResources().getIdentifier("imageView" + (i + 2), "id", getPackageName()));
            if (!imageView.getTag().equals(tag)) {
                flag = false;
                break;
            }

            flag = true;
        }

        if (flag) {
            return true;
        }

        for (int i = id % 3; i < (id % 3) + 9; i += 3) {
            imageView = findViewById(getResources().getIdentifier("imageView" + (i + 2), "id", getPackageName()));
            if (!imageView.getTag().equals(tag)) {
                flag = false;
                break;
            }

            flag = true;
        }

        if (flag) {
            return true;
        }

        for (int i = 0; i < 12; i += 4) {
            imageView = findViewById(getResources().getIdentifier("imageView" + (i + 2), "id", getPackageName()));
            if (!imageView.getTag().equals(tag)) {
                flag = false;
                break;
            }

            flag = true;
        }

        if (flag) {
            return true;
        }

        for (int i = 2; i < 8; i += 2) {
            imageView = findViewById(getResources().getIdentifier("imageView" + (i + 2), "id", getPackageName()));
            if (!imageView.getTag().equals(tag)) {
                flag = false;
                break;
            }

            flag = true;
        }

        return flag;
    }
}
