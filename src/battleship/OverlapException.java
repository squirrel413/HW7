package battleship;

public class OverlapException extends BattleshipException {

    public static final String OVERLAP = "OverlapException: Ships placed in overlapping positions";

    public OverlapException(int row, int col) {
        super(row, col, OVERLAP);
    }
}
