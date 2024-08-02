package a2022;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class A7
{
    public long getSmallestSize() {
        var v = fileDescriptors.stream().filter(e -> e.dir).map(e->e.fileSize).sorted().collect(Collectors.toList());
        var biggest = v.get(v.size() - 1);
        var totalDiskSpace = 70000000;
        var totalUsed = biggest;
        var unused = totalDiskSpace - biggest;
        var required = 30000000 - unused;
        return v.stream().filter(e -> e  >= required).findFirst().get();
    }

    public static class FileDescriptor
    {
        boolean dir;
        String fullPath;
        long fileSize;
        public String toString()
        {
            return dir? (fullPath + " (dir, size="+fileSize + ")" ): fullPath + " (file, size="+fileSize +" )";
        }
    }

    Stack<String> cwd = new Stack<>();
    List<FileDescriptor> fileDescriptors = new ArrayList<>();
    public void execute(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        execute(lines.stream().map(l -> convertToCommand(l)).collect(Collectors.toList()));
    }

    private A7Command convertToCommand(String l) {
        if (l.equals("$ cd /")){
            return new ChangeToTopDir();
        }
        if (l.equals("$ cd ..")){
            return new ChangeDirUp();
        }
        if (l.startsWith("dir ")){
            return new MkDir(l.split(" ")[1]);
        }
        if (l.startsWith("$ cd ")){
            return new ChangeDir(l.split(" ")[2]);
        }
        if (l.startsWith("$ ls")){
            return new NoOpCommand();
        }
        var lineParts = l.split(" ");
        var size = Integer.parseInt(lineParts[0]);
        var filename = lineParts[1];
        return new MkFile(size, filename);
    }

    public void execute(List<A7Command> commands)
    {
        for (A7Command command : commands)
        {
            command.execute(this);
        }
    }

    public List<FileDescriptor> getRecursiveSizes()
    {
        if (fileDescriptors.stream().noneMatch(e -> e.fullPath.equals("/"))) {
            FileDescriptor root = new FileDescriptor();
            root.fullPath = "/";
            root.dir = true;
            fileDescriptors.add(root);
        }
        for (var file : fileDescriptors) {
            if (!file.dir) continue;
            file.fileSize = fileDescriptors.stream().filter(f -> !f.dir && f.fullPath.startsWith(getPrefix(file))).mapToLong(f -> f.fileSize).sum();
        }
        return fileDescriptors;
    }

    private String getPrefix(FileDescriptor file) {
        return file.fullPath.length() == 1? "/": file.fullPath+"/";
    }

    public Long getTopSizes()
    {
        return fileDescriptors.stream().filter(e -> e.dir && e.fileSize <= 100000).mapToLong(e -> e.fileSize).sum();
    }
}
