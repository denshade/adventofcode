package a2022;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class A11Test
{
    @Test
    void testMonkey() throws IOException {
        List<Monkey> monkeys = A11.getMonkeys(new File("a11.full.txt"));
        var exe = new MonkeyExecutorBig();
        assertEquals(2713310158L, exe.calculateMonkeyBusiness(monkeys));
    }

    @Test
    void testMonkeyFull() throws IOException {
        assertEquals(4, MonkeyParser.parse(new File("a11.full.txt")).size());
        assertNotNull(MonkeyParser.parse(new File("a11.full.txt")).get(0).monkeyCommand);
    }

}