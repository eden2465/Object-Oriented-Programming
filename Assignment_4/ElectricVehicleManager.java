package vehicles;

import java.util.ArrayList;
import java.util.List;

public class ElectricVehicleManager {
    private final List<ElectricVehicle> electricBikeList = new ArrayList();
    private final List<ElectricVehicle> getElectricScooterList = new ArrayList();

    private static volatile ElectricVehicleManager instance = null;
    private static final Object ElectricVehicleManagerKey = new Object();

    private ElectricVehicleManager() {
    }
    //make sure that will not be more than one queue of vehicle
    public static ElectricVehicleManager getInstance() {
        if (instance == null) {
            synchronized (ElectricVehicleManagerKey) {
                if (instance == null) {
                    instance = new ElectricVehicleManager();
                }
            }
        }

        return instance;
    }

    public synchronized void addElectricVehicles(List<ElectricVehicle> electricVehicle) {
        electricVehicle.forEach(this::addElectricVehicles);
    }
    //insert a vehicle
    private void addElectricVehicles(ElectricVehicle electricVehicle) {
        switch (electricVehicle.getType()) {
            case BIKE -> electricBikeList.add(electricVehicle);
            case SCOOTER -> getElectricScooterList.add(electricVehicle);
        }
    }
    //getting the minimun vehicel by price
    public synchronized <T extends ElectricVehicle> T minElectricVehicle(ElectricVehiclesTypes type) {
        T minPrice;
        switch (type) {
            case BIKE -> minPrice = getMinFromList(electricBikeList);
            case SCOOTER -> minPrice = getMinFromList(getElectricScooterList);
            default -> minPrice = null;
        }

        return minPrice;
    }

    private synchronized <T extends ElectricVehicle> T getMinFromList(List<ElectricVehicle> list) {
        list.sort(ElectricVehicle::compareTo);
        return (T) list.remove(0);
    }
}


