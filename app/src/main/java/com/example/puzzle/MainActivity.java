package com.example.puzzle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.puzzle.utils.PuzzleUtils;

public class MainActivity extends AppCompatActivity {

    RadioGroup modeGroup;
    Button playBtn;
    GridLayout previewGrid;
    TextView[] tiles = new TextView[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modeGroup = findViewById(R.id.modeGroup);
        playBtn = findViewById(R.id.playBtn);
        previewGrid = findViewById(R.id.previewGrid);

        createPreviewGrid();
        showAscending();
        playBtn.setOnClickListener(v -> {

            String mode = "ASC";

            if(modeGroup.getCheckedRadioButtonId() == R.id.descMode)
                mode = "DESC";

            int[] board;

            // Generate solvable board
            do {
                board = PuzzleUtils.generateShuffledBoard(mode);
            } while (!isSolvable(board));

            Intent intent = new Intent(MainActivity.this, GameActivity.class);

            intent.putExtra("mode", mode);
            intent.putExtra("board", board);

            startActivity(intent);
        });
        modeGroup.setOnCheckedChangeListener((group, checkedId) -> {

            if(checkedId == R.id.ascMode)
                showAscending();
            else
                showDescending();

        });
    }

    private boolean isSolvable(int[] board){

        int inversions = 0;

        for(int i=0;i<9;i++){
            for(int j=i+1;j<9;j++){

                if(board[i] != 0 && board[j] != 0 && board[i] > board[j]){
                    inversions++;
                }
            }
        }

        return inversions % 2 == 0;
    }
    private void createPreviewGrid(){

        int tileSize = getResources().getDisplayMetrics().widthPixels / 5;

        for(int i=0;i<9;i++){

            TextView tile = new TextView(this);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();

            params.width = tileSize;
            params.height = tileSize;

            params.setMargins(8,8,8,8);

            tile.setLayoutParams(params);

            tile.setGravity(Gravity.CENTER);
            tile.setTextSize(20);

            tile.setBackgroundColor(Color.LTGRAY);

            tiles[i] = tile;

            previewGrid.addView(tile);
        }
    }
    private void showAscending(){

        int[] board = {1,2,3,4,5,6,7,8,0};

        for(int i=0;i<9;i++){

            if(board[i]==0)
                tiles[i].setText("");
            else
                tiles[i].setText(String.valueOf(board[i]));
        }
    }
    private void showDescending(){

        int[] board = {8,7,6,5,4,3,2,1,0};

        for(int i=0;i<9;i++){

            if(board[i]==0)
                tiles[i].setText("");
            else
                tiles[i].setText(String.valueOf(board[i]));
        }
    }
}