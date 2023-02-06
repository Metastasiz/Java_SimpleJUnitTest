import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class FileUtilParser {
    public static List<File> search(File dir, String name, BiPredicate<Path, String> predicate){
        if (!dir.isDirectory()){return null;}
        try{
            Path path = dir.toPath();
            List<File> files = Files
                    .walk(path)
                    .parallel()
                    .filter(file -> predicate.test(file, name))
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .collect(Collectors.toList());
            return files;
        } catch (IOException e) {e.printStackTrace();}
        return null;
    }
}
