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

}