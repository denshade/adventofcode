public class MkDir implements A7Command
{
    private final String filename;

    public MkDir(String filename)
    {
        this.filename = filename;
    }
    @Override
    public void execute(A7 a7) {
        A7.FileDescriptor fileDescriptor = new A7.FileDescriptor();
        fileDescriptor.fullPath = String.join("/", a7.cwd) +"/"+filename;
        if (!fileDescriptor.fullPath.startsWith("/")) {
            fileDescriptor.fullPath = "/" + fileDescriptor.fullPath;
        }
        fileDescriptor.dir = true;
        a7.fileDescriptors.add(fileDescriptor);
    }
}
