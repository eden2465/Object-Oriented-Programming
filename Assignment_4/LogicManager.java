package gui;

import customers.CustomersFeeder;
import employees.*;
import misc.DataFileReader;
import vehicles.ElectricVehicle;
import vehicles.ElectricVehicleManager;

import java.util.ArrayList;
import java.util.List;


public class LogicManager {
    private final List<Clerk> clerks = new ArrayList<>();
    private final List<JuniorTechnician> juniorTechnicians = new ArrayList<>();
    private final List<SeniorTechnician> seniorTechnicians = new ArrayList<>();
    private final List<Salesman> salesmen = new ArrayList<>();
    private final List<CustomerManager> customerManagers = new ArrayList<>();
    private Cashier cashier;
    private final List<Employee> employees = new ArrayList<>();
    float timeOfService;
    int numberOfCustomerManagers;
    int numberOfCustomers;
    CustomersFeeder feeder;
    ElectricVehicleManager vehicleManager = ElectricVehicleManager.getInstance();

    public LogicManager(float timeOfService, int numberOfCustomerManagers) {
        this.timeOfService = timeOfService;
        this.numberOfCustomerManagers = numberOfCustomerManagers;
    }

    public void readFiles(String customersFilePath, String vehiclesFilePath) {
        vehicleManager.addElectricVehicles(DataFileReader.readObjectT(vehiclesFilePath, ElectricVehicle.class));
        feeder = new CustomersFeeder(DataFileReader.readObjectT(customersFilePath, customers.Customer.class));
        numberOfCustomers = feeder.getCustomerFeederSize();
    }

    public synchronized void startDay() {
        initClerks();
        initJuniorTechnicians();
        initSeniorTechnicians();
        initSalesmen();
        cashier = new Cashier("Cashier", numberOfCustomers);
        initCustomerManagers();
        employees.addAll(clerks);
        employees.addAll(juniorTechnicians);
        employees.addAll(seniorTechnicians);
        employees.addAll(salesmen);
        employees.addAll(customerManagers);
        employees.add(cashier);
        customerManagers.forEach(customerManager -> customerManager.setEmployees(employees));
        feeder.startFeeding();
        employees.forEach(Employee::start);
    }

    public void initCustomerManagers() {
        for (int i = 0; i < numberOfCustomerManagers; i++) {
            customerManagers.add(new CustomerManager("Customer Manager " + i));
        }
    }

    private void initClerks() {
        for (int i = 0; i < 2; i++) {
            clerks.add(new Clerk("Clerk " + i));
        }
    }

    private void initJuniorTechnicians() {
        for (int i = 0; i < 3; i++) {
            juniorTechnicians.add(new JuniorTechnician("Junior Technician " + i, timeOfService));
        }
    }

    private void initSeniorTechnicians() {
        seniorTechnicians.add(new SeniorTechnician("Senior Technician " + 0, true));
        seniorTechnicians.add(new SeniorTechnician("Senior Technician " + 1, false));
    }

    private void initSalesmen() {
        for (int i = 0; i < 2; i++) {
            salesmen.add(new Salesman("Salesman " + i));
        }
    }
}
