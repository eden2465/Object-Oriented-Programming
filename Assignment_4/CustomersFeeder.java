package customers;

import buffers.QueueManager;
import buffers.UnboundedBuffer;

import java.util.ArrayList;

public class CustomersFeeder{
    private final ArrayList<Customer> customersArray;

    public CustomersFeeder(ArrayList<Customer> customersArray) {
        this.customersArray = customersArray;
    }

    public int getCustomerFeederSize(){
        return customersArray.size();
    }


    public void startFeeding() {
            for (Customer customer : customersArray) {
                customer.start();
            }

        System.out.println("CustomersFeeder finished");
    }
}
