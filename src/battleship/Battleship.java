package battleship;

import java.io.*;

/**
 * A game of one-sided battleship. This class takes in a file to set up a
 * game board of Battleship. It prompts the user for input of playing battleship
 * until all ships are sunk or the user chooses to exit.
 * @author Nicholas Tibbels nst2038@rit.edu
 * @author Samuel Whitney shw9601@rit.edu
 */
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

    /**This method reads in a file to set up the game, supports game save files and set up files*/
    public Battleship(String filename) throws battleship.BattleshipException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = null;
        String[] temp = null;
        //first try loading the file as a board object
        try {
            System.out.print("Checking if data " + filename + " is a saved game file..." );
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.board = (Board) ois.readObject();
            ois.close();
            fis.close();
        //otherwise the file must be a set-up file and will read here
        } catch (Exception e) {
            System.out.print("no; will read as text setup file.\n");
            line = br.readLine();
            temp = line.split(WHITESPACE);
            int rows = Integer.parseInt(temp[0]);
            int cols = Integer.parseInt(temp[1]);
            this.board = new Board(rows, cols);
            line = br.readLine();
            while (line != null) {
                temp = line.split(WHITESPACE);
                try {
                    Ship toAdd = new Ship(this.board, Integer.parseInt(temp[0]),
                            Integer.parseInt(temp[1]),Orientation.valueOf(temp[2]), Integer.parseInt(temp[3]));
                } catch (Exception e2){
                    System.err.println(e2.getMessage());
                    System.exit(1);
                }
                line = br.readLine();
            }
        }
    }

    /**This is a helper method to display the commands and their functions.*/
    private void displayCommands(){
        StringBuilder commands = new StringBuilder();
        commands.append(HIT).append(" - Usage: h x y. Hit a cell at x row y column\n");
        commands.append(SAVE).append(" - Saves game state to a file\n");
        commands.append(REVEAL).append(" - Reveal all remaining ships and ends game\n");
        commands.append(QUIT).append(" - Quit\n");
        System.out.println(commands.toString());
    }

    /**Handles the main play loop of Battleship and prompts user for input*/
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
                        System.out.println("Invalid Input");
                        break;
                    }
                case QUIT :
                    System.exit(0);
                    break;
                case SAVE :
                    try{
                        String name = command.split(WHITESPACE)[1];
                        FileOutputStream fos = new FileOutputStream(name);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);

                        oos.writeObject(board);
                        oos.close();
                        fos.close();
                        run = false;
                        break;
                    }
                    catch (IOException e) {System.err.println(e.getMessage());}
                case REVEAL :
                    board.fullDisplay(System.out);
                    run = false;
                    break;
                case "help" :
                    displayCommands();
            }
            if (board.allSunk()){
                run = false;
                board.display(System.out);
                System.out.println(ALL_SHIPS_SUNK);
            }
        }
        System.out.println("Thanks for playing!");
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
