import java.util.*;
public class UniqueVisitors {
    public static void main(String[] args) {
        List<String> logins = Arrays.asList(
                "Aarav", "Diya", "Aarav", "Ravi",
                "Neha", "Diya", "Jay");

        HashSet<String> unique = new HashSet<>(logins);
        LinkedHashSet<String> ordered = new LinkedHashSet<>(logins);

        System.out.println("Unique visitors: " + unique.size());
        System.out.println("First three distinct visitors:");

        int count = 0;
        for (String name : ordered) {
            if (count == 3) break;
            System.out.println(name);
            count++;
        }
    }
}
