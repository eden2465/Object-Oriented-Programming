public class FASquare extends AbstractSquare {


    public gameState revealSquare() {
        this.displayValue = "FA";
        revealed = true;
        return gameState.healed;
    }

    @Override
    public String toString() {
        return displayValue;
    }

}
