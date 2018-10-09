package buildOrder;

import java.util.ArrayList;
import java.util.Stack;

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
        var reverseBuild = new Stack<Project>();
        var projects = graph.getNodes();

        for (var project : projects)
            if (project.getState() == ProjectState.Blank && !doDFS(project, reverseBuild)) return null;

        String[] result = new String[projects.size()];
        for (int i = 0; i < result.length; i++){
            result[i] = reverseBuild.pop().getName();
        }
        return result;
    }

    private static boolean doDFS(Project p, Stack<Project> reverseBuild) {
        if (p.getState() == ProjectState.Building) return false;
        if (p.getState() == ProjectState.Built) return true;

        p.setState(ProjectState.Building);
        var children = p.getChildren();
        for (var child : children) if (!doDFS(child, reverseBuild)) return false;

        p.setState(ProjectState.Built);
        reverseBuild.push(p);
        return true;
    }
}
