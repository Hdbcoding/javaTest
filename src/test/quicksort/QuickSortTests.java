package test.quicksort;

import quicksort.PivotSelector;
import quicksort.QuickSort;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class QuickSortTests {
    @Test
    public void Homework() {        TestFile("homework", -1, -1, -1);    }

    @Test
    public void Test11_20(){
        TestFile("11_20", 71, 73, 56);
    }

    @Test
    public void Test16_100000(){
        TestFile("16_100000", 2127173, 2079088, 1749103);
    }

    public void TestFile(String fileName, int first, int last, int median){
        int fA = TestFile(fileName, QuickSort.FirstElementPivot);
        int lA = TestFile(fileName, QuickSort.LastElementPivot);
        int mA = TestFile(fileName, QuickSort.MedianElementPivot);
        System.out.println("First: " + fA);
        System.out.println("Last: " + lA);
        System.out.println("Median: " + mA);
        assertEquals(first, fA);
        assertEquals(last, lA);
        assertEquals(median, mA);
    }

    private int TestFile(String fileName, PivotSelector rule){
        List<Integer> data = QuickSort.loadData("src\\quicksort\\"+fileName+".txt");
        return QuickSort.sortAndCountComparisons(data, rule);
    }
}
