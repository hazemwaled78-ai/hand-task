public class ConsolidatedExceptionHandlerDemo {

    public static void main(String[] args) throws InterruptedException {

        // 3. Default (Global) Handler - Lowest Priority
        // This will catch any uncaught exception from any thread that doesn't 
        // have a specific handler.
        Thread.setDefaultUncaughtExceptionHandler((thread, ex) -> {
            System.out.println("--- [GLOBAL Default Handler] Caught exception in: " + thread.getName() + " ---");
            System.out.println("    Exception: " + ex.getMessage());
            System.out.println("----------------------------------------------------------");
        });

        // --- Thread 1: Internal Handling (Highest Priority) ---
        // The exception is handled internally. External handlers (per-thread or default) 
        // will never be called.
        Thread t1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " started...");
                int result = 10 / 0; // Will cause ArithmeticException
            } catch (ArithmeticException e) {
                // 1. Handled locally
                System.out.println("[Internal Handler] in " + Thread.currentThread().getName() + " caught: " + e.getMessage());
            }
            System.out.println(Thread.currentThread().getName() + " finished.");
        }, "Thread-1 (InternalTryCatch)");

        // --- Thread 2: Per-Thread Handler (Medium Priority) ---
        // This thread does not handle the exception internally.
        // It has a specific handler assigned to it.
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started...");
            // This exception will not be caught locally
            throw new RuntimeException("Intentional exception for Thread-2");
        }, "Thread-2 (PerThreadHandler)");

        // 2. Set a specific handler for t2 only
        t2.setUncaughtExceptionHandler((thread, ex) -> {
            System.out.println("--- [PER-THREAD Handler] Caught exception in: " + thread.getName() + " ---");
            System.out.println("    Exception: " + ex.getMessage());
            System.out.println("----------------------------------------------------------");
        });

        // --- Thread 3: Default Handler (Lowest Priority) ---
        // This thread does not handle the exception internally.
        // It also does not have a specific per-thread handler.
        // Therefore, the global default handler set at the beginning will catch it.
        Thread t3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started...");
            // This exception will not be caught locally
            Integer.parseInt("Not a number"); // Will cause NumberFormatException
        }, "Thread-3 (DefaultHandler)");

        // Start the threads
        t1.start();
        Thread.sleep(100); // Just to format the console output
        t2.start();
        Thread.sleep(100);
        t3.start();
    }
}