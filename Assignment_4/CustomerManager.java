package employees;

import buffers.QueueManager;
import customers.Customer;
import misc.SummaryDetails;
import misc.WorkDay;

import java.util.List;

public class CustomerManager extends Employee {
    private List<Employee> employees;

    public CustomerManager(String ID) {
        super(ID);
    }

    @Override
    public void run() {
        System.out.println("Customer Manager " + this.ID + " started his work");
        try {
            while (!WorkDay.endDay) {
                Customer customer = null;
                customer = QueueManager.getInstance().getCustomerManagerQueue().extract();
                if (customer != null) {
                    giveService(customer);
                    checkLastCustomer();
                } else {
                    WorkDay.endDay = true;
                    employees.remove(this);
                    employees.forEach(Thread::interrupt);
                }
            }
        } catch (InterruptedException e) {
        }
        System.out.println("Customer Manager " + this.ID + " finished his work");
    }
    //gives a customer service
    private void giveService(Customer customer) throws NullPointerException {
        double tempForPurches = Math.random();
        double tempForRepair = Math.random();
        if (customer.getServiceType().equals("repairment")) {
            if (tempForPurches < 0.1) {
                customer.setPayment(customer.getPayment() - 50);
                QueueManager.getInstance().getSeniorTechnicianQueue().insert(customer);
                System.out.println("Customer Manager " + this.ID + " sent customer " + customer.getCustomerName() + " to Senior Technician with discount");
            }
            // customers that decided to buy in another store
            if (tempForPurches < 0.4) {
                SummaryDetails summaryDetails = new SummaryDetails(0, customer.getServiceType(), "Customer Manager");
                QueueManager.getInstance().getFilesSummeryQueue().insert(summaryDetails);
                System.out.println("Customer Manager " + this.ID + " finished with customer " + customer.getCustomerName() + " and sent him to the files");
            } else {
                QueueManager.getInstance().getSeniorTechnicianQueue().insert(customer);
                System.out.println("Customer Manager " + this.ID + " sent customer " + customer.getCustomerName() + " to Senior Technician");
            }
        }

        if (customer.getServiceType().equals("purchesing")) {
            if (tempForPurches < 0.7) {
                customer.setPayment(customer.getPayment() - 100);
                QueueManager.getInstance().getSalesmenQueue().insert(customer);
                System.out.println("Customer Manager " + this.ID + " sent customer " + customer.getCustomerName() + " to Salesman with discount");
            } else {
                SummaryDetails summaryDetails = new SummaryDetails(0, customer.getServiceType(), "");
                QueueManager.getInstance().getFilesSummeryQueue().insert(summaryDetails);
                System.out.println("Customer Manager " + this.ID + " finished with customer " + customer.getCustomerName() + " and sent him to the files");
            }
        }
    }
    //make the program to stop by ending all the other thread
    private synchronized void checkLastCustomer() {
        if (WorkDay.isLastCustomer()) {
            WorkDay.endDay = true;
            notifyAll();
        }
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
