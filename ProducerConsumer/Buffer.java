public class Buffer {
    public static final int BUFFER_SIZE = 5;
    private final int[] buffer = new int[BUFFER_SIZE];
    private int in = 0;
    private int out = 0;
    private int count = 0;
    
    public synchronized boolean isFull() {
        return count == BUFFER_SIZE;
    }
    
    public synchronized boolean isEmpty() {
        return count == 0;
    }
    
    public synchronized void insert(int item) throws InterruptedException {
        while (isFull()) {
            System.out.println("BUFFER FULL - Producer waiting");
            wait();
        }
        
        buffer[in] = item;
        in = (in + 1) % BUFFER_SIZE;
        count++;
        System.out.printf("Producer %d inserted: %d (Buffer size: %d)%n", 
                        Thread.currentThread().getId(), item, count);
        notifyAll();
    }
    
    public synchronized int remove() throws InterruptedException {
        while (isEmpty()) {
            System.out.printf("Consumer %d - BUFFER EMPTY%n", 
                            Thread.currentThread().getId());
            wait();
        }
        
        int item = buffer[out];
        out = (out + 1) % BUFFER_SIZE;
        count--;
        System.out.printf("Consumer %d consumed: %d (Buffer size: %d)%n",
                        Thread.currentThread().getId(), item, count);
        notifyAll();
        return item;
    }
}