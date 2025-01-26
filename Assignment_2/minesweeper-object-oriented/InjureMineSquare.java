public class InjureMineSquare extends AbstractSquare {

    public gameState revealSquare() {
        this.displayValue = "IN";
        revealed = true;
        return gameState.injured;
    }

    @Override
    public String toString() {
        return displayValue;
    }
}
