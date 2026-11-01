import java.util.*;
public class WordFrequency {
    public static void main(String[] args) {
        String paragraph =
                "Java is easy. Java is powerful. Java is popular.";

        Map<String, Integer> frequency = new HashMap<>();

        for (String word : paragraph.toLowerCase()
                .split("[^a-z]+")) {
            if (!word.isEmpty()) {
                frequency.put(word,
                        frequency.getOrDefault(word, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> list =
                new ArrayList<>(frequency.entrySet());

        list.sort(
            Comparator.<Map.Entry<String, Integer>>comparingInt(
                    Map.Entry::getValue)
                    .reversed()
                    .thenComparing(Map.Entry::getKey)
        );

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey()
                    + " = " + entry.getValue());
        }
    }
}
