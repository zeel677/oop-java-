class Counter {
    int count = 0;
    void increment() {
        count++;
    }
    synchronized void incrementSync() {
        count++;
    }
}
class MyThread extends Thread {
    Counter c;
    boolean sync;
    MyThread(Counter c, boolean sync) {
        this.c = c;
        this.sync = sync;
    }
    public void run() {
        for (int i = 0; i < 1000; i++) {
            if (sync) {
                c.incrementSync();
            } else {
                c.increment();
            }
        }
    }
}
public class CounterRace {
    public static void main(String[] args) throws Exception {
        Counter c1 = new Counter();
        MyThread[] t1 = new MyThread[10];
        for (int i = 0; i < 10; i++) {
            t1[i] = new MyThread(c1, false);
            t1[i].start();
        }
        for (int i = 0; i < 10; i++) {
            t1[i].join();
        }
        System.out.println("Without Synchronization = " + c1.count);
        Counter c2 = new Counter();
        MyThread[] t2 = new MyThread[10];
        for (int i = 0; i < 10; i++) {
            t2[i] = new MyThread(c2, true);
            t2[i].start();
        }
        for (int i = 0; i < 10; i++) {
            t2[i].join();
        }
        System.out.println("With Synchronization = " + c2.count);
    }
}
