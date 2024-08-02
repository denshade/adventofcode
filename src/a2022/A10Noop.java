package a2022;

public class A10Noop implements A10Command{

    @Override
    public Integer execute(Register x, Integer currentCycle) {
        x.triggerListenerForCycle(++currentCycle);
        return currentCycle;
    }
}
