package a2025;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class A3Bis {

    static class Line {
        String text;
        Line(String text) {
            this.text = text;
        }
        long score() {
            return Long.parseLong(text.substring(0,12));
        }
        double scoreAcc() {
            return Double.parseDouble(text.substring(0,12) + "." + text.substring(12));
        }

        List<Line> getBetterLines() {
            var lines = new ArrayList<Line>();
            if (text.length() > 12) {
                for (int x = 1; x < text.length(); x++) {
                    lines.add(new Line(text.substring(0, x) + text.substring(x + 1)));
                }
            }
            return lines;
        }
    }

    static long getBiggestForRow(String row) {
        var bestSolution = new Line(row);
        boolean found=true;
        while(found) {
            found = false;
            if (bestSolution.text.length() > 12) {
                for (int x = 0; x < bestSolution.text.length(); x++) {
                    var proposed = new Line(bestSolution.text.substring(0, x) + bestSolution.text.substring(x + 1));
                    if (proposed.score() >= bestSolution.score()) {
                        bestSolution = proposed;
                        found = true;
                        break;
                    }
                }
            }
        }
        return bestSolution.score();
    }
    /*
    static long recurse(String s, int currentIndex) {
        if (currentIndex >= s.length()) {
            return 0;
        }
        if (s.length() == 12) {
            return Long.parseLong(s);
        }
        var s1 = recurse(s, currentIndex + 1);
        var s2 = recurse(s.substring(0, currentIndex) + s.substring(currentIndex + 1) , currentIndex);
        return Math.max(s1, s2);
    }
*/
    static BigInteger getBiggestForRows(String rows) {
        var sum = BigInteger.ZERO;
        int counter = 0;
        for (var row : rows.split("\n")) {
            sum = sum.add(BigInteger.valueOf(getBiggestForRow(row)));
        }
        return sum;
    }
}
