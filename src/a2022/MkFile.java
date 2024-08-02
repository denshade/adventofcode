package a2022;

public class MkFile implements A7Command
{
    private final long size;
    private final String filename;

    public MkFile(long size, String filename)
    {
        this.size = size;
        this.filename = filename;
    }
    @Override
    public void execute(A7 a7) {
        A7.FileDescriptor fileDescriptor = new A7.FileDescriptor();
        fileDescriptor.fileSize = size;
        fileDescriptor.fullPath = String.join("/", a7.cwd) +"/"+filename;
        if (!fileDescriptor.fullPath.startsWith("/")) {
            fileDescriptor.fullPath = "/" + fileDescriptor.fullPath;
        }
        fileDescriptor.dir = false;
        a7.fileDescriptors.add(fileDescriptor);
    }
}
