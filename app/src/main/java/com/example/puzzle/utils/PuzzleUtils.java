package com.example.puzzle.utils;

import java.util.Collections;
import java.util.ArrayList;

public class PuzzleUtils {

    public static int[] generateShuffledBoard(String mode){

        ArrayList<Integer> list = new ArrayList<>();

        for(int i=0;i<=8;i++)
            list.add(i);

        Collections.shuffle(list);

        int[] board = new int[9];

        for(int i=0;i<9;i++)
            board[i]=list.get(i);

        return board;
    }

}