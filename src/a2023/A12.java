package a2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class A12 {
    public static boolean doesLineAndOptionAgree(LineAndOptions line) {
        if (line.states.contains(State.Unknown)) {
            throw new UnsupportedOperationException("doesLineAndOptionAgree ran on unknown state");
        }
        var groups = findGroupsForStates(line.states);
        return groups.equals(line.groups);
    }

    public static List<Integer> findGroupsForStates(List<State> states){
        var results = new ArrayList<Integer>();
        boolean inGroup = false;
        int groupSize = 0;
        for (State state : states) {
            if (state == State.Broken && !inGroup) {
                inGroup = true;
            }
            if (state == State.Broken) {
                groupSize++;
            }
            if (state == State.Good && inGroup) {
                results.add(groupSize);
                groupSize = 0;
                inGroup = false;
            }
        }
        if (inGroup) {
            results.add(groupSize);
        }
        return results;
    }

    public static int countOptions(LineAndOptions lineAndOptions) {
        return recurseFill(lineAndOptions);
    }

    public static int recurseFill(LineAndOptions lineAndOptions) {
        if (!lineAndOptions.states.contains(State.Unknown)) {
            if (findGroupsForStates(lineAndOptions.states).equals(lineAndOptions.groups)) {
                return 1;
            } else {
                return 0;
            }
        } else {
            var firstUnknownIndex = lineAndOptions.states.indexOf(State.Unknown);
            var separate = new LineAndOptions(lineAndOptions);
            separate.states.set(firstUnknownIndex, State.Broken);
            var count = recurseFill(separate);
            var separate2 = new LineAndOptions(lineAndOptions);
            separate2.states.set(firstUnknownIndex, State.Good);
            count += recurseFill(separate2);
            return count;
        }
    }

    public static int count(String test) {
        var count = 0;
        var lines = test.split("\n");
        for (var line : lines) {
            count += A12.countOptions(A12.parse(line));
        }
        return count;
    }

    public enum State {
        Broken, Unknown, Good
    }
    public static class LineAndOptions {
        public LineAndOptions() {}
        public LineAndOptions(LineAndOptions lineAndOptions) {
            states = new ArrayList<>();
            states.addAll(lineAndOptions.states);
            groups = new ArrayList<>();
            groups.addAll(lineAndOptions.groups);
        }
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
