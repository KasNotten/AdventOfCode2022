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

        int totalScore = 0;
        for (final String line : input) {
            final String[] choices = line.split(" ");
            final String opponentChoice = decrypt(choices[0]);
            final String expectedResult = decrypt(choices[1]);
            final String myChoice = determineChoice(expectedResult, opponentChoice);
            final String result = determineResult(opponentChoice, myChoice);
            totalScore += determineScore(myChoice, result);
        }

        System.out.println(totalScore);
    }

    private static String determineResult(String opponentChoice, String myChoice) {
        if (opponentChoice.equals(myChoice)) {
            return "Draw";
        } else if (opponentChoice.equals("Paper") && myChoice.equals("Rock")) {
            return "Lose";
        } else if (opponentChoice.equals("Scissors") && myChoice.equals("Rock")) {
            return "Win";
        } else if (opponentChoice.equals("Rock") && myChoice.equals("Paper")) {
            return "Win";
        } else if (opponentChoice.equals("Scissors") && myChoice.equals("Paper")) {
            return "Lose";
        } else if (opponentChoice.equals("Rock") && myChoice.equals("Scissors")) {
            return "Lose";
        } else if (opponentChoice.equals("Paper") && myChoice.equals("Scissors")) {
            return "Win";
        } else {
            return "Error";
        }
    }

    private static int determineScore(String myChoice, String result) {
        int score = 0;

        if (myChoice.equals("Rock")) {
            score += 1;
        } else if (myChoice.equals("Paper")) {
            score += 2;
        } else {
            score += 3;
        }

        if (result.equals("Win")) {
            score += 6;
        } else if (result.equals("Draw")) {
            score += 3;
        }

        return score;
    }

    private static String determineChoice(String expectedResult, String opponentchoice) {
        if (expectedResult.equals("Win")) {
            return switch (opponentchoice) {
                case "Rock" -> "Paper";
                case "Paper" -> "Scissors";
                default -> "Rock";
            };
        } else if (expectedResult.equals("Lose")) {
            return switch (opponentchoice) {
                case "Rock" -> "Scissors";
                case "Paper" -> "Rock";
                default -> "Paper";
            };
        } else {
            return opponentchoice;
        }
    }

    private static String decrypt(String choice) {
        return switch (choice) {
            case "A" -> "Rock";
            case "B" -> "Paper";
            case "X" -> "Lose";
            case "Y" -> "Draw";
            case "Z" -> "Win";
            default -> "Scissors";
        };
    }
}
