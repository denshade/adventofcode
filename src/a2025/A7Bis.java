package a2025;

public class A7Bis {
    public static long calc(String s) {
        String[] lines = s.split("\n");
        char[][] map = new char[lines.length][lines[0].length()];
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[0].length(); x++) {
                map[y][x] = lines[y].charAt(x);
            }
        }
        int splitterHit = recurseMap(map, 0, 0);
        return splitterHit;
    }

    private static int recurseMap(char[][] map, int cy, int cx) {
        int splitterHit = 0;
        for (int y = cy; y < map.length - 1; y++) {
            for (int x = cx; x < map[y].length; x++) {
                if (map[y][x] == 'S') {
                    map[y + 1][x] = '|';
                }
                if (map[y][x] == '|') {
                    if (map[y + 1][x] == '^') {
                        var cloneMap = clone(map);
                        cloneMap[y+1][x - 1] = '|';
                        splitterHit ++;
                        splitterHit += recurseMap(cloneMap, y + 1, 0);
                        cloneMap = clone(map);
                        cloneMap[y+1][x + 1] = '|';
                        splitterHit += recurseMap(cloneMap, y + 1, 0);
                    } else {
                        map[y + 1][x] = '|';
                    }
                }
            }
        }
        return splitterHit;
    }

    private static char[][] clone(char[][] map) {
        char[][] clone = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                clone[i][j] = map[i][j];
            }
        }
        return clone;
    }
}
