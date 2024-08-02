package a2022;

public class CrtRegisterListener {

    private long sum = 0;
    public void trigger(int cycle, int value) {
        var position = (cycle - 1) % 40;
        System.out.print(SpritePositioner.getValue(position, value));
        if (cycle%40 == 0) {
            System.out.println();
        }
    }

    public long getSum()
    {
        return sum;
    }
}
