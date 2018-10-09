package buildOrder;

import java.util.ArrayList;
import java.util.HashMap;

public class Project {
    private String name;
    private ArrayList<Project> children = new ArrayList<>();
    private HashMap<String, Project> childrenMap = new HashMap<>();
    private int dependencies = 0;
    private ProjectState State = ProjectState.Blank;

    public void addNeighbor(Project dependent) {
        if (!childrenMap.containsKey(dependent.getName())) {
            children.add(dependent);
            childrenMap.put(dependent.getName(), dependent);
            dependent.addDependency();
        }
    }

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Project> getChildren() {
        return children;
    }

    public ProjectState getState() {
        return State;
    }

    public void setState(ProjectState state) {
        State = state;
    }

    public void addDependency() {
        dependencies++;
    }

    public void dropDependency() {
        dependencies--;
    }

    public boolean hasDependencies() {
        return dependencies > 0;
    }
}


