package a2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class A17 {

    public static int[][] loadMap(String map) {
        var lines = map.split("\n");
        int[][] res = new int[lines.length][lines[0].length()];
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                res[y][x] = lines[y].charAt(x) - '0';
            }
        }
        return res;
    }

    public enum Direction {
        Up, Down, Left, Right;
        public static List<Direction> allowedDirections(Direction direction) {
            Direction inverseDirection = switch (direction) {
                case Up -> Down;
                case Down -> Up;
                case Left -> Left;
                case Right -> Right;
            };
            var directions = Arrays.stream(values()).collect(Collectors.toList());
            directions.remove(inverseDirection);
            return directions;
        }
    }

    public static class Walk {
        public Direction currentDirection = Direction.Down;
        public int x = 0;
        public int y = 0;
        public int currentHeat = 0;
        public List<Direction> directionsSoFar = new ArrayList<>();
        public List<Direction> allowedDirections(int width, int height){
            var list = new ArrayList<Direction>();
            for (var dir : Direction.allowedDirections(currentDirection)) {
                if (dir == Direction.Left && x > 0) {
                    list.add(dir);
                }
                if (dir == Direction.Right && x < width - 1) {
                    list.add(dir);
                }
                if (dir == Direction.Up && y > 0) {
                    list.add(dir);
                }
                if (dir == Direction.Down && y < height - 1) {
                    list.add(dir);
                }
            }
            if (directionsSoFar.size() > 2) {
                var lastDirections = directionsSoFar.subList(directionsSoFar.size() - 3, directionsSoFar.size());
                if (lastDirections.stream().filter(e -> e == currentDirection).count() > 2) {
                    return list.stream().filter(e -> e != currentDirection).collect(Collectors.toList());
                }
            }
            return list;
        }
    }


    public static int bestWalk(int[][] mapObj) {
        //loop over the
        var walk = new Walk();
        return 0;
    }
}
