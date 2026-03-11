package com.example.puzzle.solver;

import java.util.Arrays;

public class Node {

    public int[] board;
    public int g;
    public int h;
    public int f;
    public Node parent;

    public Node(int[] board, int g, int h, Node parent) {
        this.board = board;
        this.g = g;
        this.h = h;
        this.f = g + h;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node)) return false;
        Node other = (Node) obj;
        return Arrays.equals(board, other.board);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
    }
}