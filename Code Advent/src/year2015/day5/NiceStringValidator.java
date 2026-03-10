package year2015.day5;

import java.util.List;

public class NiceStringValidator {
    public static int part1(List<String> lines){
        var niceStringCount = 0;

        for (var line : lines) {
            var isValid = StringValidator.validate(line);

            if(isValid){
                niceStringCount +=1;
            }
        }

        return niceStringCount;
    }

    public static int part2(List<String> lines){
        var niceStringCount = 0;

        for(var line : lines){
            var isValid = StringValidator.validatePart2(line);

            if(isValid){
                niceStringCount +=1;
            }
        }

        return niceStringCount;
    }
}
