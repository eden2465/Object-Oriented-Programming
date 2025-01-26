public class AlienTurtle extends SmartTurtle {
    public void draw(int sides, double size) {
        double randomProb = Math.random();
        if (randomProb <= 0.7) {
            super.draw(8, size);
        } else {
            super.draw(sides, size * 10);
        }
    }
}
