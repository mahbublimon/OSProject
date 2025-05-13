import java.util.Random;

public class Consumer implements Runnable {
    private final Buffer buffer;
    private final Random random = new Random();
    
    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(1500)); // Slower consumption
                buffer.remove();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}