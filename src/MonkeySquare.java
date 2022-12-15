import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class MonkeySquare implements MonkeyCommand{

    private final int lcm;

    public MonkeySquare(int lcm)
    {
        this.lcm = lcm;
    }
    @Override
    public BigInteger calculate(BigInteger old) {
        return old.pow(2).mod(BigInteger.valueOf(lcm));
    }
}
