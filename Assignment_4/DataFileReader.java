package misc;

import customers.Customer;
import vehicles.ElectricBike;
import vehicles.ElectricScooter;
import vehicles.ElectricVehicle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataFileReader {

    private static <T> List<T> parseLineToObject(String line, Class typeOfClass) {
        if (typeOfClass == Customer.class){
            List<T> list = new ArrayList<>();
            list.add((T) parseLineToCustomer(line));
            return list;
        }
        else if (typeOfClass == ElectricVehicle.class)
            return parseLineToElectricVehicle(line);
        return null;
    }
    //making a new customer by the fist line in the file
    private static <T> T parseLineToCustomer(String line) {
        String[] parts = line.split("[\\s\\t]+");
        String name = parts[0];
        String serviceType = parts[1];
        int arrivalTime = Integer.parseInt(parts[2]);
        String vehicleType = parts[3];


        switch (serviceType) {
            case "purchesing":
                return (T) new Customer(name, vehicleType, serviceType, arrivalTime);
            case "repairment":
                String model = parts[4];
                int maxSpeed = Integer.parseInt(parts[5]);
                int weight = Integer.parseInt(parts[6]);
                boolean isClosing = Boolean.parseBoolean(parts[7]);

                switch (vehicleType) {
                    case "Scooter" -> {
                        ElectricScooter electricScooter = new ElectricScooter(model, maxSpeed, weight);
                        return (T) new Customer(name, vehicleType, serviceType, arrivalTime, electricScooter);
                    }
                    case "Bike" -> {
                        ElectricBike electricBike = new ElectricBike(model, maxSpeed, weight, isClosing);
                        return (T) new Customer(name, vehicleType, serviceType, arrivalTime, electricBike);
                    }
                }
        }

        return null;
    }
    // making a vehicle from reading the file
    private static <T> List<T> parseLineToElectricVehicle(String line) {
        List<T> electricVehicles = new ArrayList<T>();
        String[] parts = line.split("[\\s\\t]+");
        String ElectricVehiclesTypes = parts[0];
        String model = parts[1];
        int maxSpeed = Integer.parseInt(parts[2]);
        int weight = Integer.parseInt(parts[3]);
        boolean isClosing = Boolean.parseBoolean(parts[4]);
        int price = Integer.parseInt(parts[5]);
        int unitInStocks = Integer.parseInt(parts[6]);
        for (int i = 0; i < unitInStocks; i++) {
            switch (ElectricVehiclesTypes) {
                case "Scooter" -> electricVehicles.add((T) new ElectricScooter(model, maxSpeed, weight, price));
                case "Bike" -> electricVehicles.add((T) new ElectricBike(model, maxSpeed, weight, price, isClosing));
            }
        }

        return electricVehicles;
    }

    public static <T> ArrayList<T> readObjectT(String filePath, Class<T> typeOfClass) {
        BufferedReader inFile = null;
        ArrayList<T> dataList = new ArrayList();
        try {
            FileReader fr = new FileReader(filePath);
            inFile = new BufferedReader(fr);
            String line;
            inFile.readLine(); //skip the catagories
            while ((line = inFile.readLine()) != null) {
                dataList.addAll(parseLineToObject(line, typeOfClass));
            }


        } catch (IOException exception) {
            System.out.println("The file " + filePath + " was not found.");
        } finally {
            try {
                assert inFile != null;
                inFile.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        return dataList;
    }

}
