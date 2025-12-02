package a2025;

public class A1 {
    public static long calc(String puzzle) {
        var els = puzzle.split("\n");
        var cur = 50;
        int count = 0;
        for (var el : els) {

            if (el.startsWith("L")) {
                for (int l = 0; l < Integer.parseInt(el.substring(1)); l++) {
                    if ((--cur % 100) == 0) {
                        count++;
                    }
                }
            }
            if (el.startsWith("R")) {
                for (int l = 0; l < Integer.parseInt(el.substring(1)); l++) {
                    if ((++cur % 100) == 0) {
                        count++;
                    }
                }
            }

        }
        return count;
    }
}
