package employees;

import buffers.QueueManager;
import misc.SummaryDetails;
import misc.WorkDay;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//constractor
public class Cashier extends Employee {
    private int customerCounter = 0;
    private int numberOfCustomers;
    private FileWriter writer;
    private BufferedWriter bufferedWriter;
    private int totalPayment;
    private int techEmployeeCounter;
    private int salesEmployeeCounter;

    //for maximum helper
    private static final Map<String, Integer> hashMapSales = new HashMap<>();
    private static final Map<String, Integer> hashMapTechnician = new HashMap<>();
    private static int maximumIntSales = 0;
    private static String maximumStringSales = "";
    private static int maximumIntTech = 0;
    private static String maximumStringTech = "";

    public Cashier(String ID, int numberOfCustomers) {
        super(ID);
        this.numberOfCustomers = numberOfCustomers;
        try {
            writer = new FileWriter("Cashier.txt");
            bufferedWriter = new BufferedWriter(writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        try {
            while (!WorkDay.endDay) {
                SummaryDetails summaryDetails = QueueManager.getInstance().getFilesSummeryQueue().extract();
                try {
                    helpingEmployee(summaryDetails);
                    bufferedWriter.write(summaryDetails.toString());
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                customerCounter++;
                totalPayment(summaryDetails);
                lastCustomer();
            }
        } catch (InterruptedException e) {
            System.out.println("Cashier " + this.ID + " finished his work");
            try {
                bufferedWriter.write(summeryOfDay());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    //the cashier tells the customer manager to end the day
    private synchronized void lastCustomer() {
        if (customerCounter >= numberOfCustomers) {
            WorkDay.setLastCustomer(true);
            QueueManager.getInstance().getCustomerManagerQueue().insert(null);
        }
    }
    //total payment counter
    private int totalPayment(SummaryDetails summaryDetails) {
        return totalPayment = totalPayment + summaryDetails.getPriceForService();
    }
    // finds who was the employee that helped the most customers
    private void helpingEmployee(SummaryDetails summaryDetails) {
        String employeeName = summaryDetails.getHelpingEmployee();
        if (summaryDetails.getTypeOfService().equals("repairment")) {
            // Check if the technician is already in the map
            if (hashMapTechnician.containsKey(employeeName)) {
                // Increment the value associated with the technician's name
                int value = hashMapTechnician.get(employeeName);
                hashMapTechnician.put(employeeName, value + 1);
            } else {
                // If the technician is not in the map, add them with a count of 1
                hashMapTechnician.put(employeeName, 1);
            }

            //check maximum
            if (hashMapTechnician.get(employeeName) > maximumIntTech) {
                maximumIntTech = hashMapTechnician.get(employeeName);
                maximumStringTech = employeeName;
            }
        }
        if (summaryDetails.getTypeOfService().equals("purchesing")) {
            String SalesEmployee = summaryDetails.getHelpingEmployee();
            // Check if the technician is already in the map
            if (hashMapSales.containsKey(employeeName)) {
                // Increment the value associated with the technician's name
                int value = hashMapSales.get(employeeName);
                hashMapSales.put(employeeName, value + 1);
            } else {
                // If the technician is not in the map, add them with a count of 1
                hashMapSales.put(employeeName, 1);
            }

            //check maximum
            if (hashMapSales.get(employeeName) > maximumIntSales) {
                maximumIntSales = hashMapSales.get(employeeName);
                maximumStringSales = employeeName;
            }
        }
    }


    private String salesEmployeeOfTheDay() {
        return maximumStringSales;
    }

    private String TechEmployeeOfTheDay() {
        return maximumStringTech;
    }
    //prints in the end of the day
    private String summeryOfDay() {
        int totalCustomers = numberOfCustomers;
        int totalPayments = totalPayment;
        String sales = salesEmployeeOfTheDay();
        String tech = TechEmployeeOfTheDay();
        return ("total customers :" + totalCustomers
                + "\n total payments :" + totalPayments
                + "\n sales employee of the day :" + sales
                + "\n technician employee of the day :" + tech);
    }
}
