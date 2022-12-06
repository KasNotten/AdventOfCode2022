import org.apache.commons.collections4.ListUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(final String[] args) throws URISyntaxException, IOException {
        final List<String> input = Files.readAllLines(
                Paths.get(ClassLoader.getSystemResource("input.txt").toURI()),
                StandardCharsets.UTF_8);
        System.out.println(input);

        final List<List<String>> groupsOfThree = ListUtils.partition(input, 3);

        int totalPriority = 0;
        for (final List<String> group : groupsOfThree) {
            final String firstCompartment = group.get(0);
            final String secondCompartment = group.get(1);
            final String thirdCompartment = group.get(2);
            final char duplicateCharacter = firstCompartment.chars()
                    .mapToObj(c -> (char) c)
                    .filter(character -> secondCompartment.contains(String.valueOf(character)) && thirdCompartment.contains(String.valueOf(character)))
                    .findFirst()
                    .orElseThrow();
            final int priority = determinePriority(duplicateCharacter);
            totalPriority += priority;
        }

        System.out.println(totalPriority);
    }

    private static int determinePriority(final char c) {
        final char[] characterPriorities = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (int i = 0; i < characterPriorities.length; i++) {
            if (characterPriorities[i] == c) {
                return i + 1;
            }
        }
        return 0;
    }
}
