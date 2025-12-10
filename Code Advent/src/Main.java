import year2025.day5.Solution;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            var path = Path.of("src/year2025/day5/Ingredients.txt");
            List<String> lines = Files.lines(path, Charset.defaultCharset()).toList();

            var separatorIdx = lines.indexOf("");
            List<Solution.Range> ranges = lines.subList(0,separatorIdx)
                    .stream().map(x -> {
                        var limits = x.split("-");
                        return new Solution.Range(Double.parseDouble(limits[0]), Double.parseDouble(limits[1]));
                    }).toList();

            List<Double> ingredients = lines.subList(separatorIdx + 1, lines.size())
                    .stream().map(Double::valueOf).toList();

            var freshIngredients = Solution.CountFreshIngredients(ranges, ingredients);
            System.out.printf("Fresh ingredients: %2d", freshIngredients);
        } catch (IOException ioException) {
            throw new IOException(ioException);
        }
    }
}