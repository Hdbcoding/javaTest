package quicksort;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

//coursera - Divide and Conquer, programming assignment 3
public class QuickSort {
    public static List<Integer> loadData(String path) {
        var list = new ArrayList<Integer>();
        try (Stream<String> lines = Files.lines(Paths.get(path))){
            lines.forEachOrdered(line -> {
                try {
                    var value = Integer.parseInt(line);
                    list.add(value);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int sortAndCountComparisons(List<Integer> data, PivotSelector rule){
        return sortAndCountComparisons(data, 0, data.size() - 1, rule);
    }

    private static int sortAndCountComparisons(List<Integer> data, int start, int end, PivotSelector rule) {
        int length = end - start + 1;
        if (length <= 1) return 0;
        int pivotDestination = partition(data, start, end, rule);
        int left = sortAndCountComparisons(data, start, pivotDestination - 1, rule);
        int right = sortAndCountComparisons(data, pivotDestination + 1, end, rule);
        return length - 1 + left + right;
    }

    private static int partition(List<Integer> data, int start, int end, PivotSelector rule) {
        rule.apply(data, start, end); //send pivot to beginning of array
        int pivot = data.get(start);
        int i = start + 1;
        for (int j = start + 1; j <= end; j++){
            if (data.get(j) < pivot) {
                swap(data, i++, j);
            }
        }
        swap(data, start, i - 1);
        return i - 1;
    }

    private static void swap(List<Integer> data, int i, int j){
        var value = data.get(i);
        data.set(i, data.get(j));
        data.set(j, value);
    }

    public static PivotSelector FirstElementPivot = (List<Integer> data, Integer start, Integer end) -> {}; //do nothing
    public static PivotSelector LastElementPivot = (List<Integer> data, Integer start, Integer end) -> swap(data, end, start);
    public static PivotSelector MedianElementPivot = (List<Integer> data, Integer start, Integer end) ->
    {
        Integer middle = (start + end) / 2;
        Integer first = data.get(start);
        Integer second = data.get(middle);
        Integer third = data.get(end);
        if (first < second) {
            if (second < third) swap(data, middle, start); //123
            else if (first < third) swap(data, end, start); //132
        } else {
            if (third < second) swap(data, middle, start); // 321
            else if (third < first) swap(data, end, start); //231
        }
    };
}