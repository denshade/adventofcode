package a2023;

import org.junit.jupiter.api.Test;

import java.util.List;

import static a2023.A12.State.*;
import static org.junit.jupiter.api.Assertions.*;

class A12Test {
    @Test
    void parseLine() {
        var v = A12.parse("???.### 1,1,3");
        assertEquals(List.of(1,1,3), v.groups);
        assertEquals(List.of(Unknown, Unknown, Unknown, Good, Broken, Broken, Broken), v.states);
    }

    @Test
    void doesLineAndOption() {
        A12.LineAndOptions line = A12.parse("#.#.### 1,1,3");
        assertTrue(A12.doesLineAndOptionAgree(line));
        A12.LineAndOptions line2 = A12.parse(".#.###.#.###### 1,3,1,6");
        assertTrue(A12.doesLineAndOptionAgree(line2));
        A12.LineAndOptions line3 = A12.parse(".#.###.#.###### 1,3,1,5");
        assertFalse(A12.doesLineAndOptionAgree(line3));
    }

    @Test
    void countLines() {
        //assertEquals(1, A12.countOptions(A12.parse("#.#.### 1,1,3")));
        //assertEquals(1, A12.countOptions(A12.parse("?.#.### 1,1,3")));
        //assertEquals(0, A12.countOptions(A12.parse("#.#.### 1,1,4")));
        assertEquals(4, A12.countOptions(A12.parse(".??..??...?##. 1,1,3")));
        assertEquals(10, A12.countOptions(A12.parse("?###???????? 3,2,1")));

    }

}