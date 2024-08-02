package a2022;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class A6
{
    public static long marker(String str)
    {
        return marker(str, 4);
    }

    public static long markerMsg(String str)
    {
        return marker(str, 14);
    }


    public static long marker(String str, int mark)
    {
        var charQ = new FifoFixedSizeQueue<Character>(mark);
        for (int i = 0; i < str.length(); i++)
        {
            charQ.offer(str.charAt(i));
            Set<Character> items = new HashSet<>();
            boolean hasNulls = false;
            for (Object o : charQ.items)
            {
                if (o == null) {
                    hasNulls = true;
                    break;
                }
                items.add((Character)o);
            }
            if (hasNulls) {
                continue;
            }

            if (items.size() == mark) {
                return i + 1;
            }
        }
        return 0;
    }
    public static long marker(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        return marker(lines.get(0));
    }

    public static long markerMsg(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        return markerMsg(lines.get(0));
    }
}
