import Turtle.*;

import java.util.Scanner;

public class Army {
    static Scanner sc = new Scanner(System.in);
    public static Turtle[] Army = new Turtle[5];

    public static void main(String[] args) {
        printMenu();
        getInLine();

    }

    public static void printMenu() {
        System.out.println("Choose the type of a turtle:\n" +
                "1. Simple\n" +
                "2. Smart\n" +
                "3. Drunk\n" +
                "4. Astronaut\n" +
                "5. Alien\n" +
                "6. Space");
        for (int i = 0; i < 5; i++) {
            int section = sc.nextInt();
            switch (section) {
                case 1:
                    Army[i] = new Turtle();
                    break;
                case 2:
                    Army[i] = new SmartTurtle();
                    break;
                case 3:
                    Army[i] = new DrunkTurtle();
                    break;
                case 4:
                    Army[i] = new AstronautTurtle();
                    break;
                case 5:
                    Army[i] = new AlienTurtle();
                    break;
                case 6:
                    Army[i] = new SpaceTurtle();
                    break;
            }
        }
    }

    public static void getInLine() {
        int distance=120*4;
        for (int i = 0; i < 4; i++) {
            Army[i].tailUp();
            if(Army[i] instanceof DrunkTurtle ){

            }

            
        }
    }
}
