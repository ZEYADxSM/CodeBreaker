import java.util.regex.Pattern;

public class Prepare {
    private static final Pattern NON_ALPHABETIC_PATTERN = Pattern.compile("[^a-zA-Z ]");
    public static String prepareText(String text) {
        String sanitizedText = NON_ALPHABETIC_PATTERN.matcher(text).replaceAll("");
        return sanitizedText.toUpperCase();
    }
}