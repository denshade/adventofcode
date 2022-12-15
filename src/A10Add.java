public class A10Add implements A10Command{
    private final int nrToAdd;

    public A10Add(int nrToAdd)
    {
        this.nrToAdd = nrToAdd;
    }

    @Override
    public Integer execute(Register x, Integer currentCycle) {
        x.triggerListenerForCycle(++currentCycle);
        x.intValue += nrToAdd;
        x.triggerListenerForCycle(++currentCycle);
        return currentCycle;
    }

}
