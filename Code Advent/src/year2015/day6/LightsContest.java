package year2015.day6;

import helpers.MatrixHelper;

import java.util.List;

public class LightsContest {
    private static MatrixHelper lightsMatrix = new MatrixHelper(1000, 1000);

    public static int part1(List<String> lines){
        for(var line : lines){
            var instructions = line
                    .split(" ");

            var action = instructions[0];
            var origin = "";
            var destination = "";

            if(isTurn(action)){
                action = instructions[1];
                origin = instructions[2];
                destination = instructions[4];
            } else {
                origin = instructions[1];
                destination = instructions[3];
            }

            performAction(action, origin, destination);
        }

        return lightsMatrix.countCellsWithValue(1);
    }

    private static boolean isTurn(String action){
        if(action.equals("turn")){
            return true;
        }
        return false;
    }

    private static void performAction(String action, String origin, String destination){
        var originCoords = origin.split(",");
        var destinationCoords = destination.split(",");

        var originX = Integer.parseInt(originCoords[0]);
        var originY = Integer.parseInt(originCoords[1]);
        var destinationX = Integer.parseInt(destinationCoords[0]);
        var destinationY = Integer.parseInt(destinationCoords[1]);

        switch (action){
            case "on" ->
                    lightsMatrix.setSquare(originX, destinationX, originY, destinationY, 1);
            case "off" ->
                    lightsMatrix.setSquare(originX, destinationX, originY, destinationY, -1);
            case "toggle" ->
                    lightsMatrix.toggleSquare(originX, destinationX, originY, destinationY);
        }
    }
}
