package com.example.sain.connect3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    boolean turn;
    List<Cell> cellList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turn = false;
        cellList.clear();

        for (int i = 0; i < 9; i++) {
            Cell cell = new Cell();
            cell.id = getResources().getIdentifier("textView" + (i + 2), "id", getPackageName());
            cell.status = 0;
            cellList.add(cell);
        }
    }


    public void onClick(View view) {
        TextView textView = (TextView) view;

        int id = Integer.parseInt(getResources().getResourceName(textView.getId()).substring(37)) - 2;

        if (cellList.get(id).status == 0) {
            textView.setY(-2000);
            if (turn) {
                textView.setText("O");
                cellList.get(id).status = 1;
            } else {
                textView.setText("X");
                cellList.get(id).status = 2;
            }
            textView.animate().translationYBy(2000).alpha(1).setDuration(1000);

            if (gameOver(id)) {
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
            textView = findViewById(cellList.get(i).id);
            textView.setText(null);
            cellList.get(i).status = 0;
        }
    }

    private boolean gameOver(int id) {

        boolean flag = true;
        int status = cellList.get(id).status;

        for (int i = (id / 3) * 3; i < ((id / 3) * 3) + 3; i++) {
            if (cellList.get(i).status != status) {
                flag = false;
                break;
            }
        }

        if (flag) {
            return flag;
        }

        for (int i = id % 3; i < (id % 3) + 9; i += 3) {
            if (cellList.get(i).status != status) {
                flag = false;
                break;
            }

            flag = true;
        }

        return flag;
    }

    class Cell {
        int status;
        int id;
    }
}
