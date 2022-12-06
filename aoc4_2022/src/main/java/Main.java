import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
        final List<String> input = Files.readAllLines(
                Paths.get(ClassLoader.getSystemResource("input.txt").toURI()),
                StandardCharsets.UTF_8);
        System.out.println(input);

        int totalOverlapping = 0;
        for (String line : input) {
            String[] split = line.split(",");
            String firstElfRange = split[0];
            String secondElfRange = split[1];
            List<Integer> firstElfSections = toSections(firstElfRange);
            List<Integer> secondElfSections = toSections(secondElfRange);
            if (sectionsPartiallyOverlap(firstElfSections, secondElfSections)) {
                totalOverlapping++;
            }
        }
        System.out.println(totalOverlapping);
    }

    private static List<Integer> toSections(String sectionRange) {
        String[] split = sectionRange.split("-");
        int begin = Integer.parseInt(split[0]);
        int end = Integer.parseInt(split[1]);

        return IntStream.rangeClosed(begin, end)
                .boxed()
                .toList();
    }

    // 1
    private static boolean sectionsFullyOverlap(List<Integer> firstElfSections, List<Integer> secondElfSections) {
        return secondElfSections.containsAll(firstElfSections) || firstElfSections.containsAll(secondElfSections);
    }

    // 2
    private static boolean sectionsPartiallyOverlap(List<Integer> firstElfSections, List<Integer> secondElfSections) {
        return secondElfSections.stream().anyMatch(firstElfSections::contains) ||
                firstElfSections.stream().anyMatch(secondElfSections::contains);
    }
}
