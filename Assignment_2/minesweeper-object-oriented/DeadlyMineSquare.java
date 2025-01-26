public class DeadlyMineSquare extends AbstractSquare {

    public gameState revealSquare(){
        this.displayValue = "X ";
        revealed = true;
        return gameState.lose;
    }

    @Override
    public String toString() {
        return displayValue;
    }
}
