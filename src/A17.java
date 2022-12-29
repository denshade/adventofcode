import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class A17
{
    public enum Moves {
        Left, Right;
        static List<Moves> parse(String moves){
            return moves.chars().mapToObj(e-> e=='<'?Left:Right).collect(Collectors.toList());
        }
    }
    public static int calculateHeight(String directions)
    {
        return 0;
    }

}
