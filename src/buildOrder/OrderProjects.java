package buildOrder;

import java.util.ArrayList;

public class OrderProjects {
    public static String[] GetBuildOrderTopological(String[] projects, String[][] dependencies)
            throws InstantiationException {
        var graph = Graph.generateGraph(projects, dependencies);
        return orderProjectsTopological(graph);
    }

    private static String[] orderProjectsTopological(Graph graph) {
        ArrayList<Project> projects = graph.getNodes();
        var results = new String[projects.size()];
        int lastIndex = addProjectsWithoutDependencies(results, projects, 0);

        int buildIndex = 0;
        while (buildIndex < results.length) {
            if (results[buildIndex] == null) return null;
            var current = graph.getOrCreateNode(results[buildIndex]);
            var children = current.getChildren();
            for (var child : children) child.dropDependency();

            lastIndex = addProjectsWithoutDependencies(results, children, lastIndex);
            buildIndex++;
        }

        return results;
    }

    private static int addProjectsWithoutDependencies(String[] results, ArrayList<Project> projects, int offset) {
        for (var project : projects) {
            if (!project.hasDependencies()) {
                results[offset++] = project.getName();
            }
        }
        return offset;
    }

    public static String[] GetBuildOrderDFS(String[] projects, String[][] dependencies)
            throws InstantiationException {
        var graph = Graph.generateGraph(projects, dependencies);
        return orderProjectsDFS(graph);
    }

    private static String[] orderProjectsDFS(Graph graph) {
        ArrayList<Project> projects = graph.getNodes();
        var results = new String[projects.size()];
        ArrayList<String> pending = new ArrayList<>(projects.size());


        return new String[0];
    }
}
