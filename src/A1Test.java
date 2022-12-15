import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class A1Test {

    @Test
    void checkCaloriesPerElf() throws IOException {
        assertEquals(List.of(6000, 4000, 11000, 24000, 10000).toString(), A1.caloriesPerElf(new File("calories.txt")).toString());
    }

    @Test
    void checkMaxElf() throws IOException {
        assertEquals(24000, A1.maxCaloriesForAllElves(new File("calories.txt")));
    }

    @Test
    void checkTop3() throws IOException {
        assertEquals(45000, A1.top3Elves(new File("calories.txt")));
    }

    @Test
    void checkTop3Advent() throws IOException {
        assertEquals(203420, A1.top3Elves(new File("calories_advent.txt")));
    }

    @Test
    void checkAdvent() throws IOException {
        assertEquals(68467, A1.maxCaloriesForAllElves(new File("calories_advent.txt")));
    }

}