import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(final String[] args) throws URISyntaxException, IOException {
        final List<String> input = Files.readAllLines(
                Paths.get(ClassLoader.getSystemResource("input.txt").toURI()),
                StandardCharsets.UTF_8);
        System.out.println(input);

        int totalCalories = 0;
        final List<Integer> totalCaloriesList = new ArrayList<>();
        for (final String current : input) {
            if (current.isEmpty()) {
                totalCaloriesList.add(totalCalories);
                totalCalories = 0;
            } else {
                totalCalories += Integer.parseInt(current);
            }
        }
        totalCaloriesList.add(totalCalories);
        System.out.println(totalCaloriesList);
        System.out.println(totalCaloriesList.stream()
                .mapToInt(Integer::intValue)
                .sorted()
                .skip(totalCaloriesList.size() - 3)
                .sum());
    }
}
