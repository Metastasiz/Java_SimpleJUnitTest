import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class regSearch {
    //
    private static Predicate<Path> isRegFile = Files::isRegularFile;
    private static Predicate<Path> notArcFile = e -> MyPredicates.notArcFile(e.toString());
    private static boolean hasTheName(Path p, String name){return p.getFileName().toString().equals(name);}
    private static boolean hasTheContent(Path p, String content) {
        File file = p.toFile();
        try (InputStream in = new FileInputStream(file)) {
            return MyPredicates.containsText(in, (int) file.length(), content);
        } catch (IOException e) {e.printStackTrace();return false; }
    }
    //
    public static List<File> findByName(Path path, String name) {
        Predicate<Path> hasTheName = e -> hasTheName(e, name);
        List<File> files = null;
        try {
            files = Files
                    .walk(path)
                    .filter(hasTheName)
                    .map(e -> new File(e.toString()))
                    .collect(Collectors.toList());
        } catch (IOException e) {e.printStackTrace();}
        return files;
    }
    public static List<File> findByContent(Path path, String content) {
        Predicate<Path> hasTheContent = e -> hasTheContent(e, content);
        List<File> files = null;
        try {
            files = Files
                    .walk(path)
                    .filter(isRegFile.and(notArcFile).and(hasTheContent))
                    .map(p -> new File(p.toString()))
                    .collect(Collectors.toList());
        } catch (IOException e) {e.printStackTrace();}
        return files;
    }

    public static List<File> findByNameParallel(Path path, String name) {
        Predicate<Path> hasTheName = e -> hasTheName(e, name);
        List<File> files = null;
        try {
            files = Files.walk(path)
                    .parallel()
                    .filter(hasTheName)
                    .map(p -> new File(p.toString()))
                    .collect(Collectors.toList());
        } catch (IOException e) {e.printStackTrace();}
        return files;
    }

    public static List<File> findByContentParallel(Path path, String content) {
        Predicate<Path> hasTheContent = e -> hasTheContent(e, content);
        List<File> files = null;
        try {
            files = Files
                    .walk(path)
                    .parallel()
                    .filter(isRegFile.and(notArcFile).and(hasTheContent))
                    .map(p -> new File(p.toString()))
                    .collect(Collectors.toList());
        } catch (IOException e) {e.printStackTrace();}
        return files;
    }
}
