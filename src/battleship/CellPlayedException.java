package battleship;

public class CellPlayedException extends BattleshipException {

    public static final String ALREADY_HIT = "CellPlayedException: This cell has already been hit";

    public CellPlayedException(int row, int col) {
        super(row, col, ALREADY_HIT);
    }
}
