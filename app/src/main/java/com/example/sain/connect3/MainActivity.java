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

        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 3; j++) {
                Cell cell = new Cell();
                cell.positionY = i;
                cell.positionX = j;
                cell.id = getResources().getIdentifier("textView" + (i + j + 2), "id", getPackageName());
                cellList.add(cell);
            }
        }
    }

    public void onClick(View view) {
        TextView textView = (TextView) view;

        int id = Integer.parseInt(getResources().getResourceName(textView.getId()).substring(37));

        if (!cellList.get(id - 2).clicked) {
            textView.setY(-2000);
            if (!turn) {
                textView.setText("X");
            } else {
                textView.setText("O");
            }
            textView.animate().translationYBy(2000).alpha(1).setDuration(1000);

            cellList.get(id - 2).clicked = true;
            cellList.get(id - 2).status = turn;

            turn = !turn;
        }
    }

    public void onClickRestart(View view) {
        TextView textView;
        for (int i = 0; i < 9; i++) {
            textView = findViewById(cellList.get(i).id);
            textView.setText(null);
            cellList.get(i).clicked = false;
        }
    }

    class Cell {
        int positionX;
        int positionY;
        boolean clicked = false;
        boolean status;
        int id;
    }
}
