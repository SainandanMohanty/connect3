package com.example.sain.connect3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turn = false;

        for (int i = 0; i < 9; i++) {
            TextView textView = findViewById(getResources().getIdentifier("textView" + (i + 2), "id", getPackageName()));
            textView.setTag(-1);
        }
    }


    public void onClick(View view) {
        TextView textView = (TextView) view;
        int tag = (int) textView.getTag();

        if (tag == -1) {
            textView.setY(-2000);
            if (turn) {
                textView.setText("O");
                textView.setTag(0);
            } else {
                textView.setText("X");
                textView.setTag(1);
            }
            textView.animate().translationYBy(2000).alpha(1).setDuration(1000);

            if (gameOver(textView)) {
                TextView textView1 = findViewById(R.id.textView);

                if (!turn) {
                    textView1.setText("X has won");
                } else {
                    textView1.setText("O has won");
                }

                restart();
            } else {
                turn = !turn;
            }
        }
    }

    public void onClickRestart(View view) {
        restart();
    }

    private void restart() {
        TextView textView;
        for (int i = 0; i < 9; i++) {
            textView = findViewById(getResources().getIdentifier("textView" + (i + 2), "id", getPackageName()));
            textView.setText(null);
            textView.setTag(-1);
        }
    }

    private boolean gameOver(TextView textView0) {

        TextView textView;
        boolean flag = true;
        Object tag = textView0.getTag();
        int id = Integer.parseInt(getResources().getResourceName(textView0.getId()).substring(37)) - 2;

        for (int i = (id / 3) * 3; i < ((id / 3) * 3) + 3; i++) {
            textView = findViewById(getResources().getIdentifier("textView" + (i + 2), "id", getPackageName()));
            if (!textView.getTag().equals(tag)) {
                flag = false;
                break;
            }
        }

        if (flag) {
            return flag;
        }

        for (int i = id % 3; i < (id % 3) + 9; i += 3) {
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
