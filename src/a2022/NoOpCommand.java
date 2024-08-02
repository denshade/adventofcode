package a2022;

public class NoOpCommand implements A7Command
{
    @Override
    public void execute(A7 a7) {
        return;
    }
}
