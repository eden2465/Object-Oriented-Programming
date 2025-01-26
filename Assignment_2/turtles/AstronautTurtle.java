public class AstronautTurtle extends SmartTurtle {
    public void moveForward(double distance) {
        int count = (int) distance / 5;
        for (int i = 0; i <= count && distance >= 5; i++) {
            super.moveForward(5);
            if (i % 2 == 0) {
                this.tailUp();
            }
            else this.tailDown();
            distance=distance-5;
        }
        if(distance<5){
            super.moveForward(distance);
        }
    }

    public static void main(String[] args) {
        AstronautTurtle a = new AstronautTurtle();
        a.draw(4,50);
    }
}
