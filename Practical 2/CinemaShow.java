public class CinemaShow {
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked = 0;

    public CinemaShow(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        this.seatsAvailable = capacity;
    }

    public CinemaShow(String title) {
        this(title, 100);
    }

    public boolean book(int n) {
        if (n <= seatsAvailable) {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        }
        return false;
    }

    public void cancel(int n) {
        seatsAvailable += n;
        if (seatsAvailable > capacity)
            seatsAvailable = capacity;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public static int getTotalBooked() {
        return totalBooked;
    }

    public static void main(String[] args) {
        CinemaShow c = new CinemaShow("Avengers", 100);

        System.out.println(c.book(30));
        System.out.println(c.getSeatsAvailable());

        System.out.println(c.book(50));
        System.out.println(c.getSeatsAvailable());

        System.out.println(c.book(30));
        System.out.println(c.getSeatsAvailable());

        c.cancel(20);
        System.out.println(c.getSeatsAvailable());

        System.out.println(c.book(20));
        System.out.println(c.getSeatsAvailable());

        System.out.println(CinemaShow.getTotalBooked());
    }
}