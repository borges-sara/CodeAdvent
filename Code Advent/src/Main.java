import year2025.day1.PasswordFinder;
import year2025.day5.Solution;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Select the day you want to execute:");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int day = Integer.parseInt(reader.readLine());
            ChoseExercise(day);
        } catch (IOException ioException){
            throw new IOException(ioException);
        }
    }

    private static void ChoseExercise(int day) throws IOException {
        switch (day){
            case 1 -> {
                var lines = readFile(Path.of("src/year2025/day5/Ingredients.txt"));
                Day1(lines);
            }
            case 2 -> {
                var lines = readFile(Path.of("src/year2025/day1/Rotation.txt"));
                Day2(lines);
            }
            default -> System.out.println("You have inserted an invalid day!");
        }
    }

    private static void Day1(List<String> lines){
        var separatorIdx = lines.indexOf("");
        List<Solution.Range> ranges = lines.subList(0,separatorIdx)
                .stream().map(x -> {
                    var limits = x.split("-");
                    return new Solution.Range(Double.parseDouble(limits[0]), Double.parseDouble(limits[1]));
                }).toList();

        List<Double> ingredients = lines.subList(separatorIdx + 1, lines.size())
                .stream().map(Double::valueOf).toList();

        var freshIngredients = Solution.CountFreshIngredients(ranges, ingredients);
        System.out.printf("Fresh ingredients: %2d\n", freshIngredients);
    }

    private static void Day2(List<String> lines){
        var password = PasswordFinder.FindPassword(lines);
        System.out.printf("The password is: %2d\n", password);
    }

    private static List<String> readFile(Path filePath) throws IOException {
        List<String> lines;
        try {

             lines = Files.lines(filePath, Charset.defaultCharset()).toList();
        } catch (IOException ioException) {
            throw new IOException(ioException);
        }
        return lines;
    }
}