package battleship;

import java.io.Serializable;

/**
 * A single ship in a Battleship game
 */
public class Ship implements Serializable {

    public static final String SUNK_MESSAGE = "A battleship has been sunk!";
    public Cell[] cells;
    private int noHits = 0;

    public Ship(Board board, int uRow, int lCol, Orientation ort, int length) throws OverlapException,OutOfBoundsException {
        this.cells = new Cell[length];
        try {
            //Try to have cells get an owner and add them to the ships internal
            //array of what cells it carries, may throw exceptions
            if (ort == Orientation.VERTICAL) {
                if (uRow + length - 1 >= board.getHeight()) {
                    throw new OutOfBoundsException("Ship out of bounds");
                }
                for (int i = 0; i < length; i++) {
                    board.getCell(uRow + i, lCol).putShip(this);
                    cells[i] = board.getCell(uRow, i);
                }
            } else {
                if (lCol + length - 1 >= board.getWidth()) {
                    throw new OutOfBoundsException("Ship out of bounds");
                }
                for (int i = 0; i < length; i++) {
                    board.getCell(uRow, lCol + i).putShip(this);
                    cells[i] = board.getCell(i, lCol);
                }
            } //End of if statements catching exceptions
        } catch (OverlapException | OutOfBoundsException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } finally {
            board.addShip(this);
        }
    }

    public void hit(){
        this.noHits++;
        if (this.noHits == this.cells.length) {
            System.out.println(SUNK_MESSAGE);
        }
    }

    public boolean isSunk(){
        boolean check = true;
        for (Cell cell : this.cells) {
            if (cell.getHitStatus() != Cell.HIT_SHIP_SECTION && cell.getHitStatus() != Cell.SUNK_SHIP_SECTION) {
                check = false;
                break;
            }
        }
        return check;
    }
}
