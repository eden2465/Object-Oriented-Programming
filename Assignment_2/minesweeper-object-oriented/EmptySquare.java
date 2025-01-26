public class EmptySquare extends AbstractNumeral {


    public gameState revealSquare(){
        advancedCounter();
        this.displayValue = this.hiddenValue + "";
        this.revealed = true;
        for (AbstractSquare neighborsSquare : neighborsSquares) {
            if(!neighborsSquare.revealed) {
                neighborsSquare.revealSquare();
            }
        }
        if (checkWin())
            return gameState.win;
        return gameState.continuePlaying;
    }

    @Override
    public String toString() {
        return displayValue;
    }

}
