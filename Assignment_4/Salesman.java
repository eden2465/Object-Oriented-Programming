package employees;

import buffers.QueueManager;
import customers.Customer;
import misc.SummaryDetails;
import misc.WorkDay;
import vehicles.ElectricVehicle;
import vehicles.ElectricVehicleManager;
import vehicles.ElectricVehiclesTypes;

public class Salesman extends Employee {

    private boolean endOfDay = WorkDay.endDay;

    public Salesman(String ID) {
        super(ID);
    }

    public <T extends ElectricVehicle> T findMinPrice(ElectricVehiclesTypes type) {
        return ElectricVehicleManager.getInstance().minElectricVehicle(type);
    }

    @Override
    public void run() {
        while (!WorkDay.endDay) {
            try {
                Customer customer = QueueManager.getInstance().getSalesmenQueue().extract();
                int talkTime = customer.isReturnedFromCustomerManager() ? 1500 : 3000;
                sleep(talkTime);
                if (Math.random() < 0.13) {
                    QueueManager.getInstance().getCustomerManagerQueue().insert(customer);
                    System.out.println("Salesman " + this.ID + " sent customer " + customer.getCustomerName() + " to Customer Manager");
                } else {
                    // salse the vehicle with minimum price
                    ElectricVehicle electricVehicle = findMinPrice(ElectricVehiclesTypes.convert(customer.getVehicleType()));
                    customer.setPayment(electricVehicle.getPrice());
                    QueueManager.getInstance().getFilesSummeryQueue().insert(new SummaryDetails(
                            electricVehicle.getPrice(), customer.getServiceType(), this.ID
                    ));
                    System.out.println("Salesman " + this.ID + " finished with customer " + customer.getCustomerName());
                }
            } catch (InterruptedException e) {
                System.out.println("Salesman " + this.ID + " finished his work");
                break;
            }
        }
    }


}
