package a2025;

public class A7 {
    public static long calc(String s) {
        String[] lines = s.split("\n");
        char[][] map = new char[lines.length][lines[0].length()];
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[0].length(); x++) {
                map[y][x] = lines[y].charAt(x);
            }
        }
        int splitterHit = 0;
        for (int y = 0; y < map.length - 1; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == 'S') {
                    map[y + 1][x] = '|';
                }
                if (map[y][x] == '|') {
                    if (map[y + 1][x] == '^') {
                        map[y + 1][x - 1] = '|';
                        map[y + 1][x + 1] = '|';
                        splitterHit++;
                    } else {
                        map[y + 1][x] = '|';
                    }
                }
            }
        }
        return splitterHit;
    }
}
