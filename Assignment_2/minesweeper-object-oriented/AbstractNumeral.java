public abstract class AbstractNumeral extends AbstractSquare{
    protected static int counter;
    private static int winTerm;

    public static void setWinTerm(int winTermInput){
        winTerm = winTermInput;
    }

    public static int getCounter() {
        return counter;
    }

    public static void advancedCounter(){
        counter++;
    }
    public static boolean checkWin(){
        return counter == winTerm;
    }
}
