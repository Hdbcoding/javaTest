package quicksort;

import java.util.List;

public interface PivotSelector {
    void apply(List<Integer> data, Integer start, Integer end);
}
