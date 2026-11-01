
class Theatre {
    int seatsLeft = 5;

    void book() {
        if (seatsLeft > 0) {
            System.out.println(
                Thread.currentThread().getName()
                + " booked a seat"
            );

            seatsLeft--;
        } else {
            System.out.println(
                Thread.currentThread().getName()
                + " could not book"
            );
        }
    }
}

public class SeatBooking {
    public static void main(String[] args)
            throws InterruptedException {

        Theatre theatre = new Theatre();

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(
                () -> theatre.book(),
                "User-" + (i + 1)
            );
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println(
            "Seats Left: " + theatre.seatsLeft
        );
    }
}
