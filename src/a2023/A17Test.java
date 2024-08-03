package a2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class A17Test {

    String map = """
            2413432311323
            3215453535623
            3255245654254
            3446585845452
            4546657867536
            1438598798454
            4457876987766
            3637877979653
            4654967986887
            4564679986453
            1224686865563
            2546548887735
            4322674655533
            """;

    @Test
    void loadsMap() {
        var mapObj =  A17.loadMap(map);
        assertNotNull(mapObj);
    }
    @Test
    void getBestWalk() {
        var mapObj =  A17.loadMap(map);
        int bestWalk =  A17.bestWalk(mapObj);
        assertNotNull(mapObj);
    }
}