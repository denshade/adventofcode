package a2025;

import java.math.BigInteger;
import java.util.ArrayList;

public class A6 {

    public static BigInteger calc(String s) {
        String[] lines = s.split("\n");
        int length = lines.length - 1;
        int rowLength = 0;
        for (var k : lines[0].split(" ")) {
            if (k.isEmpty()) continue;
            rowLength++;
        }
        var operations = new ArrayList<String>();
        var matrix = new Long[length][rowLength];
        int y = 0;
        for (var l : lines) {
            int x = 0;
            if (y == length)  {
                for (var k : l.split(" ")) {
                    if (k.isEmpty()) continue;
                    operations.add(k);
                }
            } else {
                for (var k : l.split(" ")) {
                    if (k.isEmpty()) continue;
                    matrix[y][x++] = Long.parseLong(k);
                }
            }
            y++;
        }
        BigInteger sum = BigInteger.ZERO;
        for (int x = 0; x < rowLength; x++) {
            BigInteger current = BigInteger.ZERO;
            if (operations.get(x).equals("+")) {
                current = BigInteger.ZERO;
            }
            else if (operations.get(x).equals("*")) {
                current = BigInteger.ONE;
            } else {
                throw new UnsupportedOperationException(operations.get(x));
            }
            for (int cy = 0; cy < length; cy++) {

                if (operations.get(x).equals("+")) {
                    current = current.add(BigInteger.valueOf(matrix[cy][x]));
                }
                if (operations.get(x).equals("*")) {
                    current = current.multiply(BigInteger.valueOf(matrix[cy][x]));
                }
            }
            sum = sum.add(current);
        }
        return sum;
    }
}
