package vehicles;

public class ElectricBike extends ElectricVehicle {
    private boolean isClosing;

    public ElectricBike(String modelName, int maxSpeed, int weight,int price, boolean isClosing) {
        super(modelName, maxSpeed, weight, price, ElectricVehiclesTypes.BIKE);
        this.isClosing = isClosing;
    }

    public ElectricBike(String modelName, int maxSpeed, int weight, boolean isClosing) {
        super(modelName, maxSpeed, weight, ElectricVehiclesTypes.BIKE);
        this.isClosing = isClosing;
    }


}
