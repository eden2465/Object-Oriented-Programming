package employees;

import buffers.QueueManager;
import customers.Customer;
import misc.SummaryDetails;
import misc.WorkDay;

public class JuniorTechnician extends Employee {
    protected int yearsOfSeniority;
    protected float timeOfService;
    private boolean endDay = WorkDay.endDay;


    public JuniorTechnician(String ID, float timeOfService) {
        super(ID);
        this.yearsOfSeniority = 0;
        this.timeOfService = timeOfService;
    }

    @Override
    public void run() {
        try {
            while (!WorkDay.endDay) {
                Customer customer = QueueManager.getInstance().getJuniorTechnicianQueue().extract();
                try {
                    electricVehicleCheck(customer);
                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Junior Technician " + this.ID + " finished his work");
        }
    }
    //checks the vehicle
    private void electricVehicleCheck(Customer customer) throws InterruptedException {
        double customerType = Math.random();
        int payment = (int) (Math.random() * 100 + 700);
        sleep((long) timeOfService * 1000);
        if (customerType < 0.2) {
            QueueManager.getInstance().getSeniorTechnicianQueue().insert(customer);
            System.out.println("Junior Technician " + this.ID + " sent customer " + customer.getCustomerName() + " to Senior Technician");
        } else if (customerType < 0.3) {
            QueueManager.getInstance().getCustomerManagerQueue().insert(customer);
            System.out.println("Junior Technician " + this.ID + " sent customer " + customer.getCustomerName() + " to Customer Manager");
        } else if (customerType > 0.3) {
            sleep(3);
            customer.setPayment(payment);
            QueueManager.getInstance().getFilesSummeryQueue().insert(new SummaryDetails(payment, customer.getServiceType(), this.ID));
            System.out.println("Junior Technician " + this.ID + " finished with customer " + customer.getCustomerName());
        }
    }
}
