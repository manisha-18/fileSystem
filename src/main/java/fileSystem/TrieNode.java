package fileSystem;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private String name;
    private Map<String, TrieNode> children;
    private boolean isFile;

    public TrieNode(String name) {
        this.name = name;
        this.children = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(Map<String, TrieNode> children) {
        this.children = children;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }
}
