import java.util.Random;

public class Producer implements Runnable {
    private final Buffer buffer;
    private final Random random = new Random();
    
    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(500)); // Faster production
                int item = random.nextInt(10000);
                buffer.insert(item);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}