package a2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.zip.CheckedInputStream;

public class A17 {

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
            Direction inverseDirection = switch (direction) {
                case Up -> Down;
                case Down -> Up;
                case Left -> Right;
                case Right -> Left;
            };
            var directions = Arrays.stream(values()).collect(Collectors.toList());
            directions.remove(inverseDirection);
            return directions;
        }
    }

    public static class Walk {
        public Direction currentDirection = Direction.Down;
        public int x = 0;
        public int y = 0;
        public int currentHeat = 0;
        public List<Direction> directionsSoFar = new ArrayList<>();
        public List<Direction> allowedDirections(int width, int height){
            var list = new ArrayList<Direction>();
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
                    return list.stream().filter(e -> e != currentDirection).collect(Collectors.toList());
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

        public static Walk moveTo(int[][] mapObj, Walk walk, Direction direction) {
            var newWalk = walk.clone();
            switch(direction) {
                case Up -> newWalk.y = newWalk.y-1;
                case Down -> newWalk.y = newWalk.y + 1;
                case Left -> newWalk.x = newWalk.x - 1;
                case Right -> newWalk.x = newWalk.x + 1;
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
                var newWalk = Walk.moveTo(mapObj, currentWalk, dir);
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

    public static class AStar {
        private int[][] mapObj;
        private int height;
        private int width;

        public AStar(int[][] mapObj) {
            this.mapObj = mapObj;
            height = mapObj.length;
            width = mapObj[0].length;
        }
        private static class Solution implements Comparable<Solution> {
            private final Walk walkSoFar;
            private int height;
            private int width;
            public Solution(Walk walkSoFar, int height, int width) {
                this.height = height;
                this.width = width;
                this.walkSoFar = walkSoFar;
            }
            public int score() {
                var distance = height - walkSoFar.y + width - walkSoFar.x;
                return walkSoFar.currentHeat + distance*2;
            }

            @Override
            public int compareTo(Solution o) {
                return Integer.compare(score(), o.score());
            }
        }
        public Walk find() {
            var queue = new PriorityQueue<Solution>();
            Walk walkSoFar = new Walk();
            Solution solution = new Solution(walkSoFar, height, width);
            queue.add(solution);
            while(queue.size() > 0) {
                var currentSolution = queue.poll();
                if (queue.size()%10000 ==0) {
                    System.out.println(currentSolution.score());
                    System.out.println(queue.size());
                }
                if (currentSolution.walkSoFar.isFinal(height, width)) {
                    return currentSolution.walkSoFar;
                }
                for (Direction direction : currentSolution.walkSoFar.allowedDirections(width, height)) {
                    Solution e = new Solution(Walk.moveTo(mapObj, currentSolution.walkSoFar, direction), height, width);
                    if (e.walkSoFar.directionsSoFar.size() < 20){
                        queue.add(e);
                    }
                }
            }
            return null;
        }
    }

    public static class FakeStrategy {
        private final int[][] board;

        FakeStrategy(int[][] board) {
            this.board = board;
        }

        public Walk find() {
            var walk = new Walk();
            var directions = List.of(
                Direction.Right, Direction.Right, Direction.Down,
                Direction.Right, Direction.Right, Direction.Right,
                    Direction.Up, Direction.Right, Direction.Right, Direction.Right, Direction.Down,
                    Direction.Down,Direction.Right, Direction.Right,
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
            for (var direction : directions) {
                if (!walk.allowedDirections(board[0].length, board.length).contains(direction)) {
                    System.out.println("WHOOPS");
                }
                walk = Walk.moveTo(board, walk, direction);
            }
            return walk;

        }
    }


    public static int bestWalk(int[][] mapObj) {
        int height = mapObj.length;
        int width = mapObj[0].length;
        //loop over the
        var walk = new Walk();
        return 0;
    }
}
