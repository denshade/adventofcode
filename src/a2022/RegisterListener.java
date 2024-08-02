package a2022;

public class RegisterListener {

    private long sum = 0;
    public void trigger(int cycle, int value) {
        if ((cycle - 20) % 40 == 0) {
            System.out.println(value * cycle);
            sum+= (long) value * cycle;
        }
    }

    public long getSum()
    {
        return sum;
    }
}
