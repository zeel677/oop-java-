interface Switchable {

    void on();

    void off();

    default void toggle() {
        System.out.println("Toggle operation performed");
    }
}

class Fan implements Switchable {

    boolean state = false;

    public void on() {
        state = true;
        System.out.println("Fan ON");
    }

    public void off() {
        state = false;
        System.out.println("Fan OFF");
    }

    public void toggle() {
        if (state)
            off();
        else
            on();
    }
}

class Light implements Switchable {

    boolean state = false;

    public void on() {
        state = true;
        System.out.println("Light ON");
    }

    public void off() {
        state = false;
        System.out.println("Light OFF");
    }

    public void toggle() {
        if (state)
            off();
        else
            on();
    }
}

@FunctionalInterface
interface SwitchPolicy {
    boolean maySwitchOn(Switchable device, int hour);
}
public class Mains{

    public static void main(String[] args) {

        Fan fan = new Fan();
        Light light = new Light();

        Switchable[] devices = {fan, light};

        // Toggle every device
        for (Switchable device : devices) {
            device.toggle();
        }

        // Anonymous class
        SwitchPolicy policy1 = new SwitchPolicy() {
            public boolean maySwitchOn(Switchable device, int hour) {
                return hour >= 6 && hour <= 22;
            }
        };
        System.out.println("Anonymous class: "
                + policy1.maySwitchOn(fan, 10));

        SwitchPolicy policy2 =
                (device, hour) -> hour >= 6 && hour <= 22;

        System.out.println("Lambda: "
                + policy2.maySwitchOn(light, 23));
    }
}