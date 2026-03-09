package year2015.day3;

import java.util.*;

public class PresentDelivery {
    private static int xSanta = 0;
    private static int ySanta = 0;
    private static int xRobot = 0;
    private static int yRobot = 0;

    public static int part1(String line){
        int x = 0;
        int y =0;

        var positions = new HashMap<String, Integer>();
        positions.put("0,0", 1);   //initial position

        //test:
        //line = "^v^v^v^v^v";

        var instructions = line.chars()
                .mapToObj(Character::toString)
                .toList();

        for (var instruction : instructions) {
            switch (instruction){
                case ">" -> x+=1;
                case "<" -> x-=1;
                case "^" -> y+=1;
                case "v" -> y-=1;
            }

            String position = x + "," + y;
            positions.merge(position, 1, (a, _) -> a + 1);

//            if(positions.containsKey(position)){
//                var newValue = positions.get(position) + 1;
//                positions.put(position, newValue);
//            } else {
//                positions.put(position, 1);
//            }
        }

        return Math.toIntExact(
                positions.values()
                .stream()
                .filter(value -> value >= 1)
                .count());
    }

    public static int part2(String line){
        //line = "^v^v^v^v^v";

        var santasMap = new HashMap<String, Integer>();
        santasMap.put("0,0", 1);

        var robotsMap = new HashMap<String, Integer>();
        robotsMap.put("0,0", 1);

        var instructions = line.chars()
                .mapToObj(Character::toString)
                .toList();

        for (var i = 0; i < instructions.size(); i++) {
            var evenIndex = i % 2 == 0;

            if(evenIndex){
                updateRobotPosition(robotsMap, instructions.get(i));
            } else {
                updateSantasPosition(santasMap, instructions.get(i));
            }
        }

        //merge both maps and sums the values
        for(Map.Entry<String, Integer> entry : robotsMap.entrySet()){
            santasMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }

        return Math.toIntExact(
                santasMap.size());
    }

    private static void updateSantasPosition(HashMap<String, Integer> map, String instruction){
        switch (instruction) {
            case ">" -> xSanta +=1;
            case "<" -> xSanta -=1;
            case "^" -> ySanta +=1;
            case "v" -> ySanta -=1;
        }

        String currentPosition = xSanta + "," + ySanta;
        map.merge(currentPosition, 1, (a, _) -> a + 1);
    }

    private static void updateRobotPosition(HashMap<String, Integer> map, String instruction){
        switch (instruction) {
            case ">" -> xRobot +=1;
            case "<" -> xRobot -=1;
            case "^" -> yRobot +=1;
            case "v" -> yRobot -=1;
        }

        String currentPosition = xRobot + "," + yRobot;
        map.merge(currentPosition, 1, (a, _) -> a + 1);
    }
}
