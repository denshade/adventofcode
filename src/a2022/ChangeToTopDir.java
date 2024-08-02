package a2022;

public class ChangeToTopDir implements A7Command
{
    @Override
    public void execute(A7 a7) {
        a7.cwd.clear();
    }
}
