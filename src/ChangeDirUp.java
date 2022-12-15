public class ChangeDirUp implements A7Command
{
    public ChangeDirUp()
    {
    }
    @Override
    public void execute(A7 a7) {
        a7.cwd.pop();
    }
}
