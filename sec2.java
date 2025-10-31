// Method 1: Create a class by extending the Thread class
class WorkerThread extends Thread {

    /**
     * The run() method contains the code that will execute in the new thread.
     */
    @Override
    public void run() {
        System.out.println("Method 1: Hello from a thread created by 'extending Thread'.");
    }
}

// Method 2: Create a class by implementing the Runnable interface
class WorkerTask implements Runnable {

    /**
     * The run() method contains the task to be executed.
     */
    @Override
    public void run() {
        System.out.println("Method 2: Hello from a thread created by 'implements Runnable'.");
    }
}


public class ThreadCreationDemo {

    public static void main(String[] args) {

        // --- Example 1: Using 'extends Thread' ---
        // Create an object of the class that extends Thread
        WorkerThread t1 = new WorkerThread();
        // Call start() to create a new thread and execute its run() method
        t1.start();


        // --- Example 2: Using 'implements Runnable' (Classic way) ---
        // Create an object of the task
        WorkerTask task = new WorkerTask();
        // Pass the task object to the Thread constructor
        Thread t2 = new Thread(task);
        // Call start() to create a new thread and execute the task's run() method
        t2.start();


        // --- Example 3: Using 'implements Runnable' (Modern Lambda way) ---
        // This is the modern, concise way to do Example 2.
        // We define the run() method's content directly inside a lambda expression.
        Runnable lambdaTask = () -> {
            System.out.println("Method 3: Hello from a thread using a 'Runnable lambda'.");
        };
        
        Thread t3 = new Thread(lambdaTask);
        t3.start();
    }
}