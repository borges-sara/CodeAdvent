import year2015.day1.FloorFinder;
import year2015.day2.CalculatePaperAmount;
import year2015.day3.PresentDelivery;
import year2015.day4.StockingStuffer;
import year2015.day5.NiceStringValidator;
import year2015.day6.LightsContest;
import year2016.day1.EaterBunnyHeadquarters;
import year2016.day2.BathroomSecurity;
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
        System.out.println("Select the year you want to execute:");
        System.out.println("- 2015");
        System.out.println("- 2016");
        System.out.println("- 2025");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int year = Integer.parseInt(reader.readLine());
            choseYear(year);
        } catch (IOException ioException){
            throw new IOException(ioException);
        }
    }

    private static void choseYear(int year) throws IOException{
        switch (year) {
            case 2015 -> {
                int day = printDayMessage();
                chose2015Exercise(day);
            }
            case 2016 -> {
                int day = printDayMessage();
                chose2016Exercise(day);
            }
            case 2025 -> {
                int day = printDayMessage();
                chose2025Exercise(day);
            }
            default -> System.out.println("You have inserted an invalid year!");
        }
    }

    private static int printDayMessage() throws IOException {
        System.out.println("Select the day of the month you want to execute:");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return Integer.parseInt(reader.readLine());
        } catch (IOException ioException){
            throw new IOException(ioException);
        }
    }

    private static void chose2025Exercise(int day) throws IOException {
        switch (day){
            case 1 -> {
                var lines = readFile(Path.of("src/year2025/day1/Rotation.txt"));
                day2(lines);
            }
            case 5 -> {
                var lines = readFile(Path.of("src/year2025/day5/Ingredients.txt"));
                day5(lines);
            }
            default -> System.out.println("You have inserted an invalid day!");
        }
    }

    private static void chose2015Exercise(int day) throws IOException{
        switch (day){
            case 1 -> {
                var lines = readFile(Path.of("src/year2015/day1/Input.txt"));
                FloorFinder.findSantasFloor(lines.getFirst());
            }
            case 2-> {
                var lines = readFile(Path.of("src/year2015/day2/Input.txt"));
                CalculatePaperAmount.calculate(lines);
            }
            case 3 -> {
                var lines = readFile(Path.of("src/year2015/day3/Input.txt"));
                var repeated = PresentDelivery.part1(lines.getFirst());
                System.out.println("Repeated houses: " + repeated);

                var robotSanta = PresentDelivery.part2(lines.getFirst());
                System.out.println(robotSanta + " houses received at least 1 present.");
            }
            case 4 -> {
                var number = StockingStuffer.part1And2(5);
                var number2 = StockingStuffer.part1And2(6);
                System.out.println("(PART1) MD5 hash with 5 zeroes: " + number );
                System.out.println("(PART2) MD5 hash with 6 zeroes: " + number2 );
            }
            case 5 -> {
                var lines = readFile(Path.of("src/year2015/day5/Input.txt"));
                var niceStrings = NiceStringValidator.part1(lines);
                var niceStrings2 = NiceStringValidator.part2(lines);
                System.out.println("(PART1) There are " + niceStrings + " nice strings.");
                System.out.println("(PART2) There are " + niceStrings2 + " nice strings.");
            }
            case 6 -> {
                var lines = readFile(Path.of("src/year2015/day6/Input.txt"));
                var litLights = LightsContest.part1(lines);
                System.out.println("(PART1) Lit lights: " + litLights);
            }
            default -> System.out.println("You have inserted an invalid day.");
        }
    }

    private static void day5(List<String> lines){
        var separatorIdx = lines.indexOf("");
        List<Solution.Range> ranges = lines.subList(0,separatorIdx)
                .stream().map(x -> {
                    var limits = x.split("-");
                    return new Solution.Range(Double.parseDouble(limits[0]), Double.parseDouble(limits[1]));
                }).toList();

        List<Double> ingredients = lines.subList(separatorIdx + 1, lines.size())
                .stream().map(Double::valueOf).toList();

        var freshIngredients = Solution.CountFreshIngredients(ranges, ingredients);
        System.out.printf("(Part 1) Fresh ingredients: %2d\n", freshIngredients);

        var validIds = Solution.CountValidIngredientIds(ranges);
        System.out.printf("(Part 2) Valid ingredient ids: %f\n", validIds);
    }

    private static void day2(List<String> lines){
        var password = PasswordFinder.findPassword(lines, true);
        System.out.printf("(Part1) The password is: %2d\n", password);

        var password2 = PasswordFinder.findPassword(lines, false);
        System.out.printf("(Part2) The password is: %2d\n", password2);
    }

    private static void chose2016Exercise(int day) throws IOException {
        switch (day) {
            case 1 -> {
                var lines = readFile(Path.of("src/year2016/day1/Input.txt"));
                var result = EaterBunnyHeadquarters.part1(lines.getFirst());
                System.out.println(result);
            }
            case 2 -> {
                var lines = readFile(Path.of("src/year2016/day2/Input.txt"));
                var result = BathroomSecurity.part1(lines);
                var result2 = BathroomSecurity.part2(lines);
                System.out.println("(PART1): Code: " + result);
                System.out.println("(PART2): Code: " + result2);
            }
            default -> System.out.println("You have inserted an invalid day.");
        }
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