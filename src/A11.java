import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class A11
{
    public static List<Monkey> getMonkeys(File file) throws IOException {
        var monkeys = MonkeyParser.parse(file);
        var exe = new MonkeyExecutorBig();
        exe.execute(monkeys, 10000);
        exe.calculateMonkeyBusiness(monkeys);
        return monkeys;
    }
}
