package battleship;

import javax.swing.*;
import java.io.Serializable;

/**
 * A single spot on the Battleship game board.
 * A cell knows if there is a ship on it, and it remembers
 * if it has been hit.
 */
public class Cell implements Serializable {

    /** Character to display for a ship that has been entirely sunk */
    public static final char SUNK_SHIP_SECTION = '*';

    /** Character to display for a ship that has been hit but not sunk */
    public static final char HIT_SHIP_SECTION = '☐';

    /** Character to display for a water cell that has been hit */
    public static final char HIT_WATER = '.';

    /**
     * Character to display for a water cell that has not been hit.
     * This character is also used for an unhit ship segment.
     */
    public static final char PRISTINE_WATER = '_';

    /**
     * Character to display for a ship section that has not been
     * sunk, when revealing the hidden locations of ships
     */
    public static final char HIDDEN_SHIP_SECTION = 'S';

    public final int row;
    public final int col;
    public Ship owner;
    public char hitStatus = PRISTINE_WATER;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void putShip(Ship ship) throws OverlapException{
        try {
            if (this.owner == null) {
                this.owner = ship;
                this.hitStatus = HIDDEN_SHIP_SECTION;
            } else {
                throw new OverlapException("Ship already exists!");
            }
        } catch (OverlapException e) {
            System.exit(1);
        }
    }

    public char getHitStatus() {
        return this.hitStatus;
    }

    public void setHitStatus(char hitStatus) {
        this.hitStatus = hitStatus;
    }

    public void hit() throws CellPlayedException {
        try {
            if (this.hitStatus != PRISTINE_WATER && this.hitStatus != HIDDEN_SHIP_SECTION) {
                throw new CellPlayedException("Cell has been played!");
            } else {
                switch (this.hitStatus) {
                    case (PRISTINE_WATER) :
                        setHitStatus(HIT_WATER);
                        break;
                    case (HIDDEN_SHIP_SECTION) :
                        setHitStatus(HIT_SHIP_SECTION);
                        break;
                }
                this.owner.hit();
            }
        } catch(CellPlayedException e) {
            System.err.println(e.getMessage());
        }
    }
}
