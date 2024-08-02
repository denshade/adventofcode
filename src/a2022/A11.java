package a2022;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.List;

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
