package a2023;

import java.util.*;
import java.util.stream.Collectors;

public class A17 {

    private static final List<Direction> SOLUTION = List.of(
        Direction.Right, Direction.Right, Direction.Down,
        Direction.Right, Direction.Right, Direction.Right,
            Direction.Up, Direction.Right, Direction.Right, Direction.Right, Direction.Down,
            Direction.Down, Direction.Right, Direction.Right,
            Direction.Down,
            Direction.Down,
            Direction.Right,
            Direction.Down,
            Direction.Down,
            Direction.Down,
            Direction.Right,
            Direction.Down,
            Direction.Down,
            Direction.Down,
            Direction.Left,
            Direction.Down,
            Direction.Down,
            Direction.Right
            );

    public static int[][] loadMap(String map) {
        var lines = map.split("\n");
        int[][] res = new int[lines.length][lines[0].length()];
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                res[y][x] = lines[y].charAt(x) - '0';
            }
        }
        return res;
    }

    public enum Direction {
        Up, Down, Left, Right;
        public static List<Direction> allowedDirections(Direction direction) {
            return switch (direction) {
                case Up -> List.of(Up, Left, Right);
                case Down -> List.of(Down, Left, Right);
                case Left -> List.of(Up, Down, Left) ;
                case Right -> List.of(Up, Down, Right);
            };
        }
    }

    public static class Point {
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
        int x;
        int y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static class Walk {
        public Direction currentDirection = Direction.Down;
        public int x = 0;
        public int y = 0;
        public int currentHeat = 0;
        public List<Direction> directionsSoFar = new ArrayList<>();

        public boolean isBad(){
            return false;
        }

        public List<Direction> allowedDirections(int width, int height){
            var list = new ArrayList<Direction>(4);
            for (var dir : Direction.allowedDirections(currentDirection)) {
                if (dir == Direction.Left && x > 0) {
                    list.add(dir);
                }
                if (dir == Direction.Right && x < width - 1) {
                    list.add(dir);
                }
                if (dir == Direction.Up && y > 0) {
                    list.add(dir);
                }
                if (dir == Direction.Down && y < height - 1) {
                    list.add(dir);
                }
            }
            if (directionsSoFar.size() > 2) {
                var lastDirections = directionsSoFar.subList(directionsSoFar.size() - 3, directionsSoFar.size());
                if (lastDirections.stream().filter(e -> e == currentDirection).count() > 2) {
                    list.remove(currentDirection);
                }
            }
            return list;
        }

        public Walk clone() {
            Walk walk = new Walk();
            walk.directionsSoFar = new ArrayList<>(directionsSoFar);
            walk.currentDirection = currentDirection;
            walk.x = x;
            walk.y = y;
            walk.currentHeat = currentHeat;
            return walk;
        }

        public Walk moveTo(int[][] mapObj, Walk walk, Direction direction) {
            var newWalk = walk.clone();
            switch(direction) {
                case Up -> newWalk.y--;
                case Down -> newWalk.y++;
                case Left -> newWalk.x--;
                case Right -> newWalk.x++;
            }
            newWalk.currentDirection = direction;
            newWalk.currentHeat += mapObj[newWalk.y][newWalk.x];
            newWalk.directionsSoFar.add(direction);
            return newWalk;
        }

        public boolean isFinal(int height, int width) {
            return (x == width - 1 && y == height - 1);
        }
    }

    public static class WalkWithPoints extends Walk {
        public List<Point> visitedPoints = new ArrayList<>();

        public WalkWithPoints() {
            visitedPoints.add(new Point(0,0));
        }

        @Override
        public boolean isBad() {
            var lastPosition = visitedPoints.get(visitedPoints.size() - 1);
            int position = visitedPoints.indexOf(lastPosition);
            if (position != visitedPoints.size() - 1) {
                return true;
            }
            return false;
        }

        public WalkWithPoints clone() {
            WalkWithPoints walk = new WalkWithPoints();
            walk.directionsSoFar = new ArrayList<>(directionsSoFar);
            walk.currentDirection = currentDirection;
            walk.x = x;
            walk.y = y;
            walk.currentHeat = currentHeat;
            walk.visitedPoints = new ArrayList<>(visitedPoints);
            return walk;
        }

        public Walk moveTo(int[][] mapObj, Walk walk, Direction direction) {

            var newWalk = (WalkWithPoints)walk.clone();
            switch(direction) {
                case Up -> newWalk.y--;
                case Down -> newWalk.y++;
                case Left -> newWalk.x--;
                case Right -> newWalk.x++;
            }
            newWalk.currentDirection = direction;
            newWalk.currentHeat += mapObj[newWalk.y][newWalk.x];
            newWalk.directionsSoFar.add(direction);
            newWalk.visitedPoints.add(new Point(newWalk.y, newWalk.x));
            return newWalk;
        }
    }


    public static class BruteforceSearch {
        private final int[][] mapObj;
        private int height;
        private int width;

        BruteforceSearch(int[][] mapObj) {
            this.mapObj = mapObj;
            height = mapObj.length;
            width = mapObj[0].length;
        }
        public Walk findNext(Walk currentWalk) {
            if (currentWalk.isFinal(height, width)) {
                return currentWalk;
            }
            if (currentWalk.currentHeat > 105) {
                return null;
            }
            Walk bestWalk = null;
            for (Direction dir : currentWalk.allowedDirections(width, height)) {
                var newWalk = currentWalk.moveTo(mapObj, currentWalk, dir);
                var bestFinalWalk = findNext(newWalk);
                if (bestFinalWalk == null) continue;// no solutions here, skip.
                if (bestWalk == null) {
                    bestWalk = bestFinalWalk;
                }
                if (bestWalk.currentHeat > bestFinalWalk.currentHeat) {
                    bestWalk = bestFinalWalk;
                }
            }
            return bestWalk;
        }
        public Walk find() {
            return findNext(new Walk());
        }
    }

    public static class BruteGreedSearch {
        private int[][] mapObj;
        private int height;
        private int width;
        int[][] heuristicMap;
        Walk bestWalk;

        public BruteGreedSearch(int[][] mapObj) {
            this.mapObj = mapObj;
            height = mapObj.length;
            width = mapObj[0].length;
            heuristicMap = new HeuristicMapBuilder().build(mapObj);
            bestWalk = new Walk();
            bestWalk.currentHeat = 920;
        }
        public Walk find() {
            width = mapObj[0].length;
            height = mapObj.length;
            Walk walk = new Walk();
            return findRecurse(walk);
        }
        public Walk findRecurse(Walk walk) {
            if (walk.isFinal(height, width)) {
                if (bestWalk == null || walk.currentHeat < bestWalk.currentHeat) {
                    bestWalk = walk;
                    System.out.println(bestWalk.currentHeat);
                }
                return walk;
            }
            if (bestWalk != null && walk.currentHeat > bestWalk.currentHeat) return bestWalk;
            if (walk.directionsSoFar.size() > 200) return bestWalk;
            var sortedWalks = new ArrayList<Walk>();
            for (Direction direction : walk.allowedDirections(width, height)) {
                var w = walk.moveTo(mapObj, walk, direction);
                sortedWalks.add(w);
            }
            sortedWalks.sort(Comparator.comparingInt(a -> heuristicMap[a.y][a.x]));
            Walk bestWalk = null;
            for (Walk w : sortedWalks) {
                var resultingWalk = findRecurse(w);
                if (bestWalk == null || resultingWalk.currentHeat < bestWalk.currentHeat) {
                    bestWalk = resultingWalk;
                }
            }
            return bestWalk;
        }
    }
    public static class AStar {
        private final int[][] mapObj;
        private final int height;
        private final int width;

        public AStar(int[][] mapObj) {
            this.mapObj = mapObj;
            height = mapObj.length;
            width = mapObj[0].length;
        }
        public static class Solution implements Comparable<Solution> {
            private final Walk walkSoFar;
            private final int[][] heuristicMap;

            public Solution(Walk walkSoFar, int[][] heuristicMap) {
                this.walkSoFar = walkSoFar;
                this.heuristicMap = heuristicMap;
            }
            public int score() {
                return walkSoFar.currentHeat + (int)(heuristicMap[walkSoFar.y][walkSoFar.x] * 1);
            }

            @Override
            public int compareTo(Solution o) {
                return Integer.compare(score(), o.score());
            }
        }

        public Walk find() {
            int[][] heuristicMap = new HeuristicMapBuilder().build(mapObj);
            int[][] bestSolutions = new int[mapObj.length][mapObj[0].length];
            for (int y = 0; y < bestSolutions.length; y++)
                for (int x = 0; x < bestSolutions.length; x++)
                    bestSolutions[y][x] = Integer.MAX_VALUE;
 /*           Walk averageWalk = new Walk();
            for (int i = 0; i < height - 1; i++) {
                averageWalk = averageWalk.moveTo(mapObj, averageWalk, Direction.Down);
                averageWalk = averageWalk.moveTo(mapObj, averageWalk, Direction.Right);
            }*/

            var queue = new PriorityQueue<Solution>();
            Walk walkSoFar = new WalkWithPoints();
            Solution solution = new Solution(walkSoFar, heuristicMap);
            queue.add(solution);
            while(!queue.isEmpty()) {
                var currentSolution = queue.poll();
                if (queue.size()%1000000 ==0) {
                    System.out.println(currentSolution.score());
                    System.out.println(queue.size());
                }
                if (currentSolution.walkSoFar.isFinal(height, width)) {
                    System.out.println(currentSolution.walkSoFar.directionsSoFar.size());
                    return currentSolution.walkSoFar;
                }
                for (Direction direction : currentSolution.walkSoFar.allowedDirections(width, height)) {
                    Solution e = new Solution(walkSoFar.moveTo(mapObj, currentSolution.walkSoFar, direction), heuristicMap);
                    if (e.walkSoFar.isBad()) continue;
                    //if (bestSolutions[e.walkSoFar.y][e.walkSoFar.x] < e.walkSoFar.currentHeat) continue;
                    //bestSolutions[e.walkSoFar.y][e.walkSoFar.x] = e.walkSoFar.currentHeat;
                    /*if (SOLUTION.subList(0, e.walkSoFar.directionsSoFar.size()).equals(e.walkSoFar.directionsSoFar)) {
                        System.out.println("OK");
                    } else {
                        continue;
                    }*/
                    //check no other solution has reached this point already.
                    /*var lastPosition = e.walkSoFar.visitedPoints.get(e.walkSoFar.visitedPoints.size() - 1);
                    int position = e.walkSoFar.visitedPoints.indexOf(lastPosition);
                    if (position != e.walkSoFar.visitedPoints.size() - 1) {
                        continue;
                    }*/
                    if (e.walkSoFar.currentHeat <= 922){//e.walkSoFar.currentHeat <= 922){ //no solution for less than 1265.
                        queue.add(e);
                    } //not 792 & not 791. not 936.
                }
            }
            return null;
        }
    }
    public static class GeneticSearch {
        
    }

    public static class HeuristicMapBuilder {
        int[][] build(int[][] mapObj) {
            int[][] heuristicMap = new int[mapObj.length][mapObj[0].length];
            for (int[] row: heuristicMap)
                Arrays.fill(row, Integer.MAX_VALUE / 2);
            heuristicMap[mapObj.length - 1][mapObj[0].length - 1] = 0;
            boolean changed = true;
            while(changed) {
                changed = false;
                for (int y = 0; y < heuristicMap.length; y++)
                    for (int x = 0; x < heuristicMap[y].length; x++)
                    {
                        changed |= updateFromAround(heuristicMap,mapObj, y, x);
                    }
            }
            return heuristicMap;
        }

        private boolean updateFromAround(int[][] heuristicMap, int[][] mapObj, int y, int x) {
            boolean changed = false;
            int height = mapObj.length;
            int width = mapObj[0].length;
            boolean hasUp = y > 0;
            boolean hasDown = y < height - 1;
            boolean hasLeft = x > 0;
            boolean hasRight = x < width - 1;
            int up = 0;
            if (hasUp) {
                up = heuristicMap[y - 1][x];
            }
            int down = 0;
            if (hasDown) {
                down = heuristicMap[y + 1][x];
            }
            int left = 0;
            if (hasLeft) {
                left = heuristicMap[y][x - 1];
            }
            int right = 0;
            if (hasRight) {
                right = heuristicMap[y][x + 1];
            }

            if (hasUp && heuristicMap[y][x] > up + mapObj[y][x]){
                heuristicMap[y][x] = up + mapObj[y][x];
                changed=true;
            }
            if (hasLeft && heuristicMap[y][x] > left + mapObj[y][x]){
                heuristicMap[y][x] = left + mapObj[y][x];
                changed =true;
            }
            if (hasRight && heuristicMap[y][x] > right + mapObj[y][x]){
                heuristicMap[y][x] = right + mapObj[y][x];
                changed = true;
            }
            if (hasDown && heuristicMap[y][x] > down + mapObj[y][x]){
                heuristicMap[y][x] = down + mapObj[y][x];
                changed = true;
            }
            return changed;
        }
    }

    public static class FakeStrategy {
        private final int[][] board;

        FakeStrategy(int[][] board) {
            this.board = board;
        }

        public Walk find() {
            int[][] heuristicMap = new HeuristicMapBuilder().build(board);

            var walk = new Walk();
            for (var direction : SOLUTION) {
                int width = board[0].length;
                int height = board.length;
                if (!walk.allowedDirections(width, height).contains(direction)) {
                    System.out.println("WHOOPS");
                }
                System.out.println(new AStar.Solution(walk, heuristicMap).score());
                walk = walk.moveTo(board, walk, direction);
            }
            return walk;

        }
    }

}
