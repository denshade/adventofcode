import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class MonkeyMult implements MonkeyCommand{
    private final int mult;
    private final int lcd;

    public MonkeyMult(int mult, int lcd)
    {
        this.mult = mult;
        this.lcd = lcd;
    }

    @Override
    public BigInteger calculate(BigInteger old) {
        return old.multiply(BigInteger.valueOf(mult)).mod(BigInteger.valueOf(lcd));
    }
}
