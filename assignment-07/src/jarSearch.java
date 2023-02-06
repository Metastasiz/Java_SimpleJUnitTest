import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class jarSearch {

    private static Predicate<JarEntry> isDir = JarEntry::isDirectory;
    private static Predicate<JarEntry> notArcFile = e -> MyPredicates.notArcFile(e.toString());
    private static boolean hasTheName(JarEntry entry, String name) {
        return new File(entry.getName()).getName().equals(name);
    }
    private static boolean hasTheContent(JarFile archive, JarEntry entry, String content) {
        try (InputStream in = new BufferedInputStream(archive.getInputStream(entry))) {
            return MyPredicates.containsText(in, (int) entry.getSize(), content);
        } catch (IOException e) {e.printStackTrace();return false;}
    }
    //
    public static List<JarEntry> findByName(JarFile archive, String name) {
        Predicate<JarEntry> hasTheName = e -> hasTheName(e, name);
        return archive
                .stream()
                .filter(hasTheName)
                .collect(Collectors.toList());
    }

    public static List<JarEntry> findByContent(JarFile archive, String content) {
        Predicate<JarEntry> hasTheContent = e -> hasTheContent(archive, e, content);
        return archive
                .stream()
                .filter(isDir.negate().and(notArcFile).and(hasTheContent))
                .collect(Collectors.toList());
    }

    public static List<JarEntry> findByNameParallel(JarFile archive, String name) {
        Predicate<JarEntry> hasTheName = e -> hasTheName(e, name);
        return archive
                .stream()
                .parallel()
                .filter(hasTheName)
                .collect(Collectors.toList());
    }

    public static List<JarEntry> findByContentParallel(JarFile archive, String content) {
        Predicate<JarEntry> hasTheContent = e -> hasTheContent(archive, e, content);
        return archive
                .stream()
                .parallel()
                .filter(isDir.negate().and(notArcFile).and(hasTheContent))
                .collect(Collectors.toList());
    }
}
