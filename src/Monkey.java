import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Monkey
{
    public List<BigInteger> items = new ArrayList<>();
    public MonkeyCommand monkeyCommand;
    public int divisibility;
    public int trueMonkey;
    public int falseMonkey;
    public int inspect = 0;

    public String toString()
    {
        return items.toString();
    }
}
