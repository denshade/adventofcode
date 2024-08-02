package a2022;

import java.io.File;
import java.io.IOException;

public class A10
{
    public static long calculate(File file) throws IOException {
        System.out.print("#");
        var commands = A10CommandParser.parse(file);
        Integer currentCycle = 1;
        var register = new Register();
        register.intValue = 1;
        for (A10Command a10: commands) {
            currentCycle = a10.execute(register, currentCycle);
        }
        return register.getListener().getSum();

    }
}
