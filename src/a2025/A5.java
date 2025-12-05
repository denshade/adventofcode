package a2025;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class A5 {
    record Range(long start, long end) {
        boolean isInRange(long value) {
            if (value <= end && value >= start) {
                return true;
            }
            return false;
        }

        static Optional<Range> tryMerge(Range r1, Range r2) {
            if (r2.isInRange(r1.start) || r1.isInRange(r2.start) || r2.isInRange(r1.end) || r1.isInRange(r2.end)) {
                return Optional.of(new Range(Math.min(r1.start, r2.start), Math.max(r1.end, r2.end)));
            }
            return Optional.empty();
        }

    }
    public static long calculate(String s) {
        var ranges = new ArrayList<Range>();
        var lines = s.split("\n");
        boolean rangeMode = true;
        long match = 0;
        for (String line : lines) {
            if (line.equals("")) {
                rangeMode = false;
                continue;
            }
            if (rangeMode) {
                var parts = line.split("-");
                ranges.add(new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1])));
            } else {
                var value = Long.parseLong(line);
                for (var r : ranges) {
                    if (r.isInRange(value)) {
                        match++;
                        break;
                    }
                }
            }
        }
        return match;
    }

    public static BigInteger ingredients(String s) {
        List<Range> ranges = new ArrayList<>();
        var lines = s.split("\n");
        for (String line : lines) {
            if (line.equals("")) {
                break;
            }
            var parts = line.split("-");
            ranges.add(new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1])));
        }
        boolean hasMerged = true;
        Optional<List<Range>> workList;
        while(hasMerged) {
            hasMerged=false;
            workList = mergeList(ranges);
            if (workList.isPresent()) {
                ranges = workList.get();
                hasMerged = true;
            }
        }
        BigInteger size = BigInteger.ZERO;
        for (var range: ranges) {
            size = size.add(BigInteger.valueOf(range.end - range.start + 1));
        }
        return size;
    }

    static Optional<List<Range>> mergeList(List<Range> ranges) {

        var workList = new ArrayList<>(ranges);
        for (int x = 0; x < ranges.size(); x++) {
            for (int x2 = x + 1; x2 < ranges.size(); x2++) {
                var newRange = Range.tryMerge(ranges.get(x), ranges.get(x2));
                if (newRange.isPresent()) {
                    workList.remove(x2);
                    workList.remove(x);
                    workList.add(newRange.get());
                    return Optional.of(workList);
                }
            }
        }
        return Optional.empty();
    }


}
