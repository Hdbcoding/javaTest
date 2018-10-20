package test.buildOrder;

import buildOrder.OrderProjects;

import static org.junit.Assert.*;

import org.junit.Test;

public class TopologicalTests {
    @Test
    public void NoDependenciesTopological() {
        String[] nodes = {"a", "b", "c"};
        String[][] edges = new String[0][0];
        String[] result = TestTopological(nodes, edges);
        assertNotNull("Project list is null", result);
        assertEquals("Incorrect number of projects", 3, result.length);
        assertArrayEquals(new String[]{"a", "b", "c"}, result);
    }

    @Test
    public void NullOnCycleTopological(){
        String[] nodes = {"a", "b", "c"};
        String[][] edges = { { "b", "a" }, { "c", "b" }, { "a", "c" } };
        String[] result = TestTopological(nodes, edges);
        assertNull("Project list is null", result);
    }

    @Test
    public void ReverseOrderTopological(){
        String[] nodes = {"a", "b", "c"};
        String[][] edges = { { "b", "a" }, { "c", "b" } };
        String[] result = TestTopological(nodes, edges);
        assertNotNull("Project list is null", result);
        assertEquals("Incorrect number of projects", 3, result.length);
        assertArrayEquals(new String[]{"c", "b", "a"}, result);
    }

    private String[] TestTopological(String[] nodes, String[][] edges){
        String[] result = null;
        try {
            result = OrderProjects.GetBuildOrderTopological(nodes, edges);
        } catch (InstantiationException ie) {
            fail("Failed to instantiate graph");
        }
        return result;
    }

    @Test
    public void NoDependenciesDFS() {
        String[] nodes = {"c", "b", "a"};
        String[][] edges = new String[0][0];
        String[] result = TestDFS(nodes, edges);
        assertNotNull("Project list is null", result);
        assertEquals("Incorrect number of projects", 3, result.length);
        assertArrayEquals(new String[]{"a", "b", "c"}, result); //note - dfs will be in reverse order for each topological level
    }

    @Test
    public void NullOnCycleDFS(){
        String[] nodes = {"a", "b", "c"};
        String[][] edges = { { "b", "a" }, { "c", "b" }, { "a", "c" } };
        String[] result = TestDFS(nodes, edges);
        assertNull("Project list is null", result);
    }

    @Test
    public void ReverseOrderDFS(){
        String[] nodes = {"a", "b", "c"};
        String[][] edges = { { "b", "a" }, { "c", "b" } };
        String[] result = TestDFS(nodes, edges);
        assertNotNull("Project list is null", result);
        assertEquals("Incorrect number of projects", 3, result.length);
        assertArrayEquals(new String[]{"c", "b", "a"}, result);
    }

    private String[] TestDFS(String[] nodes, String[][] edges) {
        String[] result = null;
        try {
            result = OrderProjects.GetBuildOrderDFS(nodes, edges);
        } catch (InstantiationException ie) {
            fail("Failed to instantiate graph");
        }
        return result;
    }
}
