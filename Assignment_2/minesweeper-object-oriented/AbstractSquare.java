import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSquare {
    protected int hiddenValue;
    protected String displayValue = "#";
    protected boolean revealed;
    protected final List<AbstractSquare> neighborsSquares = new ArrayList<>();

    public String toString(){
        return ""+ hiddenValue; //TODO: should be display
    }

    public abstract gameState revealSquare();

    public void addNeighbor(AbstractSquare newNeighbor){
        if (newNeighbor != this) {
            neighborsSquares.add(newNeighbor);
        }
    }
    public  void flagSquare (String value){
        this.displayValue=value;
    }



}

