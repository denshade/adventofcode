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
        for (int i = 0; i < states.size(); i++) {
            if (states.get(i) == State.Broken && !inGroup) {
                inGroup = true;
            }
            if (states.get(i) == State.Broken) {
                groupSize++;
            }
            if (states.get(i) == State.Good && inGroup) {
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
