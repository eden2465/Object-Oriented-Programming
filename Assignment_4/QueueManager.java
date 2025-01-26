package buffers;

import customers.Customer;
import misc.SummaryDetails;

public class QueueManager {
    private final UnboundedBuffer<Customer> clerksQueue = new UnboundedBuffer<>();
    private final UnboundedBuffer<Customer> salesmenQueue = new UnboundedBuffer<>();
    private final UnboundedBuffer<Customer> seniorTechnicianQueue = new UnboundedBuffer<>();
    private final UnboundedBuffer<Customer> CustomerManagerQueue = new UnboundedBuffer<>();
    private final UnboundedBuffer<SummaryDetails> filesSummeryQueue = new UnboundedBuffer<>();

    private final BoundedBuffer<Customer> juniorTechnicianQueue = new BoundedBuffer<>(5);

    private static volatile QueueManager instance = null;
    private static final Object QueueManagerKey = new Object();

    private QueueManager() {
    }
    //make sure that there will no be more than one queue
    public static QueueManager getInstance() {
        if (instance == null) {
            synchronized (QueueManagerKey) {
                if (instance == null) {
                    instance = new QueueManager();
                }
            }
        }

        return instance;
    }


    public UnboundedBuffer<Customer> getClerksQueue() {
        return clerksQueue;
    }
    public int getClerkQueueSize(){
        return clerksQueue.size();
    }

    public UnboundedBuffer<Customer> getSalesmenQueue() {
        return salesmenQueue;
    }

    public UnboundedBuffer<Customer> getSeniorTechnicianQueue() {
        return seniorTechnicianQueue;
    }

    public UnboundedBuffer<Customer> getCustomerManagerQueue() {
        return CustomerManagerQueue;
    }

    public UnboundedBuffer<SummaryDetails> getFilesSummeryQueue() {
        return filesSummeryQueue;
    }

    public int getFileSummeryQueueSize(){
        return filesSummeryQueue.size();
    }

    public BoundedBuffer<Customer> getJuniorTechnicianQueue() {
        return juniorTechnicianQueue;
    }

}
