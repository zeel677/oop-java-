
import java.io.*;

public class SaveLoadDemo {

    static class Student implements Serializable {
        private static final long serialVersionUID = 1L;

        String name;
        int marks;
        transient String password;

        Student(String name, int marks, String password) {
            this.name = name;
            this.marks = marks;
            this.password = password;
        }

        public String toString() {
            return name + " | " + marks + " | password=" + password;
        }
    }

    public static void main(String[] args) throws Exception {
        Student[] students = {
            new Student("Aarav", 85, "abc123"),
            new Student("Diya", 92, "xyz456")
        };

        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("students.dat"))) {
            out.writeObject(students);
        }

        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("students.dat"))) {
            Student[] loaded = (Student[]) in.readObject();

            for (Student student : loaded) {
                System.out.println(student);
            }
        }
    }
}
