package hackme.util.plugin;

import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class PluginManagement {

    public boolean searchPlugin(String name) throws Exception {

        Path direct = FileSystems.getDefault().getPath( "src/main/resources/modules/" );
            DirectoryStream<Path> stream = Files.newDirectoryStream(direct, "*.jar");
            for (Path path : stream) {
                String pathName = path.getFileName().toString().substring(0,path.getFileName().toString().lastIndexOf("."));
                if (pathName.equals(name)) {
                    return true;
                }
            }
            stream.close();
        return false;
    }


}
