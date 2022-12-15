import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class A5
{
    public static List<Stack<String>> loadStacks(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        int emptyLine = 0;
        while(lines.get(emptyLine).length() > 0) {
            emptyLine++;
        }
        var listOfStacks = new ArrayList<Stack<String>>();
        var firstLine = lines.get(emptyLine - 1);
        for (int i = 0; i <= firstLine.length()/4; i++) {
            listOfStacks.add(new Stack<>());
        }
        for (int loopBack = emptyLine - 2; loopBack > -1; loopBack--)
        {
            var line = lines.get(loopBack);
            for (int stackIndex = 0; stackIndex <= line.length()/4; stackIndex++)
            {
                if (line.charAt(1 + stackIndex*4) != ' ') {
                    listOfStacks.get(stackIndex).add(String.valueOf(line.charAt(1 + stackIndex*4)));
                }
            }
        }
        return listOfStacks;
    }
    public static class Command
    {
        public int howMany;
        public int fromStack;
        public int toStack;

        public Command(String fromString)
        {
            var parts = fromString.split(" ");
            howMany = Integer.parseInt(parts[1]);
            fromStack = Integer.parseInt(parts[3]);
            toStack = Integer.parseInt(parts[5]);
        }

    }

    public static List<Stack<String>> processFile(File file) throws IOException {
        List<Stack<String>> stacks = loadStacks(file);
        List<String> lines = Files.readAllLines(file.toPath());
        int emptyLine = 0;
        while(lines.get(emptyLine).length() > 0) {
            emptyLine++;
        }
        for (int lineNr = emptyLine + 1; lineNr < lines.size(); lineNr++)
        {
            move(stacks, new Command(lines.get(lineNr)));
        }
        return stacks;

    }
    public static String toString(List<Stack<String>> stacks)
    {
        var s = "";
        for (Stack stack : stacks) {
            s += stack.pop();
        }
        return s;
    }

    public static List<Stack<String>> move(List<Stack<String>> stacks, Command command)
    {
        Stack<String> middleStack = new Stack<>();
        for (int nrLoops = 0; nrLoops < command.howMany; nrLoops++)
        {
            var payload = stacks.get(command.fromStack - 1).pop();
            middleStack.push(payload);
        }
        while(middleStack.size() > 0) {
            var payload = middleStack.pop();
            stacks.get(command.toStack - 1).push(payload);
        }

        return stacks;
    }
}
