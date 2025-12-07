package a2025;

import java.math.BigInteger;
import java.util.ArrayList;

public class A6Bis {

    public static BigInteger calc(String s) {
        String[] lines = s.split("\n");
        var numbers = new ArrayList<BigInteger>();
        var lastLine = lines[lines.length - 1];

        for (int x = 0; x < lastLine.length(); x++) {
            var number = "";
            for (int y = 0; y < lines.length - 1;y++) {
                number = number + lines[y].charAt(x);
            }
            if (number.trim().isEmpty()) {
                numbers.add(BigInteger.ZERO);
            } else {
                var b = new BigInteger(number.trim());
                numbers.add(b);
            }
        }
        BigInteger sum = BigInteger.ZERO;
        var currentOperation = '+';
        BigInteger currentOp = BigInteger.ZERO;
        int nextNum = 0;
        for (int x = 0; x < lastLine.length();x++) {
            if (lastLine.charAt(x) == '+') {
                currentOperation = '+';
                sum = sum.add(currentOp);
                currentOp = numbers.get(nextNum++);
            } else if (lastLine.charAt(x) == '*') {
                currentOperation = '*';
                sum = sum.add(currentOp);
                currentOp = numbers.get(nextNum++);
            } else  {
                BigInteger nextNumber = numbers.get(nextNum++);
                if (currentOperation == '+') {
                    currentOp = currentOp.add(nextNumber);
                } else {
                    if (!nextNumber.equals(BigInteger.ZERO)) {
                        currentOp = currentOp.multiply(nextNumber);
                    }
                }
            }
        }
        sum = sum.add(currentOp);
        return sum;
    }
}
