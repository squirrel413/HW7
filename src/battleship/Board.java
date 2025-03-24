package battleship;

import java.io.PrintStream;
import java.io.Serializable;

public class Board implements Serializable {

    private int rows;
    private int cols;
    private Cell[][] board;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new Cell(i,j);
            }
        }
    }

    public Cell getCell(int row, int col){return board[row][col];}

    public int getHeight() {return rows;}

    public int getWidth() {return cols;}

    public void addShip(Ship ship) {

    }

    public boolean allSunk(){
        return false;
    }

    public void display(PrintStream out) {

    }

    public void fullDisplay(PrintStream out) {

    }



    public String toString() {
        return "";
    }
}
