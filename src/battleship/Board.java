package battleship;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable {

    private int rows;
    private int cols;
    private Cell[][] board;
    public ArrayList<Ship> ships = new ArrayList<Ship>();

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

    /**This method returns the cell given by its position on the board
     * @return the specified cell*/
    public Cell getCell(int row, int col){return board[row][col];}

    /**This method returns the amount of rows in this board.
     * @return int rows*/
    public int getHeight() {return rows;}

    /**This method returns the amount of columns in this board.
     * @return int columns*/
    public int getWidth() {return cols;}

    /** This method adds a ship to the boards internal ships ArrayList
     * This ArrayList is only used for checking if all ships are sunk. */
    public void addShip(Ship ship) {
        this.ships.add(ship);
    }

    /**Method creates a boolean check variable that starts as true and becomes
     * false if any ships in its internal ships ArrayList have not been sunk.
     * @return check*/
    public boolean allSunk(){
        boolean check = true;
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                check = false;
                break;
            }
        }
        return check;
    }

    public void display(PrintStream out) {
        StringBuilder sb = new StringBuilder("  ");
        for (int i = 0; i < cols; i++) {
            sb.append(i).append(" ");
        }
        sb.append("\n");
        for (int i = 0; i < rows; i++) {
            sb.append(i).append(" ");
            for (int j = 0; j < cols; j++) {
                if (board[i][j].getHitStatus() == Cell.HIDDEN_SHIP_SECTION)
                    sb.append(Cell.PRISTINE_WATER).append(" ");
                else
                    sb.append(board[i][j].getHitStatus()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public void fullDisplay(PrintStream out) {
        StringBuilder sb = new StringBuilder("  ");
        //builds the number header
        for (int i = 0; i < cols; i++) {
            sb.append(i).append(" ");
        }
        sb.append("\n");
        //builds the actual board
        for (int i = 0; i < rows; i++) {
            sb.append(i).append(" ");
            for (int j = 0; j < cols; j++) {
                sb.append(board[i][j].getHitStatus()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }



    public String toString() {
        return "";
    }
}
