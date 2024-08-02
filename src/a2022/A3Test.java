package a2022;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class A3Test {

    @Test
    void findDuplicate() {
        assertEquals("p", A3.getDuplicated("vJrwpWtwJgWrhcsFMMfFFhFp"));
    }

    @Test
    void findExample() throws IOException {
        assertEquals(157, A3.calculate(new File("rucksack.txt")));
    }
    @Test
    void findExampleFull() throws IOException {
        assertEquals(7581, A3.calculate(new File("rucksack_full.txt")));
    }
    @Test
    void findTriple() throws IOException {
        assertEquals(70, A3.calculateTriple(new File("rucksack.txt")));
    }
    @Test
    void findTripleFull() throws IOException {
        assertEquals(70, A3.calculateTriple(new File("rucksack_full.txt")));
    }
    @Test
    void map() throws IOException {
        assertEquals(1, A3.toScore('a'));
        assertEquals(26, A3.toScore('z'));
        assertEquals(27, A3.toScore('A'));
        assertEquals(52, A3.toScore('Z'));

    }


}