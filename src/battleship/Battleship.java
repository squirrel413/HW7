package battleship;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Battleship {

    static final String ALL_SHIPS_SUNK = "All ships sunk!";
    static final String BAD_ARG_COUNT = "Wrong number of arguments for command";
    static final String DIM_TOO_BIG = "Board dimensions too big to display.";
    static final String HIT = "h";
    static final int MAX_DIM = 20;
    static final String PROMPT = "> ";
    static final String QUIT = "q";
    static final String REVEAL = "!";
    static final String SAVE = "s";
    static final String WHITESPACE = "\\s+";

    public Battleship(String filename) throws battleship.BattleshipException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
    }

    public void play(){

    }

    public static void main(String[] args) throws IOException {
        try {
            Battleship game = new Battleship(args[0]);
            game.play();
        } catch (IOException | BattleshipException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
