
public class GenericDemo {

    static class Box<T> {
        private T value;

        public void set(T value) {
            this.value = value;
        }

        public T get() {
            return value;
        }
    }

    static class Pair<A, B> {
        private final A first;
        private final B second;

        Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public void display() {
            System.out.println(first + " | " + second);
        }
    }

    public static <T> T first(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        return array[0];
    }

    public static void main(String[] args) {
        Box<Integer> numberBox = new Box<>();
        numberBox.set(100);
        System.out.println(numberBox.get());

        Box<String> textBox = new Box<>();
        textBox.set("Java");
        System.out.println(textBox.get());

        Pair<String, Integer> pair = new Pair<>("Age", 20);
        pair.display();

        String[] names = {"Aarav", "Diya", "Ravi"};
        System.out.println("First name: " + first(names));

        Integer[] numbers = {10, 20, 30};
        System.out.println("First number: " + first(numbers));
    }
}
