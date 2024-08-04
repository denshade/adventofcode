package a2023;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static a2023.A17.Direction.*;
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
        var mapObj = testMap();
        assertNotNull(mapObj);
    }
    @Test
    void getBestWalk() {
        var mapObj = testMap();
        int bestWalk =  A17.bestWalk(mapObj);
        assertEquals(102, bestWalk);
    }

    @Test
    void checkPossibleWalks() {
        var w = new A17.Walk();
        assertEquals(List.of(Down, Right), w.allowedDirections(10,10));
        var w1 = new A17.Walk();
        w1.directionsSoFar = List.of(Down, Down, Down);
        assertEquals(List.of(Right), w1.allowedDirections(10,10));
    }

    @Test
    void walkTo() {
        var w = new A17.Walk();
        var newW = A17.Walk.moveTo(testMap(), w, Down);
        assertEquals(0, newW.x);
        assertEquals(1, newW.y);
        assertEquals(Down, newW.currentDirection);
        assertEquals(List.of(Down), newW.directionsSoFar);
        assertEquals(3, newW.currentHeat);
        var newW2 = A17.Walk.moveTo(testMap(), newW, Down);
        assertEquals(0, newW2.x);
        assertEquals(2, newW2.y);
        assertEquals(Down, newW2.currentDirection);
        assertEquals(List.of(Down, Down), newW2.directionsSoFar);
        assertEquals(6, newW2.currentHeat);
        var newW3 = A17.Walk.moveTo(testMap(), newW2, Right);
        assertEquals(1, newW3.x);
        assertEquals(2, newW3.y);
        assertEquals(Right, newW3.currentDirection);
        assertEquals(List.of(Down, Down, Right), newW3.directionsSoFar);
        assertEquals(8, newW3.currentHeat);
        var newW4 = A17.Walk.moveTo(testMap(), newW3, Left);
        assertEquals(0, newW4.x);
        assertEquals(2, newW4.y);
        assertEquals(Left, newW4.currentDirection);
        assertEquals(List.of(Down, Down, Right, Left), newW4.directionsSoFar);
        assertEquals(11, newW4.currentHeat);
        var newW5 = A17.Walk.moveTo(testMap(), newW4, Up);
        assertEquals(0, newW5.x);
        assertEquals(1, newW5.y);
        assertEquals(Up, newW5.currentDirection);
        assertEquals(List.of(Down, Down, Right, Left, Up), newW5.directionsSoFar);
        assertEquals(14, newW5.currentHeat);
    }

    @Test
    @Disabled
    void bruteForceSearch() {
        var searcher = new A17.BruteforceSearch(testMap());
        A17.Walk actual = searcher.find();
        assertNotNull(actual);
        assertEquals(102, actual.currentHeat);
    }


    @Test
    void AStarSearch() {
        var searcher = new A17.AStar(testMap());
        A17.Walk actual = searcher.find();
        assertNotNull(actual);
        assertEquals(102, actual.currentHeat);
    }

    @Test
    void fakeWalk() {
        var s = new A17.FakeStrategy(testMap());
        var actual = s.find();
        assertEquals(102, actual.currentHeat);
    }

    @Test
    void AStarSearchSmall() {
        var searcher = new A17.AStar(A17.loadMap("""
                229
                929
                922
                """));
        A17.Walk actual = searcher.find();
        assertNotNull(actual);
        assertEquals(10, actual.currentHeat);
    }



    private int[][] testMap() {
        return A17.loadMap(map);
    }
}