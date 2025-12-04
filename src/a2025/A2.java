package a2025;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A2 {

    record Range(long start, long end){

    }

    List<Long> invalidIds(List<Range> ranges) {
        var results = new ArrayList<Long>();
        for (var range: ranges) {
            results.addAll(invalidIdForRange(range));
        }
        return results;
    }

    List<Long> invalidIds(String ranges) {
        return invalidIds(Arrays.stream(ranges.split(",")).map(s -> new Range(Long.parseLong(s.split("-")[0]), Long.parseLong(s.split("-")[1]))).toList());
    }

    BigInteger sumInvalidIds(String ranges) {
        var results =  invalidIds(Arrays.stream(ranges.split(",")).map(s -> new Range(Long.parseLong(s.split("-")[0]), Long.parseLong(s.split("-")[1]))).toList());
        var b = BigInteger.ZERO;
        for (var result: results) {
            b = b.add(BigInteger.valueOf(result));
        }
        return b;
    }


    List<Long> invalidIdForRange(Range range) {
        var list = new ArrayList<Long>();
        for (long cur = range.start; cur <= range.end;cur++) {
            var curS = String.valueOf(cur);
            if (curS.substring(0, curS.length() / 2).equals(curS.substring(curS.length() / 2))) {
                list.add(cur);
            }
        }
        return list;
    }
}
