import java.lang.Math;
import java.util.Scanner;
//second commit
//third commit and push
//forth commit from GitHub website
public class Minesweeper {
    static Scanner sc = new Scanner(System.in);
    public static int[][] hiddenBoard = new int[9][9];
    public static char[][] displayBoard = new char[9][9];
    public static boolean[][] booleanBoard = new boolean[9][9];
    public static int input;
    public static int row;
    public static int col;
    public static int reveledCounter = 0;

    public static void main(String[] args) {
        boolean gameOver = false;
        boolean programIsRunning = true;
        while (programIsRunning) { //restarting a game while the program is running
            resetHiddenBoard();//resting the hidden board for each new game
            resetDisplayBoard();//resting the display board for each new game
            printToStart();//printing the first message
            while (!gameOver) {//the loop of the game-running while the game is not over
                playersMove();//the user make his move
                if (gameStatus() == 0) {//checking if we are on a loss or win case
                    printModifiedBoard();//if not, printing the new display board according to users choice of square
                } else {//if we are on a loose or win game, printing the wanted message for the user
                    printEndMessage(gameStatus());
                    gameOver = true;//lose or win-game is over
                }
            }
            gameOver = false;//resting everything and starting a new game
        }
    }

    //this function will reset the hidden board for each game according to the rules of the game
    public static void resetHiddenBoard() {
        mineGenerator();
        mineInRange();
    }

    //this function will reset the display board for a new game
    public static void resetDisplayBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                displayBoard[i][j] = '#';
            }
        }
    }

    //this function will generate random mines according to the given algorithm (we chose -1 value for squares with mines)
    public static void mineGenerator() {
        double mineLeftCounter = 10;
        double squaresLeftCounter = 81;
        double probability;
        while (mineLeftCounter > 0) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    double random = Math.random();
                    probability = mineLeftCounter / squaresLeftCounter;
                    if (random <= probability) {
                        hiddenBoard[i][j] = -1;
                        mineLeftCounter--;
                    }
                    squaresLeftCounter--;
                }
            }
        }
    }

    /*this function is modifying the hidden board with all the values according to calculating the mines in range
      for each square*/
    public static void mineInRange() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (hiddenBoard[i][j] != -1)
                    hiddenBoard[i][j] = neighborChecker(i, j);
            }
        }
    }

    //this is a helping function for calculating the surrounding mines for each square
    public static int neighborChecker(int i, int j) {
        int neighborMineCounter = 0;
        int numRows = hiddenBoard.length;
        int numCol = hiddenBoard[0].length;
        for (int k = i - 1; k <= i + 1; k++) {
            for (int p = j - 1; p <= j + 1; p++) {
                boolean flag = (p >= 0 && p < numRows && k >= 0 && k < numCol);
                if (flag) {
                    if (hiddenBoard[k][p] == -1)
                        neighborMineCounter++;
                }
            }
        }
        return neighborMineCounter;
    }

    //printing opening game message for the user and the premoves board
    public static void printToStart() {
        System.out.println("Welcome to Fatma Minesweeper to start the game press 'enter'");
        sc.nextLine();
        printBoard();
    }

    //function that is printing the display board for the user
    public static void printBoard() {//printing display board
        System.out.println("The board:");
        for (int i = 0; i < displayBoard.length; i++) {
            for (int j = 0; j < displayBoard.length; j++) {
                System.out.print((displayBoard[i][j]) + " ");
            }
            System.out.println();
        }
    }

    //function that is printing the menu for the user and setting the wanted square to deal with at any move by the user
    public static void playersMove() {
        printMenu();
        input = sc.nextInt();
        row = (input / 10) % 10; //extracting the row number from the user's input
        col = (input % 10); // extracting the colum number from the user's input

    }

    //helping function for printing the menu of choices for the user
    public static void printMenu() {
        System.out.println();
        System.out.println("Please enter an operation number and its location\n" +
                "(1=select a square, 2= mark a mine, 3= unmark a mine)\n");
    }

    //checks after each play move if the game status is a lose or a win, else, returns zero
    public static int gameStatus() {
        if (input / 100 == 1) {
            if (checkLose())
                return 2;
            else if (checkWin(input, displayBoard))
                return 1;
        }
        return 0;
    }

    //checking if the user won
    public static boolean checkWin(int input, char[][] displayBoard) {
        return (reveledCounter == 70 && hiddenBoard[row][col]!=-1);

    }

    //checking if the user stepped on a mine and lost
    public static boolean checkLose() {
        if (hiddenBoard[row][col] == -1)
            return true;
        return false;

    }

    /*this function using other functions to modify the display board by the users choice of square
        and prints the display board accordingly*/
    public static void printModifiedBoard() {
        if (input / 100 == 1) {
            squareReveal(row, col);
            printBoard();

        }
        if (input / 100 == 2) {
            modifyDisplayBoard('&');
            printBoard();
        }
        if (input / 100 == 3) {
            modifyDisplayBoard('#');
            printBoard();
        }

    }

    //this function is printing the message wanted according to the game status (on win or lose cases)
    public static void printEndMessage(int winOrLose) {
        if (winOrLose == 1) {
            System.out.println("Congratulations, you won :)");
            return;
        } else {
            System.out.println("Boom!!! You've hit a mine and lost…");
            return;
        }

    }

    //this function is changing square value to wanted new value on the display board
    public static void modifyDisplayBoard(char newVal) {
        displayBoard[row][col] = newVal;
    }

    //this function reveals squares according to the game algorithm
    public static void squareReveal(int Row, int Col) {
        if (Row < 0 || Row >= booleanBoard.length || Col < 0 || Col >= booleanBoard[0].length) {
            return;
        }
        if (booleanBoard[Row][Col]) {
            return;
        }
        if (hiddenBoard[Row][Col] != 0) {
            booleanBoard[Row][Col] = true;
            char square = (char) (hiddenBoard[Row][Col] + '0');
            row = Row;
            col = Col;
            modifyDisplayBoard(square);
            reveledCounter++;
            return;
        }

        // recursive call to the neighboring cells
        booleanBoard[Row][Col] = true;
        row = Row;
        col = Col;
        modifyDisplayBoard(' ');
        reveledCounter++;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                squareReveal(Row + i, Col + j);

            }

        }
    }

}











