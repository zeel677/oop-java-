class SeatBooking {
    int seatsLeft = 5;
    void book(String name)
    {
        if (seatsLeft > 0)
            {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
            seatsLeft--;
            System.out.println(name + " booked a seat.");
        } else {
            System.out.println(name + " failed to book.");
        }
    }
    synchronized void bookSync(String name)
    {
        if (seatsLeft > 0) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
            seatsLeft--;
            System.out.println(name + " booked a seat.");
        } else {
            System.out.println(name + " failed to book.");
        }
    }
}
class BookingThread extends Thread
{
    SeatBooking s;
    String name;
    boolean sync;
    BookingThread(SeatBooking s, String name, boolean sync)
    {
        this.s = s;
        this.name = name;
        this.sync = sync;
    }
    public void run()
    {
        if (sync) {
            s.bookSync(name);
        } else {
            s.book(name);
        }
    }
}
public class SeatBookingRace
{
    public static void main(String[] args) throws Exception
    {
        SeatBooking s1 = new SeatBooking();
        BookingThread[] t1 = new BookingThread[10];
        for (int i = 0; i < 10; i++)
        {
            t1[i] = new BookingThread(s1, "User " + (i + 1), false);
            t1[i].start();
        }
        for (int i = 0; i < 10; i++)
        {
            t1[i].join();
        }
        System.out.println("Seats Left Without Synchronization = " + s1.seatsLeft);
        SeatBooking s2 = new SeatBooking();
        BookingThread[] t2 = new BookingThread[10];
        for (int i = 0; i < 10; i++)
        {
            t2[i] = new BookingThread(s2, "User " + (i + 1), true);
            t2[i].start();
        }
        for(int i=0;i<10;i++)
        {
            t2[i].join();
        }
        System.out.println("Seats Left With Synchronization = " + s2.seatsLeft);
    }
}