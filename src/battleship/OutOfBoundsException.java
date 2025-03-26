package battleship;

public class OutOfBoundsException extends BattleshipException {

    public static final String PAST_EDGE = "OutOfBoundsException: Coordinates are past board edge";

    public OutOfBoundsException(int row, int col) {
        super(row, col, PAST_EDGE);
    }
}
