package com.example.puzzle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.puzzle.solver.PuzzleSolver;

public class GameActivity extends AppCompatActivity {

    GridLayout grid;
    TextView stepsTxt;
    TextView optimalTxt;
    Button newGameBtn;

    int steps = 0;

    int[] board;

    Button[] tiles = new Button[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        grid = findViewById(R.id.grid);
        stepsTxt = findViewById(R.id.stepsTxt);
        optimalTxt = findViewById(R.id.optimalTxt);

        board = getIntent().getIntArrayExtra("board");

        int optimalSteps = PuzzleSolver.solve(board);

        optimalTxt.setText("Optimal: " + optimalSteps);
        newGameBtn = findViewById(R.id.newGameBtn);

        newGameBtn.setOnClickListener(v -> {

            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        });

        createGrid();
    }

    private void createGrid() {

        grid.removeAllViews();

        for(int i = 0; i < 9; i++) {

            Button btn = new Button(this);
            btn.setBackgroundResource(R.drawable.tile_bg);
            btn.setElevation(8);
            btn.setTextSize(24);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();

            params.width = 200;
            params.height = 200;

            btn.setLayoutParams(params);

            btn.setTextSize(24);

            tiles[i] = btn;

            int index = i;

            btn.setOnClickListener(v -> moveTile(index));

            grid.addView(btn);
        }

        updateBoard();
    }

    private void updateBoard(){

        for(int i=0;i<9;i++){

            if(board[i]==0){
                tiles[i].setText("");
                tiles[i].setBackgroundColor(Color.TRANSPARENT);
            }
            else{
                tiles[i].setBackgroundResource(R.drawable.tile_bg);
                tiles[i].setText(String.valueOf(board[i]));
            }
        }
    }

    private void moveTile(int index){

        int emptyIndex = findEmpty();

        int r1 = index / 3;
        int c1 = index % 3;

        int r2 = emptyIndex / 3;
        int c2 = emptyIndex % 3;

        if(Math.abs(r1-r2) + Math.abs(c1-c2) == 1){

            int temp = board[index];
            board[index] = board[emptyIndex];
            board[emptyIndex] = temp;

            steps++;

            stepsTxt.setText("Steps: " + steps);

            updateBoard();

            if(checkWin()){
                playWinAnimation();
                Toast.makeText(this,"Puzzle Solved!",Toast.LENGTH_LONG).show();
            }
        }
    }
    void playWinAnimation() {

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.win_bounce);

        for(int i = 0; i < grid.getChildCount(); i++){
            View tile = grid.getChildAt(i);
            tile.startAnimation(anim);
        }

    }

    private int findEmpty(){

        for(int i=0;i<9;i++){
            if(board[i]==0)
                return i;
        }

        return -1;
    }

    private boolean checkWin(){

        for(int i=0;i<8;i++){
            if(board[i] != i+1)
                return false;
        }

        return board[8] == 0;
    }
}