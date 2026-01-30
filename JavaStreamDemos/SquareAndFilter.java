import java.util.List;

public class SquareAndFilter {

    public static void squareAndFilter() {

        List<Integer> numbers = List.of(1,2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Original numbers: " + numbers);

        //end product: List<Integer> of squared even numbers
        List<Integer> result = numbers.stream()
                .map(n -> n * n)
                .filter(square -> square > 20)
                .toList();

        System.out.println("Squared numbers greater than 20: " + result);
    }
}