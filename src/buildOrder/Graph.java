package buildOrder;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private ArrayList<Project> nodes = new ArrayList<>();
    private HashMap<String, Project> nodeMap = new HashMap<>();

    public Project getOrCreateNode(String name) {
        if (!nodeMap.containsKey(name)) {
            Project node = new Project(name);
            nodes.add(node);
            nodeMap.put(name, node);
        }
        return nodeMap.get(name);
    }

    public void addEdge(String start, String end) {
        var startNode = getOrCreateNode(start);
        var endNode = getOrCreateNode(end);
        startNode.addNeighbor(endNode);
    }

    public ArrayList<Project> getNodes() {
        return nodes;
    }

    public static Graph generateGraph(String[] nodes, String[][] edges) throws InstantiationException {
        var graph = new Graph();
        for (var node : nodes) {
            graph.getOrCreateNode(node);
        }
        for (var edge : edges) {
            if (edge.length != 2) throw new InstantiationException("invalid edge definition");
            graph.addEdge(edge[0], edge[1]);
        }
        return graph;
    }
}
