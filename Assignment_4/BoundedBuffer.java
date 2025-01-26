package buffers;

import java.util.ArrayList;
import java.util.List;

public class BoundedBuffer<T> {
    private List<T> buffer;
    private final int N;


    public BoundedBuffer(int size){
        buffer = new ArrayList<>();
        N = size;
    }

    //insert item to the queue
    public synchronized void insert(T item){
        while (buffer.size() == N) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        buffer.add(item);
        notifyAll();
    }

    //exctract item from the queue
    public synchronized T extract() throws InterruptedException {
        while (buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
                throw new InterruptedException("Interrupted");
            }
        }

        T item = buffer.remove(0);
        notifyAll();

        return item;
    }
}