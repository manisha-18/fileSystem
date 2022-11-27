package fileSystem;

import fileSystem.FileSystemFactory;
import fileSystem.FileSystemService;
import fileSystem.FileSystemType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileSystemTest {

    FileSystemService fs;

    @Before
    public void init(){
        fs = FileSystemFactory.getInstance(FileSystemType.TRIE_FS);
    }

    @Test
    public void testDirectoryCreation(){
        String path = "/a/b";
        Assert.assertTrue(fs.mkDir(path));

        String path2 = "/a/c";
        Assert.assertTrue(fs.mkDir(path2));

    }

    @Test
    public void testAddFile(){
        String path = "/a/b";
        Assert.assertTrue(fs.mkDir(path));
        String path2 = "/a/b/file1.txt";
        Assert.assertTrue(fs.addFile(path2));
    }

    @Test
    public void testGetFiles(){
        String path = "/a/b/c";
        Assert.assertTrue(fs.mkDir(path));
        String path2 = "/a/b/c/file1.txt";
        Assert.assertTrue(fs.addFile(path2));
        String path4 = "/a/e/c";
        Assert.assertTrue(fs.mkDir(path4));
        String path3 = "/a/e/c/zibb.txt";
        Assert.assertTrue(fs.addFile(path3));
        fs.getFiles("a/*/*").forEach(s-> System.out.println(s));
    }

}
