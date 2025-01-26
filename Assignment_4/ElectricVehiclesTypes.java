package vehicles;

public enum ElectricVehiclesTypes {
    SCOOTER,
    BIKE;

    public static ElectricVehiclesTypes convert(String type) {
        switch (type) {
            case "Scooter":
                return SCOOTER;
            case "Bike":
                return BIKE;
            default:
                return null;
        }
    }
}
