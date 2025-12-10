package year2025.day5;

import java.util.List;

public class Solution {

    public static int CountFreshIngredients (List<Range> ranges, List<Double> ingredients){
        var counter = 0;

        for (int i = 0; i < ingredients.size(); i++) {
            var curIngredient = ingredients.get(i);

            for (int j = 0; j < ranges.size(); j++) {
                double start = ranges.get(j).start;
                double end = ranges.get(j).end;

                if(start <= curIngredient && curIngredient <= end){
                    counter++;
                    break;
                }
            }
        }
        return counter;
    }

    public record Range(double start, double end) { }
}
