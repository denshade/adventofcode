package a2022;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class A10Test {

    @Test
    void calculate() throws IOException {
        assertEquals(13140,A10.calculate(new File("a10.test.txt")));
    }
    @Test
    void calculateFull() throws IOException {
        assertEquals(12740,A10.calculate(new File("a10.full.txt")));
    }

    @Test
    void calculateTest() throws IOException {
        assertEquals(12740,A10.calculate(new File("a10.full.txt")));
        //RBPARAGF
    }
}