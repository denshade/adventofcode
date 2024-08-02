package a2022;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MonkeyExecutorBig
{
    public List<Monkey> execute(List<Monkey> monkeys, int nrCycles)
    {
        for (int cycle = 0; cycle < nrCycles; cycle++) {
            for (Monkey monkey : monkeys) {
                for (BigInteger itemWorry : monkey.items) {
                    monkey.inspect++;
                    BigInteger newWorryLevel = monkey.monkeyCommand.calculate(itemWorry);
                    int monkeyToThrowAt;
                    if (newWorryLevel.mod(BigInteger.valueOf(monkey.divisibility)).equals(BigInteger.ZERO)) {
                        monkeyToThrowAt = monkey.trueMonkey;
                    } else {
                        monkeyToThrowAt = monkey.falseMonkey;
                    }
                    monkeys.get(monkeyToThrowAt).items.add(newWorryLevel);
                }
                monkey.items.clear();
            }
        }
        return monkeys;
    }

    public long calculateMonkeyBusiness(List<Monkey> monkeys)
    {
        var list = monkeys.stream().map(l -> l.inspect).collect(Collectors.toList());
        Collections.sort(list);
        return (long) list.get(list.size() - 1) * list.get(list.size() - 2);
    }
}
