public class Main {
    public static void main(String[] args) throws InterruptedException {

        // System.out.println(Thread.currentThread().getName());

        // Thread thread = Thread.currentThread() ;

        // System.out.println("current Thread" + thread.getName());

        // Thread.sleep(
        //     5000
        // );

        // System.out.println("current Thread" + thread.getName());

        // Mythread thread = new Mythread();

        // thread.start();
        System.out.println("===> " + Thread.currentThread().getName());
        // use Runnable Interface

        Runnable r1 = new Mythread();
            r1.run();
        Thread thread = new Thread(r1, "MyThread");
        thread.start();
    }

    // static class Mythread extends Thread {

    //     public void run(){
    //         setName("Mythread33");
    //         System.out.println("current Thread " + Thread.currentThread());
    //     }

    //     public int run(int x){
    //         return x + 5;
    //     } 
    // }

        static class Mythread implements Runnable {

        public void run(){
            System.out.println("current Thread " + Thread.currentThread());
        }

        public int run(int x){
            return x + 5;
        } 
    }
}