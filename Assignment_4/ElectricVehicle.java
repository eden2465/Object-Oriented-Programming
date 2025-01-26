package vehicles;

public abstract class ElectricVehicle extends Thread {
    protected String modelName;
    protected int maxSpeed;
    protected int weight;
    protected int price;

    public ElectricVehiclesTypes getType() {
        return type;
    }

    protected final ElectricVehiclesTypes type;

    public ElectricVehicle(String modelName, int maxSpeed, int weight, int price, ElectricVehiclesTypes type) {
        this.modelName = modelName;
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.price = price;
        this.type = type;
    }

    public ElectricVehicle(String modelName, int maxSpeed, int weight, ElectricVehiclesTypes type) {
        this.modelName = modelName;
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.type = type;
    }
    // natural comparing by price
    public int compareTo(Object other) {
        if (this.price > ((ElectricVehicle) other).price)
            return 1;
        else if (this.price < ((ElectricVehicle) other).price)
            return -1;
        else
            return 0;
    }
    public int getPrice (){
        return this.price;
    }

    public String getModel (){
        return this.modelName;
    }
}
