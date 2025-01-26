public class ElectricScooter implements Comparable, Updatable {
    protected int serialNumber;
    protected int price;
    protected String model;
    protected int maxSpeed;
    protected int popularPriceUpdate = 10;

    public ElectricScooter(int serialNumber, int price, String model, int maxSpeed) {
        this.serialNumber = serialNumber;
        if (price < 0)
            throw new IncorrectPriceException("insert a positive integer");
        else
            this.price = price;
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    public int getSerialNumber(){
        return this.serialNumber;
    }
    public int getPrice() {
        return this.price;
    }

    public int getMaxSpeed(){
        return this.maxSpeed;
    }

    public boolean update() {
//        if (Company.popular()) {
//            this.price = this.price * popularPriceUpdate / 100;
//            return true;
//        }
        return false;
    }

    public void technicalTreatment() {
        System.out.println("model:" + this.model + "\n" + "max speed" + this.maxSpeed + "\n");
    }

    public int compareTo(Object other) {
        if (this.price > ((ElectricScooter) other).price)
            return 1;
        else if (this.price < ((ElectricScooter) other).price)
            return -1;
        else
            return 0;
    }


}
