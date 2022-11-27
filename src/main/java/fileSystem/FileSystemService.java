package fileSystem;

import java.util.List;

public interface FileSystemService {

    boolean mkDir(String path);
    boolean addFile(String path);
    List<String> getFiles(String path);

}
