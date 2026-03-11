package com.example.puzzle.solver;

import java.util.*;

public class PuzzleSolver {

    static int[] goal = {1,2,3,4,5,6,7,8,0};

    public static int solve(int[] startBoard){

        PriorityQueue<Node> open =
                new PriorityQueue<>(Comparator.comparingInt(n -> n.f));

        HashSet<String> closed = new HashSet<>();

        Node start = new Node(startBoard,0,manhattan(startBoard),null);

        open.add(start);

        while(!open.isEmpty()){

            Node current = open.poll();

            if(Arrays.equals(current.board,goal)){
                return current.g;
            }

            closed.add(Arrays.toString(current.board));

            for(int[] neighbor : getNeighbors(current.board)){

                if(closed.contains(Arrays.toString(neighbor)))
                    continue;

                int g = current.g + 1;
                int h = manhattan(neighbor);

                Node child = new Node(neighbor,g,h,current);

                open.add(child);
            }
        }

        return -1;
    }

    private static int manhattan(int[] board){

        int dist = 0;

        for(int i=0;i<9;i++){

            int val = board[i];

            if(val == 0)
                continue;

            int targetRow = (val-1)/3;
            int targetCol = (val-1)%3;

            int row = i/3;
            int col = i%3;

            dist += Math.abs(row-targetRow) + Math.abs(col-targetCol);
        }

        return dist;
    }

    private static List<int[]> getNeighbors(int[] board){

        List<int[]> neighbors = new ArrayList<>();

        int zeroIndex = 0;

        for(int i=0;i<9;i++)
            if(board[i]==0)
                zeroIndex = i;

        int r = zeroIndex/3;
        int c = zeroIndex%3;

        int[][] moves = {
                {1,0},{-1,0},{0,1},{0,-1}
        };

        for(int[] m : moves){

            int nr = r + m[0];
            int nc = c + m[1];

            if(nr>=0 && nr<3 && nc>=0 && nc<3){

                int newIndex = nr*3 + nc;

                int[] newBoard = board.clone();

                newBoard[zeroIndex] = newBoard[newIndex];
                newBoard[newIndex] = 0;

                neighbors.add(newBoard);
            }
        }

        return neighbors;
    }
}