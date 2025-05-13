public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Main <sleepTime> <numProducers> <numConsumers>");
            return;
        }
        
        int sleepTime = Integer.parseInt(args[0]);
        int numProducers = Integer.parseInt(args[1]);
        int numConsumers = Integer.parseInt(args[2]);
        
        Buffer buffer = new Buffer();
        
        // Create producers
        for (int i = 0; i < numProducers; i++) {
            new Thread(new Producer(buffer), "Producer-" + (i+1)).start();
        }
        
        // Create consumers
        for (int i = 0; i < numConsumers; i++) {
            new Thread(new Consumer(buffer), "Consumer-" + (i+1)).start();
        }
        
        // Run for specified time
        try {
            Thread.sleep(sleepTime * 1000);
            System.out.println("\n===== Simulation finished =====");
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}