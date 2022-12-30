import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class DynamicBlocks {
    public final java.util.List<Point> points;

    public static DynamicBlocks create(int xOffset, int height, A17.BlockType blockType) {
        var pts = switch (blockType) {
            case Minus -> java.util.List.of(new Point(xOffset, height), new Point(xOffset + 1, height), new Point(xOffset + 2, height), new Point(xOffset + 3, height));
            case Plus -> java.util.List.of(
                    new Point(xOffset + 1, height),
                    new Point(xOffset, height + 1), new Point(xOffset + 1, height + 1), new Point(xOffset + 2, height + 1),
                    new Point(xOffset + 1, height + 2));
            case L -> java.util.List.of(new Point(xOffset, height), new Point(xOffset + 1, height), new Point(xOffset + 2, height),
                    new Point(xOffset + 2, height + 1),
                    new Point(xOffset + 2, height + 2)
            );
            case I -> java.util.List.of(new Point(xOffset, height),
                    new Point(xOffset, height + 1),
                    new Point(xOffset, height + 2),
                    new Point(xOffset, height + 3)
            );
            case Block -> java.util.List.of(new Point(xOffset, height), new Point(xOffset + 1, height),
                    new Point(xOffset, height + 1), new Point(xOffset + 1, height + 1));
            default -> throw new IllegalStateException("Unexpected value: " + blockType);
        };
        return new DynamicBlocks(pts);
    }

    private DynamicBlocks(List<Point> points) {
        this.points = points;
    }

    public DynamicBlocks moveToLeft(A17.Board board) {
        if (points.stream().anyMatch(p -> p.x == 0)) {
            return this;
        }
        DynamicBlocks newPosition = new DynamicBlocks(points.stream().map(p -> new Point(p.x - 1, p.y)).collect(Collectors.toList()));
        if (newPosition.points.stream().anyMatch(board::hasPoint)) {
            return this;
        }
        return newPosition;
    }
    public DynamicBlocks moveToRight(int width, A17.Board board) {
        if (points.stream().anyMatch(p -> p.x == width - 1)) {
            return this;
        }
        DynamicBlocks newPosition = new DynamicBlocks(points.stream().map(p -> new Point(p.x + 1, p.y)).collect(Collectors.toList()));
        if (newPosition.points.stream().anyMatch(board::hasPoint)) {
            return this;
        }
        return newPosition;
    }

    public DynamicBlocks moveDown(A17.Board board) {
        DynamicBlocks newPosition = new DynamicBlocks(points.stream().map(p -> new Point(p.x, p.y - 1)).collect(Collectors.toList()));
        if (newPosition.points.stream().anyMatch(board::hasPoint)) {
            throw new IllegalStateException("Nope!");
        }
        return newPosition;
    }


}
