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
        if (end - start <= 0) return 0;
        int length = end - start;
        var pivot = (int) rule.apply(data, start, end);
        int pivotDestination = partition(data, pivot, start, end);
        int left = sortAndCountComparisons(data, start, pivotDestination - 1, rule);
        int right = sortAndCountComparisons(data, pivotDestination + 1, end, rule);
        return length + left + right;
    }

    private static int partition(List<Integer> data, int pivot, int start, int end) {
        return 0;
    }

    public static PivotSelector FirstElementPivot = (List<Integer> data, Integer start, Integer end) -> start;
    public static PivotSelector LastElementPivot = (List<Integer> data, Integer start, Integer end) -> end;
    public static PivotSelector MedianElementPivot = (List<Integer> data, Integer start, Integer end) ->
    {
        Integer middle = (start + end) / 2;
        Integer first = data.get(start);
        Integer second = data.get(middle);
        Integer third = data.get(end);
        if (first < second) {
            if (second < third) return middle; //123
            if (first < third) return end; //132
            return start; //312
        }
        if (third < second) return middle; // 321
        if (third < first) return end; //231
        return start; //213
    };
}

interface PivotSelector {
    public Integer apply(List<Integer> data, Integer start, Integer end);
}
