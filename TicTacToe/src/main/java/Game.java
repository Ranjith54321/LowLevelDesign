import java.util.Scanner;

/**
 * Created by Ranjith S on 26/10/24.
 **/
public class Game {
    private final Player player1;
    private final Player player2;
    private final Board board;
    private Player currentPlayer;
    private Scanner sc;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board();
        currentPlayer = player1;
        sc = new Scanner(System.in);
    }

    public void play() {
        board.printBoard();

        while(!board.isFull() && !board.hasWinner()) {
            System.out.println(currentPlayer.getName() + "'s turn");
            int row = getValidInput("Enter row value between 0 to 2: ", this.sc);
            int col = getValidInput("Enter col value between 0 to 2: ", this.sc);;

            try {
                board.makeMove(row, col, currentPlayer.getSymbol());
                board.printBoard();
                switchPlayer();
            } catch (IllegalArgumentException e) {
                System.out.println(" Error : " + e);
            }
        }
        if(board.hasWinner()) {
            switchPlayer();
           System.out.println(currentPlayer.getName() + "  Own the match!!!...");
        } else {
            System.out.println(" Match Draw...");
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2:player1;
    }

    private int getValidInput(String message, Scanner sc) { // updated this only. Not updated in UML diagram.
        int input;
        while (true) {
            System.out.print(message);
            if(sc.hasNextInt()) {
                input = sc.nextInt();
                if (input>=0 || input<=2) {
                    return input;
                }
            } else {
                sc.next();
            }
            System.out.println(" Enter Input between 0 to 2");
        }
    }

}
