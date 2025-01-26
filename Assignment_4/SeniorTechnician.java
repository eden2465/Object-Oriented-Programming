package employees;

import buffers.QueueManager;
import customers.Customer;
import misc.SummaryDetails;
import misc.WorkDay;

import static java.lang.Thread.sleep;

public class SeniorTechnician extends JuniorTechnician {
    private boolean master;
    private boolean endDay = WorkDay.endDay;

    public SeniorTechnician(String ID, boolean master) {
        super(ID, 2);
        this.master = master;
    }

    public void electricVehicleCheck(Customer customer) throws InterruptedException {
        int timeOfService;
        int payment;
        sleep(1000);
        switch (customer.getVehicleType()) {
            case "Bike" -> {
                payment = (int) (Math.random() * 700 + 100);
            }
            case "Scooter" -> {
                payment = (int) (Math.random() * 450 + 50);
            }
            default -> throw new IllegalStateException("Unexpected value: " + customer.getVehicleType());
        }
        customer.setPayment(payment);

        if ((!master) && payment > 450 && Math.random() < 0.9) {
            QueueManager.getInstance().getCustomerManagerQueue().insert(customer);
            System.out.println("Senior Technician " + this.ID + " sent customer " + customer.getCustomerName() + " to Customer Manager");
        } else {
            timeOfService = customer.isReturnedFromCustomerManager() ? 1 : getTimeOfServiceByPayment(payment);
            sleep(timeOfService);
            QueueManager.getInstance().getFilesSummeryQueue().insert(new SummaryDetails(
                    payment, customer.getServiceType(), this.ID
            ));
            System.out.println("Senior Technician " + ID + " finished with customer " + customer.getCustomerName());
        }
    }

    private int getTimeOfServiceByPayment(int payment) {
        if (payment < 300)
            return 1;
        else if (payment < 450)
            return 2;
        else
            return 3;
    }

    @Override
    public void run() {
        try {
            while (!WorkDay.endDay) {
                Customer customer = null;

                customer = QueueManager.getInstance().getSeniorTechnicianQueue().extract();

                assert customer != null;
                try {
                    electricVehicleCheck(customer);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        } catch (InterruptedException e) {
        }
        System.out.println("Senior Technician " + this.ID + " finished his work");

    }
}
