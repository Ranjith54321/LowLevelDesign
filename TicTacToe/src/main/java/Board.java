/**
 * Created by Ranjith S on 26/10/24.
 **/
public class Board {
    private final char[][] grid;
    private int movesCount;

    public Board() {
        this.grid = new char[3][3];
        movesCount = 0 ;
        initializeBoard();
    }

    private void initializeBoard() {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                grid[i][j] = '-';
            }
        }
    }

    public synchronized void makeMove(int row, int col, char symbol) { // allow one user at a time.
        if(row<0 || row>3 || col<0 || col>3 || grid[row][col]!='-') {
            throw new IllegalArgumentException("Please select the correct Move...");
        }
        grid[row][col] = symbol;
        movesCount++;
    }
    public boolean isFull() {
        return movesCount==9;
    }

    public boolean hasWinner() {
        // check row
        for (int row = 0; row < 3; row++) {
            if (grid[row][0] != '-' && grid[row][0] == grid[row][1] && grid[row][1] == grid[row][2]) {
                return true;
            }
        }

        // check column
        for (int col = 0; col < 3; col++) {
            if (grid[0][col] != '-' && grid[0][col] == grid[1][col] && grid[1][col] == grid[2][col]) {
                return true;
            }
        }

        // check diagonal-1 : left to right:
        if (grid[0][0] != '-' && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
            return true;
        }

        // check diagonal-2 : right to left:
        return (grid[0][2] != '-' && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]);
    }

    public void printBoard() {
        System.out.println("Board State: ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(" ====== ");
    }
}
