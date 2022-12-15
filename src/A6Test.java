import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class A6Test {

    @Test
    void marker() {
        assertEquals(5, A6.marker("bvwbjplbgvbhsrlpgdmjqwftvncz"));
        assertEquals(6, A6.marker("nppdvjthqldpwncqszvftbrmjlhg"));
        assertEquals(10, A6.marker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"));
        assertEquals(11, A6.marker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"));
    }

    @Test
    void markerFile() throws IOException {
        assertEquals(5, A6.marker(new File("a6.test.txt")));
    }

    @Test
    void markerMsgFile() throws IOException {
        assertEquals(5, A6.markerMsg(new File("a6.test.txt")));
    }

    @Test
    void markerMsg()
    {
        assertEquals(19, A6.markerMsg("mjqjpqmgbljsphdztnvjfqwrcgsmlb"));
        assertEquals(23, A6.markerMsg("bvwbjplbgvbhsrlpgdmjqwftvncz"));
        assertEquals(23, A6.markerMsg("nppdvjthqldpwncqszvftbrmjlhg"));
        assertEquals(29, A6.markerMsg("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"));
    }
}