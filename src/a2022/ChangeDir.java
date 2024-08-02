package a2022;

public class ChangeDir implements A7Command
{
    private final String dirToChangeTo;

    public ChangeDir(String dirToChangeTo)
    {
        this.dirToChangeTo = dirToChangeTo;
    }
    @Override
    public void execute(A7 a7) {
        a7.cwd.push(dirToChangeTo);
    }
}
