import Turtle.Turtle;

import java.lang.Math;

public class DrunkTurtle extends Turtle {
    public void moveForward(double distance) {
        double randomDistance = distance * Math.random();
        super.moveForward(randomDistance);
        double prob = 0.3;
        double randomProb = Math.random();
        if (randomProb <= prob)
            turnLeft((int) distance);
        randomDistance = 0.5 * distance * Math.random();
        super.moveForward(randomDistance);
    }
    public void moveForwardOrg(double distance){
        super.moveForward(distance);
    }

    public void turnRight(int degree) {
        double randomDegree = 1.5 * degree * Math.random();
        super.turnRight((int) randomDegree);
    }

    public void turnLeft(int degree) {
        double randomDegree = 1.5 * degree * Math.random();
        super.turnLeft((int) randomDegree);
    }
    public void turnLeftOrg (int degree){
        super.turnLeft(degree);
    }
    public void turnRightOrg (int degree){
        super.turnRight(degree);
    }

    public static void main(String[] args) {
        DrunkTurtle D = new DrunkTurtle();
        D.moveForward(250);
    }
}
