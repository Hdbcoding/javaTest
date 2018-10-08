package buildOrder;

public class OrderProjects {
    public static String[] GetBuildOrderTopological(String[] projects, String[][] dependencies)
            throws InstantiationException {
        Graph graph = Graph.generateGraph(projects, dependencies);
        return orderProjectsTopological(graph);
    }

    private static String[] orderProjectsTopological(Graph graph) {
        return new String[0];
    }

    public static String[] GetBuildOrderDFS(String[] projects, String[][] dependencies)
            throws InstantiationException {
        Graph graph = Graph.generateGraph(projects, dependencies);
        return orderProjectsDFS(graph);
    }

    private static String[] orderProjectsDFS(Graph graph) {
        return new String[0];
    }
}
