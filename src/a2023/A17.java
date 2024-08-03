package a2023;

import java.util.ArrayList;
import java.util.List;

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
        Up, Down, Left, Right
    }

    public static class Walk {
        public Direction currentDirection = Direction.Down;
        public int x;
        public int y;
        public int currentHeat;
    }


    public static int bestWalk(int[][] mapObj) {

        return 0;
    }
}
