import static java.util.concurrent.TimeUnit.SECONDS;  // utility class

public class Clock extends Thread {
    // This field is volatile because two different threads may access it
    volatile boolean keepRunning = true;
    
    public Clock() {     // The constructor
        setDaemon(true); // Daemon thread: JVM can exit while it runs
    }
    
    public void run() {        // The body of the thread
        while(keepRunning) {   // This thread runs until asked to stop
            long now = System.currentTimeMillis();    // Get current time
            System.out.printf("%tr%n", now);          // Print it out
            try { Thread.sleep(1000); }               // Wait 1000 milliseconds
            catch (InterruptedException e) { return; }// Quit on interrupt
        }
    }
    
    // Ask the thread to stop running.  An alternative to interrupt().
    public void pleaseStop() { keepRunning = false; }
    
    // This method demonstrates how to use the Clock class
    public static void main(String[] args) {
        Clock c = new Clock();                 // Create a Clock thread
        c.start();                             // Start it
        try {  SECONDS.sleep(10); }            // Wait 10 seconds
        catch(InterruptedException ignore) {}  // Ignore interrupts
        // Now stop the clock thread.  We could also use c.interrupt()
        c.pleaseStop();
    }
}
