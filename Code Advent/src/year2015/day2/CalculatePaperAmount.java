package year2015.day2;

import java.util.Arrays;
import java.util.List;

public class CalculatePaperAmount {
    public static void calculate(List<String> lines){
        var totalPaper = 0;
        var totalRibbon = 0;

        for(var line : lines){
            var dimensions = line.split("x");

            var length = Integer.parseInt(dimensions[0]);
            var width = Integer.parseInt(dimensions[1]);
            var height = Integer.parseInt(dimensions[2]);

            var area = 2*length*width + 2*length*height + 2*width*height;
            var excess = Arrays.stream(dimensions)
                    .map(Integer::parseInt)
                    .sorted()
                    .limit(2)
                    .reduce((a, b) -> a*b);

            var ribbon = Arrays.stream(dimensions)
                    .map(Integer::parseInt)
                    .sorted()
                    .limit(2)
                    .reduce((a,b) -> 2*a + 2*b);
            var bow = length*width*height;

            totalPaper += area + excess.orElseThrow();
            totalRibbon += ribbon.orElseThrow() + bow;
        }

        System.out.println("Total paper required: " + totalPaper + "; Total ribbon: " + totalRibbon);
    }
}
