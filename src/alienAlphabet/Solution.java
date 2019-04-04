package alienAlphabet;

import java.util.HashMap;

public class Solution {
    public String alienOrder(String[] words) {
        HashMap<Character, Node> graph = new HashMap<Character, Node>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String lastWord = i > 0 ? words[i - 1] : null;

            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                Character boxed = new Character(c);
                // make sure the current letter is in the graph
                if (!graph.containsKey(boxed)) graph.put(boxed, new Node(c));

                if (lastWord != null && lastWord.length() > j) {
                    String prefix = word.substring(0, j);
                    String lastPrefix = lastWord.substring(0, j);
                    char lastC = lastWord.charAt(j);
                    Character lastBoxed = new Character(lastC);
                    if (prefix.equals(lastPrefix) && !boxed.equals(lastBoxed)){
                        graph.get(lastBoxed).children.add(graph.get(boxed));
                    }
                }

            }
        }

        StringBuilder s = new StringBuilder(graph.size());
        try{
            for (Character key : graph.keySet()){
                Node n = graph.get(key);
                visit(n, s);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "";
        }

        return s.reverse().toString();
    }

    public void visit(Node n, StringBuilder s) throws Exception {
        if (n.visited) return;
        if (n.visiting) throw new Exception("Cycle detected!");
        n.visiting = true;
        for (Node c : n.children) visit(c, s);
        n.visited = true;
        s.append(n.value);
    }
}
