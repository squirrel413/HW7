package battleship;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public Board board;

    public Battleship(String filename) throws battleship.BattleshipException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine();
        String[] temp = line.split(WHITESPACE);
        int rows = Integer.parseInt(temp[0]);
        int cols = Integer.parseInt(temp[1]);
        this.board = new Board(rows, cols);
        line = br.readLine();
        while (line != null) {
            temp = line.split(WHITESPACE);
            try {
                Ship toAdd = new Ship(this.board, Integer.parseInt(temp[0]),
                        Integer.parseInt(temp[1]),Orientation.valueOf(temp[2]), Integer.parseInt(temp[3]));
            } catch (Exception e){
                System.err.println(e.getMessage());
                System.exit(1);
            }
            line = br.readLine();
        }
    }

    public void play(){
        boolean run = true;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String command = "";
        while (run){
            board.display(System.out);
            System.out.print(PROMPT);
            try {
                command = input.readLine();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
            String temp = command.split(WHITESPACE)[0];
            switch (temp) {
                case HIT :
                    try {
                        board.getCell(Integer.parseInt(command.split(WHITESPACE)[1])
                                ,Integer.parseInt(command.split(WHITESPACE)[2])).hit();
                        break;
                    } catch (Exception e){
                        System.err.println(e.getMessage());
                        System.exit(1);
                    }
                case QUIT :
                    System.exit(0);
                    break;
                case SAVE :
                    //serialize the stuff
                    break;
                case REVEAL :
                    board.fullDisplay(System.out);
                    run = false;
                    break;
            }
            run = !board.allSunk();
        }

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
