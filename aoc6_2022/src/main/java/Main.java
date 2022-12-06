import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
        final List<String> inputList = Files.readAllLines(
                Paths.get(ClassLoader.getSystemResource("input.txt").toURI()),
                StandardCharsets.UTF_8);
        final String input = inputList.get(0);
        System.out.println(input);

        // 1: x=4 2: x=14
        int x = 14;
        int totalProcessed = x;
        for (int i = 0; i < input.length() - x; i++) {
            String sequence = input.substring(i, i + x);
            if (sequence.chars().distinct().count() == x) {
                break;
            } else {
                totalProcessed++;
            }
        }
        System.out.println(totalProcessed);
    }
}
