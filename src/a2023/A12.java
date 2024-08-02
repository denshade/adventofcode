package a2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class A12 {
    public enum State {
        Broken, Unknown, Good
    }
    public static class LineAndOptions {
        public List<State> states;
        public List<Integer> groups;
    }
    public static LineAndOptions parse(String line) {
        var lineAndOptions = new LineAndOptions();
        var states = line.split(" ")[0];
        lineAndOptions.states = states.chars().mapToObj(e -> e == '.'?A12.State.Good: e=='?'? A12.State.Unknown: A12.State.Broken).collect(Collectors.toList());
        var groups = line.split(" ")[1];
        lineAndOptions.groups = Arrays.stream(groups.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        return lineAndOptions;
    }
}
