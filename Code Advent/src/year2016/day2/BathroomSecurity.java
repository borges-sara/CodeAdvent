package year2016.day2;

import helpers.GenericMatrixHelper;
import helpers.MatrixHelper;

import java.util.ArrayList;
import java.util.List;

public class BathroomSecurity {
    //1 2 3
    //4 5 6
    //7 8 9
    private static final MatrixHelper bathroomLocker = new MatrixHelper(3,3);
    private static int currentX = 1;    //initial position -> 5
    private static int currentY = 1;    //initial position -> 5

    static int row = 2; // começa no 5
    static int col = 0;

    public static String part1(List<String> lines){
        //To test:
        lines = new ArrayList<String>();
        lines.add("ULL");
        lines.add("RRDDD");
        lines.add("LURDL");
        lines.add("UUUUD");

        StringBuilder sb = new StringBuilder();

        bathroomLocker.set(0, 0, 1);
        bathroomLocker.set(0, 1, 2);
        bathroomLocker.set(0, 2, 3);
        bathroomLocker.set(1, 0, 4);
        bathroomLocker.set(1, 1, 5);
        bathroomLocker.set(1, 2, 6);
        bathroomLocker.set(2, 0, 7);
        bathroomLocker.set(2, 1, 8);
        bathroomLocker.set(2, 2, 9);

        bathroomLocker.print();

        for(var line : lines){
            for(var move : line.toCharArray()){
                int newY = currentY;
                int newX = currentX;

                switch (move){
                    case 'U': newY--; break;
                    case 'D': newY++; break;
                    case 'L': newX--; break;
                    case 'R': newX++; break;
                }

                if(newY >= 0 && newY < 3
                && newX >= 0 && newX < 3){
                    currentY = newY;
                    currentX = newX;
                }
            }

            sb.append(bathroomLocker.get(currentY, currentX));
        }

        return sb.toString();
    }

    /// 2nd part locker:
    ///     0   1   2   3   4
    ///    -----------------------
    ///0|           1
    ///1|       2   3   4
    ///2|   5   6   7   8   9
    ///3|       A   B   C
    ///4|           D
    public static String part2(List<String> lines) {

        String[][] keypad = {
                {null, null, "1", null, null},
                {null, "2", "3", "4", null},
                {"5", "6", "7", "8", "9"},
                {null, "A", "B", "C", null},
                {null, null, "D", null, null}
        };

        StringBuilder code = new StringBuilder();

        for (String line : lines) {
            for (char move : line.toCharArray()) {

                int newRow = row;
                int newCol = col;

                switch (move) {
                    case 'U': newRow--; break;
                    case 'D': newRow++; break;
                    case 'L': newCol--; break;
                    case 'R': newCol++; break;
                }

                // validar limites
                if (newRow >= 0 && newRow < 5 &&
                        newCol >= 0 && newCol < 5 &&
                        keypad[newRow][newCol] != null) {

                    row = newRow;
                    col = newCol;
                }
            }

            code.append(keypad[row][col]);
        }

        return code.toString();
    }
}
