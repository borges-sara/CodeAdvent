package year2016.day1;

import java.util.HashMap;

public class EaterBunnyHeadquarters {
    private static int x = 0;
    private static int y = 0;
    private static String currentOrientation = "N";
    private static HashMap<String, Integer> positions = new HashMap<String, Integer>();

    public static int part1(String line) {
        positions.put("0,0", 1);
        //line = "R8, R4, R4, R8"; // (0,0) (8,0) (8, -4) (4, -4) (4,4)
        var instructions = line.trim().split(",");

        for(var instruction : instructions) {
            var direction = instruction.trim().substring(0,1);
            var steps = Integer.parseInt(instruction.trim().substring(1));

            var position = updatePosition(direction, steps);
            if(position != null)
            {
                int finalX = Integer.parseInt(position.split(",")[0]);
                int finalY = Integer.parseInt(position.split(",")[1]);
                return Math.abs(finalX) + Math.abs(finalY);
            }
        }
        return 0;
        //return Math.abs(x) + Math.abs(y); -> part 1
    }

    private static String updatePosition (String direction, int steps){
        switchOrientation(direction);

        switch (currentOrientation){
            case "N": {
                for (int i = 1; i <= steps; i++) {
                    y = y + 1;
                    var position = x + "," + y;
                    positions.merge(position, 1, (oldValue, _) -> oldValue + 1);

                    if(positions.get(position) > 1){
                        return position;
                    }
                }
                break;
            }
            case "S": {
                for (int i = 1; i <= steps; i++) {
                    y = y - 1;
                    var position = x + "," + y;
                    positions.merge(position, 1, (oldValue, _) -> oldValue + 1);

                    if(positions.get(position) > 1){
                        return position;
                    }
                }
                break;
            }
            case "E": {
                for (int i = 1; i <= steps; i++) {
                    x = x + 1;
                    var position = x + "," + y;
                    positions.merge(position, 1, (oldValue, _) -> oldValue + 1);

                    if(positions.get(position) > 1){
                        return position;
                    }
                }
                break;
            }
            case "W": {
                for (int i = 1; i <= steps; i++) {
                    x = x - 1;
                    var position = x + "," + y;
                    positions.merge(position, 1, (oldValue, _) -> oldValue + 1);

                    if(positions.get(position) > 1){
                        return position;
                    }
                }
                break;
            }
        }
        return null;
    }

    private static void switchOrientation(String direction){
        switch (currentOrientation){
            case "N": {
                      if(direction.equals("L")){
                          currentOrientation = "W";
                      }
                      if(direction.equals("R")){
                          currentOrientation = "E";
                      }
                      break;
            }

            case "S": {
                if(direction.equals("L")){
                    currentOrientation = "E";
                }

                if(direction.equals("R")){
                    currentOrientation = "W";
                }
                break;
            }

            case "E": {
                if(direction.equals("L")){
                    currentOrientation = "N";
                }

                if(direction.equals("R")){
                    currentOrientation = "S";
                }
                break;
            }

            case "W": {
                if(direction.equals("L")){
                    currentOrientation = "S";
                }

                if(direction.equals("R")){
                    currentOrientation = "N";
                }
                break;
            }
        }
    }
}
