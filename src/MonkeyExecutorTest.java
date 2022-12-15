import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MonkeyExecutorTest
{
    @Test
    public void processMonkeys() throws IOException {
        var monkeys = MonkeyParser.parse(new File("a11.test.txt"));
        var exe = new MonkeyExecutorBig();
        exe.execute(monkeys, 10000);
        assertEquals(2713310158L, exe.calculateMonkeyBusiness(monkeys));

    }

    @Test
    public void processMonkeysFull() throws IOException {
        var monkeys = MonkeyParser.parse(new File("a11.full.txt"));
        var exe = new MonkeyExecutorBig();
        exe.execute(monkeys, 10000);
        assertEquals(10605, exe.calculateMonkeyBusiness(monkeys));

    }

}