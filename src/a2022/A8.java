package a2022;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class A8
{
    public static long countVisible(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        var matrix = new byte[lines.size()][lines.get(0).length()];
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                matrix[y][x] = Byte.parseByte(String.valueOf(line.charAt(x)));
            }
        }
        int counter = 0;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                var value = matrix[y][x];
                if (canBeSeenFromUp(matrix, y, x, value)) {
                    counter++;
                } else if (canBeSeenFromDown(matrix, y, x, value)) {
                    counter++;
                } else if (canBeSeenFromLeft(matrix, y, x, value)) {
                    counter++;
                } else if (canBeSeenFromRight(matrix, y, x, value)) {
                    counter++;
                }

            }
        }
        return counter;
    }

    private static boolean canBeSeenFromUp(byte[][] matrix, int y, int x, byte value) {
        for (int cy = y - 1; cy > -1; cy--)
        {
            if (matrix[cy][x] >= value) {
                return false;
            }
        }
        return true;
    }

    private static boolean canBeSeenFromDown(byte[][] matrix, int y, int x, byte value) {
        for (int cy = y + 1; cy < matrix.length; cy++)
        {
            if (matrix[cy][x] >= value) {
                return false;
            }
        }
        return true;
    }

    private static boolean canBeSeenFromLeft(byte[][] matrix, int y, int x, byte value) {
        for (int cx = x + 1; cx < matrix.length; cx++)
        {
            if (matrix[y][cx] >= value) {
                return false;
            }
        }
        return true;
    }
    private static boolean canBeSeenFromRight(byte[][] matrix, int y, int x, byte value) {
        for (int cx = x - 1; cx > -1; cx--)
        {
            if (matrix[y][cx] >= value) {
                return false;
            }
        }
        return true;
    }

    public static int bestScenicVisible(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        var matrix = new byte[lines.size()][lines.get(0).length()];
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                matrix[y][x] = Byte.parseByte(String.valueOf(line.charAt(x)));
            }
        }
        int maxTrees = 0;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                var value = matrix[y][x];
                var vUp = valBeSeenFromUp(matrix,y, x, value);
                var vDown = valBeSeenFromDown(matrix,y, x, value);
                var vLeft = valBeSeenFromLeft(matrix,y, x, value);
                var vRight = valBeSeenFromRight(matrix,y, x, value);
                var calc = vUp * vDown * vLeft * vRight;
                if (calc > maxTrees) {
                    maxTrees = calc;
                }
            }
        }
        return maxTrees;
    }

    private static int valBeSeenFromUp(byte[][] matrix, int y, int x, byte value) {
        int treesSeen = 0;
        for (int cy = y - 1; cy > -1; cy--)
        {
            treesSeen++;
            if (matrix[cy][x] >= value) {
                break;
            }
        }
        return treesSeen;
    }

    private static int valBeSeenFromDown(byte[][] matrix, int y, int x, byte value) {
        var treesSeen = 0;
        for (int cy = y + 1; cy < matrix.length; cy++)
        {
            treesSeen++;
            if (matrix[cy][x] >= value) {
                break;
            }
        }
        return treesSeen;
    }

    private static int valBeSeenFromLeft(byte[][] matrix, int y, int x, byte value) {
        var treesSeen = 0;

        for (int cx = x + 1; cx < matrix.length; cx++)
        {
            treesSeen++;
            if (matrix[y][cx] >= value) {
                break;
            }
        }
        return treesSeen;
    }
    private static int valBeSeenFromRight(byte[][] matrix, int y, int x, byte value) {
        var treesSeen = 0;

        for (int cx = x - 1; cx > -1; cx--)
        {
            treesSeen++;

            if (matrix[y][cx] >= value) {
                break;
            }
        }
        return treesSeen;
    }

}
