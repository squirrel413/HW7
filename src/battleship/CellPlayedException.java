package battleship;

public class CellPlayedException extends RuntimeException {
    public CellPlayedException(String message) {
        super(message);
    }
}
