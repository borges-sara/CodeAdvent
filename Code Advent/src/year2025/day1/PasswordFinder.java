package year2025.day1;

import java.util.List;

public class PasswordFinder {
    private static int dialPosition1 = 50;
    private static int counter1 = 0;

    private static int dialPosition2 = 50;
    private static int counter2 = 0;

    public static int findPassword(List<String> instructions, boolean firstPart){
        return firstPart ? partOne(instructions) : partTwo(instructions);
    }

    private static int partOne(List<String> instructions){
        instructions.forEach((x) -> {
            var direction = x.charAt(0);
            var steps = Integer.parseInt(x.substring(1));

            switch (direction){
                case 'L' -> rotateLeft1(steps);
                case 'R' -> rotateRight1(steps);
            }
        });
        return counter1;
    }

    private static void rotateRight1(int steps) {
        dialPosition1 = Math.floorMod(dialPosition1 + steps, 100);
        if(dialPosition1 == 0) counter1++;
    }

    private static void rotateLeft1(int steps){
        dialPosition1 = Math.floorMod(dialPosition1 - steps, 100);
        if(dialPosition1 == 0) counter1++;
    }

    private static int partTwo(List<String> instructions){
        instructions.forEach((x) -> {
            var direction = x.charAt(0);
            var steps = Integer.parseInt(x.substring(1));

            switch (direction){
                case 'L' -> rotateLeft2(steps);
                case 'R' -> rotateRight2(steps);
            }
        });
        return counter2;
    }

    private static void rotateLeft2(int steps){
        int oldPosition = dialPosition2;
        // check how many steps we need to get to 0
        int target = oldPosition % 100;
        if (target == 0) target = 100;

        if (steps >= target) {
            counter2 += 1 + (steps - target) / 100;
        }

        int newPosition = Math.floorMod(oldPosition - steps, 100);
        dialPosition2 = newPosition;
    }

    private static void rotateRight2(int steps){
        int oldPosition = dialPosition2;
        // check how many steps we need to get to 0
        int target = (100 - oldPosition) % 100;
        if (target == 0) target = 100;

        if (steps >= target) {
            counter2 += 1 + (steps - target) / 100;
        }

        int newPosition = Math.floorMod(oldPosition + steps, 100);
        dialPosition2 = newPosition;
    }

}
