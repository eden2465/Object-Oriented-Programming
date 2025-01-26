public class NumSquare extends AbstractNumeral {

    public gameState revealSquare() {
        advancedCounter();
        this.displayValue = this.hiddenValue + "";
        revealed = true;
        if (checkWin())
            return gameState.win;
        return gameState.continuePlaying;
    }

    @Override
    public String toString() {
        return this.displayValue;
    }
}
