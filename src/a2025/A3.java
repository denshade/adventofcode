package a2025;

public class A3 {

    static long getBiggestForRow(String row) {
        int max = 0;
        for (int x = 0; x < row.length(); x++) {
            for (int x2 = x + 1; x2 < row.length(); x2++) {
                var w = (row.charAt(x) - '0')*10 + row.charAt(x2) - '0';
                if (w > max) {
                    max = w;
                }
            }
        }
        return max;
    }

    static long getBiggestForRows(String rows) {
        var sum = 0;
        for (var row : rows.split("\n")) {
            sum += getBiggestForRow(row);
        }
        return sum;
    }
}
