import gui.LogicManager;

public class Main {
    public static void main(String[] args) {
        LogicManager logicManager = new LogicManager(2,1);

        logicManager.readFiles("src\\data\\Customerscpy.txt", "src\\data\\Stock.txt");
        logicManager.startDay();
    }
}