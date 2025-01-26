package employees;

import buffers.QueueManager;
import buffers.UnboundedBuffer;
import customers.Customer;
import misc.WorkDay;


public class Clerk extends Employee {
    private final UnboundedBuffer<Customer> clerksQueue = QueueManager.getInstance().getClerksQueue();

    public Clerk(String ID) {
        super(ID);
    }


    @Override
    public void run() {
        try {
            while (!WorkDay.endDay) {
                Customer customer = null;

                customer = clerksQueue.extract();

                helpCustomer(customer);
                sendToQueue(customer);
            }
        } catch (InterruptedException e) {

        }
        System.out.println("Clerk " + ID + " finished");
    }
    //directing the customers for each queue by their service type
    private void sendToQueue(Customer customer) {
        switch (customer.getServiceType()) {
            case "purchesing" -> {
                QueueManager.getInstance().getSalesmenQueue().insert(customer);
                System.out.println("Clerk " + ID + " sent customer " + customer.getCustomerName() + " to Salesman");
            }
            case "repairment" -> {
                QueueManager.getInstance().getJuniorTechnicianQueue().insert(customer);
                System.out.println("Clerk " + ID + " sent customer " + customer.getCustomerName() + " to Junior Technician");
            }
            case default -> throw new RuntimeException("Invalid service type");
        }
    }

    //helping customer by the given algorithm
    private synchronized void helpCustomer(Customer customer) {
        double serviceTime = Math.random() * 3 + 3;
        try {
            sleep((long) serviceTime * 1000);
            System.out.println("Clerk " + ID + " helped customer " + customer.getId() + " for " + String.format("%.2f", serviceTime) + " seconds");
        } catch (InterruptedException e) {
        }

    }
}
