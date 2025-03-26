package battleship;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The game board for the battleship simulation.
 * It also holds reference to ships for checking sunk status.
 * @author Nicholas Tibbels nst2038@rit.edu
 * @author Samuel Whitney shw9601@rit.edu
 */
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
    public Cell getCell(int row, int col){
        try {
            if (row < 0 || row >= rows || col < 0 || col >= cols) {
                throw new OutOfBoundsException(row,col);
            }
        } catch (OutOfBoundsException e) {
            System.err.println(e);
        }
        return board[row][col];
    }

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

    /**This method creates a boolean check variable that starts as true and becomes
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

    /**This method builds a string to display the current state of the board
     * with hidden ship sections*/
    public void display(PrintStream out) {
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
                sb.append(board[i][j].displayHitStatus()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    /**This method builds a string to display the current state of the board
     * without hidden ship sections*/
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
                sb.append(board[i][j].displayChar()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public String toString() {
        return "rows=" + rows + ", cols=" + cols
                + ", no of ships=" + ships.size();
    }
}
