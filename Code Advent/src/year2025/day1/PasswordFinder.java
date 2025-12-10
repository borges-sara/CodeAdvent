package year2025.day1;

import java.util.List;

public class PasswordFinder {
    private static int initialPosition = 50;
    private static int counter = 0;

    public static int FindPassword(List<String> instructions){
        instructions.forEach((x) -> {
            var direction = x.charAt(0);
            var steps = Integer.parseInt(x.substring(1));

            switch (direction){
                case 'L' -> RotateLeft(steps);
                case 'R' -> RotateRight(steps);
            }
        });
        return counter;
    }

    private static void RotateRight(int steps) {
        initialPosition = Math.floorMod(initialPosition + steps, 100);
        if(initialPosition == 0) counter++;
    }

    private static void RotateLeft(int steps){
        initialPosition = Math.floorMod(initialPosition - steps, 100);
        if(initialPosition == 0) counter++;
    }
}
