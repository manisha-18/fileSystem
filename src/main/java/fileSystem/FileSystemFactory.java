package fileSystem;

public class FileSystemFactory {

    public static FileSystemService getInstance(FileSystemType fileSystemType){
        if(fileSystemType == FileSystemType.TRIE_FS){
            return TrieBasedFileSystem.getInstance();
        }
        return null;
    }

}
