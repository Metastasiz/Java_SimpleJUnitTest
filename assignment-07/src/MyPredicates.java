import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class MyPredicates {
    private static final List<String> ARCHIVE_EXTENSIONS = new LinkedList<>(Arrays.asList(".zip", ".tar", ".jar", ".rar", ".7z"));
    //
    public static boolean notArcFile(String in) {
        for (String extension : ARCHIVE_EXTENSIONS)if (in.endsWith(extension)) return false;
        return true;
    }
    public static boolean containsText(InputStream input, int length, String content) {
        Scanner scanner = new Scanner(input);
        String result = scanner.findWithinHorizon(content, length);
        return result != null;
    }
}
