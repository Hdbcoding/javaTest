package test.buildOrder;

import buildOrder.OrderProjects;
import static org.junit.Assert.*;
import org.junit.Test;

public class TopologicalTests {
    @Test
    public void NoDependenciesTopological(){
        String[] nodes = { "a", "b", "c" };
        String[][] edges = new String[0][0];
        String[] result = null;
        try{
            result = OrderProjects.GetBuildOrderTopological(nodes, edges);
        } catch (InstantiationException ie){
            fail("Failed to instantiate graph");
        }
        assertNotNull("Project list is null",result);
        assertEquals("Incorrect number of projects", 3, result.length);
    }
}
