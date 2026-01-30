import java.util.List;

public class DistinctAndSort {
    
    public static void distinctAndSort() {
        List<Integer> numbers = List.of(5, 3, 8, 3, 9, 5, 1, 8, 2);
        System.out.println("Original Numbers: " + numbers);

        List<Integer> distinctSortedNumbers = numbers.stream()
        .distinct()
        .sorted()
        .toList();

        System.out.println("Distinct and Sorted Numbers: " + distinctSortedNumbers);
    }
}
