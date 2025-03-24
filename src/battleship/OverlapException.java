package battleship;

public class OverlapException extends Exception {

    public OverlapException() {
        super();
    }

    public OverlapException(String message) {
        super(message + ", overlapping ships!");
    }
}
