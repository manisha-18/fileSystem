package fileSystem;

import java.util.*;

public class TrieBasedFileSystem implements FileSystemService{

    private volatile static TrieBasedFileSystem fs;
    private static TrieNode root;
    private static final Object lock = new Object();

    private TrieBasedFileSystem(){
        if(fs != null){
            throw new IllegalStateException("Not allowed to use constructor");
        }
    }

    public static TrieBasedFileSystem getInstance(){
        if(fs == null){
            synchronized (lock){
                if(fs == null){
                    root = new TrieNode("/");
                    fs = new TrieBasedFileSystem();
                }
            }
        }
        return fs;
    }

    @Override
    public boolean mkDir(String path) {
        if(!path.isEmpty() && path.charAt(0) == '/'){
            path = path.substring(1);
        }
        String[] pathSplit = path.split("/");
        TrieNode curr = root;
        int i = 0;
        for(; i < pathSplit.length; i++){
            String dir = pathSplit[i];
            if(curr.getChildren().get(dir) != null){
                curr = curr.getChildren().get(dir);
            }
            else{
                break;
            }
        }

        for(; i < pathSplit.length; i++){
            TrieNode trie = new TrieNode(pathSplit[i]);
            String[] filename = pathSplit[i].split(".");
            if(filename.length == 2 ){
                trie.setFile(true);
            }
            curr.getChildren().put(pathSplit[i], trie) ;
            curr = curr.getChildren().get(pathSplit[i]);
        }

        return true;
    }

    @Override
    public boolean addFile(String path) {
        if(!path.isEmpty() && path.charAt(0) == '/'){
            path = path.substring(1);
        }
        String[] pathSplit = path.split("/");

        TrieNode curr = root;
        int i = 0;
        boolean pathExists = false;
        for(; i < pathSplit.length; i++){
            String dir = pathSplit[i];
            if(curr.getChildren().get(dir) != null){
                curr = curr.getChildren().get(dir);
                pathExists = true;
            }
            else{
                break;
            }
        }

        if(!pathExists){
            return false;
        }
        TrieNode trie = new TrieNode(pathSplit[i]);
        trie.setFile(true);
        curr.getChildren().put(pathSplit[i], trie);
        return true;
    }

    @Override
    public List<String> getFiles(String path) {
        if(!path.isEmpty() && path.charAt(0) == '/'){
            path = path.substring(1);
        }
        String[] pathSplit = path.split("/");
        TrieNode curr = root;
        return returnFiles(new StringBuilder(), pathSplit, 0, curr, new ArrayList<String>());
    }

    private List<String> returnFiles(StringBuilder pattern, String[] pathSplit, int index, TrieNode curr, List<String> result){

        if(index == pathSplit.length){
            result.add(pattern.toString());
            return result;
        }


        String dir = pathSplit[index];

        if(dir.equals("*")){
            for (Map.Entry<String, TrieNode> entry : curr.getChildren().entrySet()) {
                if(index+1 < pathSplit.length && entry.getValue().getChildren().containsKey(pathSplit[index+1])) {
                    String nextPath = pattern + entry.getKey() + "/";
                    returnFiles(new StringBuilder(nextPath), pathSplit, index + 1, entry.getValue(), result);
                }
            }
        }
        else if(curr.getChildren().get(dir) != null) {
            if(curr.getChildren().get(dir).isFile()){
                if(dir.equals(pathSplit[index])){
                    result.add(pattern.toString() + dir);
                    return result;
                }
                else{
                    return result;
                }
            }
            pattern.append(dir).append("/");
            curr = curr.getChildren().get(dir);
        }
        returnFiles(pattern, pathSplit, index + 1, curr, result);

        return result;

    }
}
