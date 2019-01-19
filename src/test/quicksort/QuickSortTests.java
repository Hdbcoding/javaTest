package test.quicksort;

import org.junit.Assert;
import quicksort.QuickSort;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class QuickSortTests {
    @Test
    public void LoadDataWorks(){
        List<Integer> data = QuickSort.loadData("src\\quicksort\\data.txt");
        assertEquals(10000, data.size());
    }

    public void SortWorks(){
        List<Integer> data = List.of(5, 4, 3, 2, 1);
        var comparisons = QuickSort.sortAndCountComparisons(data);
        System.out.println(comparisons);
        assertEquals((Integer)1, data.get(0));
    }
}
