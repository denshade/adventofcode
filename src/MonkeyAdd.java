import java.math.BigInteger;

public class MonkeyAdd implements MonkeyCommand
{
    private final int number;
    private final int lcm = 19 * 3 * 11 * 17 * 5 *2 * 13 *7;

    public MonkeyAdd(int number, int lcm)
    {
        this.number = number;
    }

    @Override
    public BigInteger calculate(BigInteger old) {
        return old.add(BigInteger.valueOf(number)).mod(BigInteger.valueOf(lcm));
    }
}
