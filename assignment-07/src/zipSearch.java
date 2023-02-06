import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class zipSearch {
    //
    private static Predicate<ZipEntry> isDir = ZipEntry::isDirectory;
    private static Predicate<ZipEntry> notArcFile = entry -> MyPredicates.notArcFile(entry.toString());
    private static boolean hasTheName(ZipEntry entry, String name) {
        return new File(entry.getName()).getName().equals(name);
    }
    private static boolean hasTheContent(ZipFile archive, ZipEntry entry, String content) {
        try (InputStream in = new BufferedInputStream(archive.getInputStream(entry))) {
            return MyPredicates.containsText(in, (int) entry.getSize(), content);
        } catch (IOException e) {e.printStackTrace();return false;}
    }
    //
    public static List<ZipEntry> findByName(ZipFile archive, String name) {
        Predicate<ZipEntry> hasTheName = e -> hasTheName(e, name);
        return archive
                .stream()
                .filter(hasTheName)
                .collect(Collectors.toList());
    }

    public static List<ZipEntry> findByContent(ZipFile archive, String content) {
        Predicate<ZipEntry> hasTheContent = e -> hasTheContent(archive, e, content);
        return archive
                .stream()
                .filter(isDir.negate().and(notArcFile).and(hasTheContent))
                .collect(Collectors.toList());
    }

    public static List<ZipEntry> findByNameParallel(ZipFile archive, String name) {
        Predicate<ZipEntry> hasTheName = e -> hasTheName(e, name);
        return archive
                .stream()
                .parallel()
                .filter(hasTheName)
                .collect(Collectors.toList());
    }

    public static List<ZipEntry> findByContentParallel(ZipFile archive, String content) {
        Predicate<ZipEntry> hasTheContent = e -> hasTheContent(archive, e, content);
        return archive
                .stream()
                .parallel()
                .filter(isDir.negate().and(notArcFile).and(hasTheContent))
                .collect(Collectors.toList());
    }
}
