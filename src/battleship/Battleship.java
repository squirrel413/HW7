package battleship;

import java.io.*;

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

    private void displayCommands(){
        StringBuilder commands = new StringBuilder();
        commands.append(HIT).append(" - Usage: h x y. Hit a cell at x row y column\n");
        commands.append(SAVE).append(" - Saves game state to a file\n");
        commands.append(REVEAL).append(" - Reveal all remaining ships and ends game\n");
        commands.append(QUIT).append(" - Quit\n");
        System.out.println(commands.toString());
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
                        System.out.println("Invalid Input");
                        break;
                    }
                case QUIT :
                    System.exit(0);
                    break;
                case SAVE :
                    try{
                        FileOutputStream file = new FileOutputStream("save.txt");
                        ObjectOutputStream out = new ObjectOutputStream(file);

                        out.writeObject(board);
                        out.close();
                        file.close();
                    }
                    catch (IOException e) {
                        System.err.println(e.getMessage() + " we errored here bestie");}
                    break;
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
