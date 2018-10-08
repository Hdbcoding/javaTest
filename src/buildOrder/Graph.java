package buildOrder;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private ArrayList<Project> nodes = new ArrayList<>();
    private HashMap<String, Project> nodeMap = new HashMap<>();

    public Project getOrCreateNode(String name){
        if (!nodeMap.containsKey(name)){
            Project node = new Project(name);
            nodes.add(node);
            nodeMap.put(name, node);
        }
        return nodeMap.get(name);
    }

    public void addEdge(String start, String end){
        Project startNode = getOrCreateNode(start);
        Project endNode = getOrCreateNode(end);
        startNode.addNeighbor(endNode);
    }

    public ArrayList<Project> getNodes() {
        return nodes;
    }

    public static Graph generateGraph(String[] nodes, String[][] edges) throws InstantiationException {
        Graph graph = new Graph();
        for (String node : nodes){
            graph.getOrCreateNode(node);
        }
        for (String[] edge : edges){
            if (edge.length != 2) throw new InstantiationException("invalid edge definition");
            graph.addEdge(edge[0], edge[1]);
        }
        return graph;
    }
}
