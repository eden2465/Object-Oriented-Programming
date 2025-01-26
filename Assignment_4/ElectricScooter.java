package vehicles;

public class ElectricScooter extends ElectricVehicle{
    public ElectricScooter(String modelName, int maxSpeed, int weight, int price) {
        super(modelName, maxSpeed, weight, price, ElectricVehiclesTypes.SCOOTER);
    }

    public ElectricScooter(String modelName, int maxSpeed, int weight) {
        super(modelName, maxSpeed, weight, ElectricVehiclesTypes.SCOOTER);
    }
}
