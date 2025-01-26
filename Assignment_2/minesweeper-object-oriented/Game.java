import java.util.Scanner;

public class Game {
    private boolean playerInjured = false;
    private Board board;
    private static Scanner sc = new Scanner(System.in);
    private int input = 0;
    private int row = 0;
    private int col = 0;
    private static int winTerm = 70;
    boolean gameOver = false;

    public void startGame() {
        board = new Board();//setting new board for each game
        AbstractNumeral.setWinTerm(winTerm); //so the squares know when do we win
        printBoard();

        while (!gameOver) {
            //player chooses what mode
            gameState result = playersMove();
            switch (result) {
                case injured:
                    if (playerInjured)
                        endGame(gameState.lose);
                    else
                        playerInjured = true;
                    break;

                case win:
                case lose:
                    endGame(result);
                    break;

                case healed:
                    playerInjured = false;
                case continuePlaying:
                case flag:
                    printBoard();
                    break;
            }
        }
    }

    private void endGame(gameState result) {
        printEndMessage(result);
        printAllBoard();
        gameOver = true;
    }

    private void printBoard() {//using the toString method from the board class
        System.out.println(board);
    }

    private void printAllBoard() {
        board.revealAllBoard();
        printBoard();
    }

    //printing the default menu
    private void printMenu() {
        System.out.println();
        System.out.println("Please enter an operation number and its location\n" +
                "(1=select a square, 2= mark a mine, 3= unmark a mine)\n");
    }

    //calculating the move by the player
    private gameState playersMove() {
        printMenu();
        input = sc.nextInt();
        this.row = (input / 10) % 10; //extracting the row number from the user's input
        this.col = (input % 10); // extracting the colum number from the user's input

        if (input / 100 == 1)
            return board.revealBoardSquare(row, col);
        if (input / 100 == 2)
            board.flagBoardSquare(row, col, "&");
        if (input / 100 == 3)
            board.flagBoardSquare(row, col, "#");
        return gameState.flag;
    }

    public void printEndMessage(gameState result) {
        if (result == gameState.win) {
            System.out.println("Congratulations, you won :)");
        } else if (result == gameState.lose) {
            System.out.println("Boom!!! You've hit a mine and lost…");
        }
    }
}



