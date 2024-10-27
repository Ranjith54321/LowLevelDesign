/**
 * Created by Ranjith S on 26/10/24.
 **/
public class TicTacToeDemo {
    public static void run() {
        Player player1 = new Player("Player1", 'X');
        Player player2 = new Player("Player2", 'O');


        Game game = new Game(player1, player2);
        game.play();
    }
}
