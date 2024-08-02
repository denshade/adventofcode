package a2022;

public class Register
{
    private final CrtRegisterListener listener = new CrtRegisterListener();

    public int intValue;

    public void triggerListenerForCycle(int cycle)
    {
        listener.trigger(cycle, intValue);
    }

    public CrtRegisterListener getListener()
    {
        return listener;
    }

}
