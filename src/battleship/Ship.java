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
        try {
            //First try to have cells get an owner and add them to the ships internal
            //array of what cells it carries
            if (ort == Orientation.VERTICAL) {
                if (uRow + length - 1 >= board.getHeight()) {
                    throw new OutOfBoundsException("Ship out of bounds");
                }
                for (int i = uRow; i < length; i++) {
                    if (board.getCell(uRow, i).owner != null) {
                        board.getCell(uRow, i).putShip(this);
                        cells[i] = board.getCell(uRow, i);
                    } else {
                        throw new OverlapException("Cell already claimed!");
                    }
                }
            } else {
                if (lCol + length - 1 >= board.getWidth()) {
                    throw new OutOfBoundsException("Ship out of bounds");
                }
                for (int i = lCol; i < length; i++) {
                    if (board.getCell(i, lCol).owner != null) {
                        board.getCell(i, lCol).putShip(this);
                        cells[i] = board.getCell(i, lCol);
                    } else {
                        throw new OverlapException("Cell already claimed!");
                    }
                }
            } //End of if statements catching exceptions

        } catch (OverlapException | OutOfBoundsException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public void hit(){

    }

}
