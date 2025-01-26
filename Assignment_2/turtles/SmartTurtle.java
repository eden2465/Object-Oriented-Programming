import Turtle.Turtle;
public class SmartTurtle extends Turtle {
    public void draw(int sides, double size) {
        int degree = degreeCalculator(sides);
        this.tailDown();
        for (int i = 0; i < sides; i++) {
            this.moveForward(size);
            this.turnRight(180-degree);
        }

    }

    public int degreeCalculator(int sides) {
        return 180-(360/sides);
    }

    public static void main(String[] args) {
        SmartTurtle t=new SmartTurtle();
        t.draw(8,50);
    }
}

