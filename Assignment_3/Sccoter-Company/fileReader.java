import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class fileReader {
    private static <T> T parseLineToObject(String line, Class<T> typeOfClass) {
        if (typeOfClass == Employee.class)
            return parseLineToEmployee(line);
        else if (typeOfClass == ElectricScooter.class)
            return parseLineToElectricScooter(line);
        else if (typeOfClass == Customer.class) {
            return parseLineToCustomer(line);
        } else if (typeOfClass == ServiceCall.class) {
            return parseLineToServiceCall(line);
        }
        return null;
    }

    private static <T> T parseLineToServiceCall(String line) {
        String[] parts = line.split("[\\s\\t]+");
        int customerID = Integer.parseInt(parts[0]);
        int EmployeeID = Integer.parseInt(parts[1]);
        String typeOfService = parts[2];
        Customer customer = Company.getCustomer(customerID);
        ElectricScooter electricScooter = customer.getScooter();
        Employee employee = Company.getEmployee(EmployeeID);
        return (T) new ServiceCall(customer, employee, typeOfService, electricScooter);

    }

    private static <T> T parseLineToCustomer(String line) {
        String[] parts = line.split("[\\s\\t]+");
        int ID = Integer.parseInt(parts[0]);
        String fName = parts[1];
        int age = Integer.parseInt(parts[2]);
        char gender = parts[3].charAt(0);
        boolean helmet = Boolean.parseBoolean(parts[4]);
        boolean lock = Boolean.parseBoolean(parts[5]);
        if (parts.length == 6)
            return (T) new Customer(ID, fName, age, gender, helmet, lock);
        else {
            int ES_Number = Integer.parseInt(parts[6]);
            return (T) new Customer(ID, fName, age, gender, helmet, lock, Company.getScooter(ES_Number));
        }
    }

    private static <T> T parseLineToElectricScooter(String line) {
        String[] parts = line.split("[\\s\\t]+");

        // Check if there are enough fields in the line
        int serialNumber = Integer.parseInt(parts[0]);
        int price = Integer.parseInt(parts[1]);
        String model = parts[2];
        int maxSpeed = Integer.parseInt(parts[3]);
        if (parts.length == 4) {
            return (T) new ElectricScooter(serialNumber, price, model, maxSpeed);
        } else {
            double appVersion = Double.parseDouble(parts[4]);
            return (T) new QuickElectricScooter(serialNumber, price, model, maxSpeed, appVersion);
        }
    }

    private static <T> T parseLineToEmployee(String line) {
        String[] parts = line.split("[\\s\\t]+");

        // Check if there are enough fields in the line
        int id = Integer.parseInt(parts[0]);
        String profession = parts[1];
        String name = parts[2];
        int age = Integer.parseInt(parts[3]);
        char gender = parts[4].charAt(0);
        if (gender != 'f' || gender != 'm')
            gender = 'u'; //default option is u
        if (profession.equals("Technical")) {
            char shirtSize = parts[5].charAt(0);
            return (T) new TechnicalEmployee(id, name, age, gender, shirtSize);
        } else { //Sales
            double commRate = Double.parseDouble(parts[5]);
            char shirtSize = parts[6].charAt(0);
            return (T) new SalesEmployee(id, name, age, gender, shirtSize, commRate);
        }
    }

    public static <T> ArrayList<T> readObjectT(String filePath, Class<T> typeOfClass) {
        BufferedReader inFile = null;
        ArrayList<T> dataList = new ArrayList<T>();
        try {
            FileReader fr = new FileReader(filePath);
            inFile = new BufferedReader(fr);
            String line;
            inFile.readLine(); //skip the catagories
            while ((line = inFile.readLine()) != null) {
                dataList.add(parseLineToObject(line, typeOfClass));
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
