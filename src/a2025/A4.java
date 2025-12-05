package a2025;

public class A4 {

    static long count(String strmap) {
        var map = parseMap(strmap);
        int c = 0;
        for (int y = 0; y < map.length;y++) {
            for (int x = 0; x < map[0].length;x++) {
                if (map[y][x] && count(x,y,map) < 4) {
                    System.out.print("X");
                    c++;
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        return c;
    }

    static int count(int x, int y, boolean[][] map) {
        int counter = 0;
        if (y >= 0 && x - 1 >= 0 && map[y][x - 1])counter++;
        if (y - 1 >= 0 && x - 1 >= 0 && map[y - 1][x - 1])counter++;
        if (y + 1 < map.length && x - 1 >= 0 && map[y + 1][x - 1])counter++;
        if (y + 1 < map.length && x >= 0 && map[y + 1][x])counter++;
        if (y + 1 < map.length && x + 1 < map.length && map[y + 1][x + 1])counter++;
        if (y < map.length && x + 1 < map.length && map[y][x + 1])counter++;
        if (y - 1 >= 0 && x + 1 < map.length && map[y - 1][x + 1])counter++;
        if (y - 1 >= 0 && map[y - 1][x])counter++;
        return counter;
    }

    private static boolean[][] parseMap(String map) {
        var lines = map.split("\n");
        boolean[][] b = new boolean[lines.length][lines[0].length()];
        for (int x = 0; x < lines[0].length();x++) {
            for (int y = 0; y < lines.length;y++) {
                b[y][x] = lines[y].charAt(x) == '@';
            }
        }
        return b;
    }
}
