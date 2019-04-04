package alienAlphabet;

import java.util.HashSet;

public class Node {
    public char value;
    public HashSet<Node> children;
    public boolean visited;
    public boolean visiting;

    public Node(char value){
        this.value = value;
        children = new HashSet<Node>();
        visited = false;
        visiting = false;
    }
}
