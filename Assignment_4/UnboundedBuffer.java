package buffers;

import java.util.ArrayList;
import java.util.List;

public class UnboundedBuffer<T> {
    private List<T> buffer;


    public UnboundedBuffer() {
        buffer = new ArrayList<>();
    }

    //insert item to the queue
    public synchronized void insert(T item) {
        buffer.add(item);
        notifyAll();
    }

    //extract item from thr queue
    public synchronized T extract() throws InterruptedException {
        while (buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new InterruptedException("Interrupted");
            }
        }

        T item = buffer.remove(0);
        notifyAll();

        return item;
    }

    public synchronized int size(){
        return buffer.size();
    }
}
