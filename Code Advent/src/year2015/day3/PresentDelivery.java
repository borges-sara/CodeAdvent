package year2015.day3;

import java.util.*;

public class PresentDelivery {
    public static int presentsDelivery(String line){
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

    public static int presentsWithRoboSanta(String line){
        int xSanta = 0;
        int ySanta = 0;
        int xRobo = 0;
        int yRobo = 0;

        //line = "^v^v^v^v^v";

        var santasMap = new HashMap<String, Integer>();
        santasMap.put("0,0", 1);

        var robotsMap = new HashMap<String, Integer>();
        robotsMap.put("0,0", 1);

        var instructions = line.chars()
                .mapToObj(Character::toString)
                .toList();

        for (var i = 0; i < instructions.size(); i++){
            switch (instructions.get(i)){
                case ">" -> {
                    if(i%2 == 0){
                        xRobo+=1;
                    } else {
                        xSanta+=1;
                    }
                }
                case "<" -> {
                    if(i%2 == 0){
                        xRobo-=1;
                    } else {
                        xSanta-=1;
                    }
                }
                case "^" -> {
                    if(i%2 == 0){
                        yRobo+=1;
                    } else {
                        ySanta+=1;
                    }
                }
                case "v" -> {
                    if(i%2 == 0){
                        yRobo-=1;
                    } else {
                        ySanta-=1;
                    }
                }
            }

            if(i%2 == 0){
                String position = xRobo + "," + yRobo;
                robotsMap.merge(position, 1, (a, _) -> a +1);
            } else{
                String position = xSanta + "," + ySanta;
                santasMap.merge(position, 1, (a, _) -> a + 1);
            }
        }

        //merge both maps and sums the values
        for(Map.Entry<String, Integer> entry : robotsMap.entrySet()){
            santasMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }

        return Math.toIntExact(
                santasMap.size());
    }
}
