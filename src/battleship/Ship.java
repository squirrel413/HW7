package battleship;

import java.io.Serializable;

/**
 * A single ship in a Battleship game
 */
public class Ship implements Serializable {

    public static final String SUNK_MESSAGE = "A battleship has been sunk!";
    public Cell[] cells;

    public Ship(Board board, int uRow, int lCol, Orientation ort, int length) throws OverlapException,OutOfBoundsException {
        this.cells = new Cell[length];
    }

    public void hit(){

    }

}
