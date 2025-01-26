import java.lang.Math;
public class SpaceTurtle extends DrunkTurtle {
    public double randomNum (){
        return Math.random();
    }

    public void moveForward(double distance) {
        double prob=randomNum();
       if (prob<=0.5){
           super.moveForward(distance);
       }
       if (prob>0.5 && prob<=0.7){
           super.moveForwardOrg(distance);
       }
       else
           return;
    }

    public void turnRight (int degree) {
        double prob=randomNum();
        if (prob<=0.5){
            super.turnRight(degree);
        }
        if (prob>0.5 && prob<=0.7){
            super.turnRightOrg(degree);
        }
        else
            return;
    }

    public void turnLeft (int degree) {
        double prob = randomNum();
        if (prob <= 0.5) {
            super.turnLeft(degree);
        }
        if (prob > 0.5 && prob <= 0.7) {
            super.turnLeftOrg(degree);
        } else
            return;
    }
}
