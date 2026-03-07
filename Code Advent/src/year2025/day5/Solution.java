package year2025.day5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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

    public static double CountValidIngredientIds(List<Range> ranges){
        //order ranges by 1st id
        var orderedList = new ArrayList<Range>(ranges);
        orderedList.sort(Comparator.comparingDouble(Range::start));

        var previousStart = orderedList.getFirst().start;
        var previousEnd = orderedList.getFirst().end;
        var validIdsCounter = previousEnd - previousStart + 1;

        for (int i = 1; i < orderedList.size(); i++) {
            var currentStart = orderedList.get(i).start;
            var currentEnd = orderedList.get(i).end;

            if(currentStart < previousEnd){
                validIdsCounter = validIdsCounter - (previousEnd - previousStart + 1);
                validIdsCounter = validIdsCounter + (currentEnd - previousStart + 1);
                previousEnd = currentEnd;
            } else {
                validIdsCounter = validIdsCounter + (currentEnd - currentStart + 1);
                previousEnd = currentEnd;
                previousStart = currentStart;
            }
        }
        return validIdsCounter;
    }

    public static final class Range{
        private final double start;
        private final double end;

        public Range(double start, double end) {
            this.start = start;
            this.end = end;
        }

        public double start() {
            return start;
        }

        public double end() {
            return end;
        }
    }
}
