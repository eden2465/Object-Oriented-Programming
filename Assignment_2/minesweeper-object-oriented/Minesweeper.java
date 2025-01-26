import java.util.Scanner;

public class Minesweeper {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean programIsRunning = true;
        while (programIsRunning) {//starting new game while the program is running
            Game game = new Game();
            printMenu();
            game.startGame();//starting to run the game from the game class
        }
    }

    private static void printMenu() {
        System.out.println("Welcome to Fatma Minesweeper to start the game press 'enter'");
        sc.nextLine();
    }
}



