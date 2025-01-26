package customers;

import buffers.QueueManager;
import buffers.UnboundedBuffer;
import misc.WorkDay;
import vehicles.ElectricVehicle;

public class Customer extends Thread {
    private String name;
    private String vehicleType;
    private final String serviceType;

    public String getCustomerName() {
        return name;
    }

    private int arrivalTime;
    private int payment;
    private ElectricVehicle electricVehicle;
    private boolean returnedFromCustomerManager = false;

    private final UnboundedBuffer<Customer> clerksQueue = QueueManager.getInstance().getClerksQueue();


    public Customer(String name, String vehicleType, String serviceType, int arrivalTime, ElectricVehicle electricVehicle) {
        this.name = name;
        this.vehicleType = vehicleType;
        this.serviceType = serviceType;
        this.arrivalTime = arrivalTime;
        this.electricVehicle = electricVehicle;
    }

    public Customer(String name, String vehicleType, String serviceType, int arrivalTime) {
        this.name = name;
        this.vehicleType = vehicleType;
        this.serviceType = serviceType;
        this.arrivalTime = arrivalTime;
    }

    public void run() {
        try {
            sleep(arrivalTime * 1000);
            clerksQueue.insert(this);
            System.out.println("Customer " + name + " arrived at " + arrivalTime + " seconds");
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }

    }

    public int getPayment() {
        return this.payment;
    }

    public int setPayment(int payment) {
        return this.payment = payment;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public String getElectricVehicleModel() {
        return this.electricVehicle.getModel();
    }

    public String getVehicleType() {
        return vehicleType;
    }

    //finding out if this customer got customer maneger "treatment"
    public boolean isReturnedFromCustomerManager() {
        return returnedFromCustomerManager;
    }

    public void setReturnedFromCustomerManager(boolean returnedFromCustomerManager) {
        this.returnedFromCustomerManager = returnedFromCustomerManager;
    }
}
