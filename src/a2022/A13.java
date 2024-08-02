package a2022;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.*;

public class A13
{

    enum Order {
        RIGHT, INCORRECT, INCONCLUSIVE
    };

    public static List<Integer> getIndices(File file) throws IOException {
        var indices = new ArrayList<Integer>();
        List<String> lines = Files.readAllLines(file.toPath());
        for (int l = 0; l < lines.size(); l+=3) {
            JSONArray left = new JSONArray(lines.get(l));
            JSONArray right = new JSONArray(lines.get(l + 1));
            if (recursiveDiff(left, right) == Order.RIGHT || recursiveDiff(left, right) == Order.INCONCLUSIVE)
            {
                indices.add((l / 3) + 1);
            }
        }
        return indices;
    }

    public static int calculateIndices(File file) throws IOException {
        var indices = getIndices(file);
        return indices.stream().mapToInt(l->l).sum();
    }

    private static Order recursiveDiff(JSONArray left, JSONArray right) {

        for (int index = 0; index < left.length(); index++) {
            if (index >= right.length()) {
                return Order.INCORRECT;
            }
            if (left.get(index) instanceof Integer && right.get(index) instanceof Integer) {
                Order right1 = processIntegerArray(left, right, index);
                if (right1 != null) return right1;
                continue;
            }
            JSONArray leftArray = null;
            JSONArray rightArray = null;
            if (left.get(index) instanceof JSONArray && right.get(index) instanceof JSONArray) {
                leftArray = (JSONArray) left.get(index);
                rightArray = (JSONArray) right.get(index);
            }
            if (left.get(index) instanceof JSONArray && right.get(index) instanceof Integer) {
                leftArray = (JSONArray) left.get(index);
                rightArray = new JSONArray(List.of(right.get(index)));
            }
            if (left.get(index) instanceof Integer && right.get(index) instanceof JSONArray) {
                leftArray = new JSONArray(List.of(left.get(index)));
                rightArray = (JSONArray)right.get(index);
            }
            if (leftArray == null) {
                System.out.println("HERE");
            }
            assert leftArray != null;
            assert rightArray != null;
            var comingBack = recursiveDiff(leftArray, rightArray);
            if (comingBack == Order.RIGHT || comingBack == Order.INCORRECT) return comingBack;
        }
        return Order.INCONCLUSIVE;
    }

    private static Order processIntegerArray(JSONArray left, JSONArray right, int index) {
        var leftVal = (Integer) left.get(index);
        var rightVal = (Integer) right.get(index);
        if (leftVal < rightVal) {
            return Order.RIGHT;
        }
        if (leftVal > rightVal) {
            return Order.INCORRECT;
        }
        return null;
    }
}
