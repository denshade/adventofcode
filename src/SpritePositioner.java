public class SpritePositioner
{
    public static String createSprite(int x)
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < x; i++) {
            builder.append('.');
        }
        builder.append("###");
        return builder.toString();
    }

    public static String getValue(int crtPosition, int registerPosition) {
        var s = createSprite(registerPosition);
        if (crtPosition >= s.length()) {
            return ".";
        }
        return String.valueOf(s.charAt(crtPosition));

    }
}
