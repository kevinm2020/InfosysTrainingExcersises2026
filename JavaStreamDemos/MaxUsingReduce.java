import java.util.List;
import java.util.Optional;

public class MaxUsingReduce {
    
    public static void maxUsingReduce()
    {
        List<Integer> numbers = List.of(10,45,23,89,12);
        System.out.println("Original numbers: " + numbers);

        Optional<Integer> max = numbers.stream()
                                       .reduce(Integer::max);

        System.out.println("Maximum number: " + max.orElse(null));
    }
}
