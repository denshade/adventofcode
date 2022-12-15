import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MonkeyItem
{
    public final Map<Integer, Integer> divisorResiduesMap;

    public MonkeyItem(Map<Integer, Integer> divisorResiduesMap) {
        this.divisorResiduesMap = divisorResiduesMap;
    }

    public MonkeyItem(Integer residues, Set<Integer> dividors) {
        this(getIntegerIntegerHashMap(residues, dividors));
    }

    private static HashMap<Integer, Integer> getIntegerIntegerHashMap(Integer residues, Set<Integer> dividors) {
        var map = new HashMap<Integer, Integer>();
        for (int divisor: dividors)
        {
            map.put(divisor, residues % divisor);
        }
        return map;
    }


    public boolean isDivisable(int divisor) {
        return divisorResiduesMap.get(divisor) == 0;
    }
}
