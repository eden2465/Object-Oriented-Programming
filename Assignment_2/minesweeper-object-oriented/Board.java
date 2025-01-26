import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Board {
    private static final int BOARD_SIZE = 9;
    public static final Random random = new Random();
    private static final int NUM_OF_ACTIVE_SQUARES = 10;
    private final AbstractSquare[][] gameBoard = new AbstractSquare[BOARD_SIZE][BOARD_SIZE];
    private final List<AbstractSquare> activeSquaresInBoard = new ArrayList<>();


    public Board() {
        initEmptyBoard();
        placeActiveSquares();
        initNeighbors();
        initNumsSquares();
    }

    public gameState revealBoardSquare(int row, int col) {
        return gameBoard[row][col].revealSquare();
    }

    private void initEmptyBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                gameBoard[row][col] = new EmptySquare();
            }
        }
    }

    private void initNeighbors() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                iterateOverNeighbors(row, col);
            }
        }
    }

    private void iterateOverNeighbors(int row, int col) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (squareIsInBounds(row + i, col + j)) {
                    gameBoard[row + i][col + j].addNeighbor(gameBoard[row][col]);
                }
            }
        }
    }

    private boolean squareIsInBounds(int row, int col) {
        return row >= 0 && row < BOARD_SIZE & col >= 0 && col < BOARD_SIZE;
    }

    private void placeActiveSquares() {
        double ActiveSquareLeft = NUM_OF_ACTIVE_SQUARES;
        double squaresLeftCounter = BOARD_SIZE * BOARD_SIZE;
        double probability;
        int FACounter = 0, DMCounter = 0, IMCounter = 0;
        LinkedList<AbstractSquare> choosingList;
        while (ActiveSquareLeft > 0) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    double random = Math.random();
                    probability = ActiveSquareLeft / squaresLeftCounter;
                    if (random <= probability) {
                        choosingList = new LinkedList<>();
                        if (FACounter < 4)
                            choosingList.add(new FASquare());
                        if (DMCounter < 4)
                            choosingList.add(new DeadlyMineSquare());
                        if (IMCounter < 4)
                            choosingList.add(new InjureMineSquare());
                        gameBoard[i][j] = getRandActive(choosingList);

                        if (gameBoard[i][j] instanceof FASquare)
                            FACounter++;
                        if (gameBoard[i][j] instanceof DeadlyMineSquare)
                            DMCounter++;
                        if (gameBoard[i][j] instanceof InjureMineSquare)
                            IMCounter++;

                        activeSquaresInBoard.add(gameBoard[i][j]);
                        ActiveSquareLeft--;
                        locateNumsSquares(i, j);
                    }

                    squaresLeftCounter--;
                }
            }
        }
    }

    private AbstractSquare getRandActive(List<AbstractSquare> choosingList) {
        return choosingList.get(random.nextInt(choosingList.size())); //chooses in random between the available active squares
    }

    private void locateNumsSquares(int row, int col) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (squareIsInBounds(row + i, col + j) &&
                        gameBoard[row][col] != gameBoard[row + i][col + j] &&
                        gameBoard[row+i][col+j] instanceof EmptySquare) {
                    gameBoard[row + i][col + j] = new NumSquare();
                }
            }
        }
    }


    private void initNumsSquares() {
        for (AbstractSquare activeSquare : activeSquaresInBoard) {
            for (AbstractSquare numSquare : activeSquare.neighborsSquares) {
                numSquare.hiddenValue++;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                sb.append(gameBoard[i][j].toString());
                sb.append("  ");
            }
            sb.append('\n');
        }

        return sb.toString();
    }

    public void revealAllBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                gameBoard[i][j].revealSquare();
            }
        }
    }


    public void flagBoardSquare(int row, int col, String value) {
        gameBoard[row][col].flagSquare(value);
    }

//    public boolean DMchecker(int row, int col){
//        return gameBoard[row][col] instanceof DeadlyMineSquare;
//
//    }
//    public boolean IMchecker(int row, int col){
//        return gameBoard[row][col] instanceof InjureMineSquare;
//    }
//    public boolean FAchecker(int row, int col) {
//        return gameBoard[row][col] instanceof FASquare;
//    }
//    public boolean NumeralChecker(int row, int col) {
//        return gameBoard[row][col] instanceof AbstractNumeral;
//    }
}
