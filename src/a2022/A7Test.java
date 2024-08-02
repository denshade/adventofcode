package a2022;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class A7Test
{

    @Test
    void checkCwd() throws IOException {
        var a7 = new A7();
        a7.execute(new File("a7.test.txt"));
        assertEquals("[/a (dir, size=0), /b.txt (file, size=14848514 ), /c.dat (file, size=8504156 ), /d (dir, size=0), /a/e (dir, size=0), /a/f (file, size=29116 ), /a/g (file, size=2557 ), /a/h.lst (file, size=62596 ), /a/e/i (file, size=584 ), /d/j (file, size=4060174 ), /d/d.log (file, size=8033020 ), /d/d.ext (file, size=5626152 ), /d/k (file, size=7214296 )]", a7.fileDescriptors.toString());
    }
    @Test
    void checkSizeCwd() throws IOException {
        var a7 = new A7();
        a7.execute(new File("a7.test.txt"));
        a7.getRecursiveSizes();
        assertEquals("[/a (dir, size=94853), /b.txt (file, size=14848514 ), /c.dat (file, size=8504156 ), /d (dir, size=24933642), /a/e (dir, size=584), /a/f (file, size=29116 ), /a/g (file, size=2557 ), /a/h.lst (file, size=62596 ), /a/e/i (file, size=584 ), /d/j (file, size=4060174 ), /d/d.log (file, size=8033020 ), /d/d.ext (file, size=5626152 ), /d/k (file, size=7214296 ), / (dir, size=48381165)]", a7.fileDescriptors.toString());
        assertEquals(95437, a7.getTopSizes());
    }
    @Test
    void checkSizeCwdSmallestFull() throws IOException {
        var a7 = new A7();
        a7.execute(new File("a7.full.txt"));
        a7.getRecursiveSizes();
        assertEquals(24933642, a7.getSmallestSize());
    }

    @Test
    void checkSizeCwdSmallest() throws IOException {
        var a7 = new A7();
        a7.execute(new File("a7.test.txt"));
        a7.getRecursiveSizes();
        assertEquals(24933642, a7.getSmallestSize());
    }
    @Test
    void checkSizeCwdFull() throws IOException {
        var a7 = new A7();
        a7.execute(new File("a7.full.txt"));
        a7.getRecursiveSizes();
        assertEquals(4473403, a7.getSmallestSize());
    }
}